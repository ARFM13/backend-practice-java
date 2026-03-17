const auth = 'Basic ' + btoa('angel:12345');
const headers = { 'Authorization': auth, 'Content-Type': 'application/json' };
let allDevs = [];
let teamToDeleteId = null;

function showTab(tab) {
    document.querySelectorAll('.section').forEach(s => s.classList.remove('active'));
    document.querySelectorAll('.tab-btn').forEach(b => b.classList.remove('active'));
    document.getElementById('section-' + tab).classList.add('active');
    document.getElementById('btn-tab-' + tab).classList.add('active');
    if(tab === 'devs') loadAll(); else loadTeams();
}

async function loadAll() {
    await loadTeams();
    const res = await fetch('/api/developers', { headers });
    allDevs = await res.json();
    renderDevs(allDevs);
}

async function loadTeams() {
    const res = await fetch('/api/teams', { headers });
    const teams = await res.json();
    const list = document.getElementById('teams-list');
    list.innerHTML = teams.length === 0 ? '<p>No hay equipos.</p>' :
        teams.map(t => `<div class="item-row"><span><strong>${t.name}</strong></span><button class="btn-sm btn-del" onclick="deleteTeam(${t.id})"><i class="fas fa-trash"></i></button></div>`).join('');

    const options = '<option value="">Sin Equipo (Freelance)</option>' + teams.map(t => `<option value="${t.id}">${t.name}</option>`).join('');
    document.getElementById('dev-team-select').innerHTML = options;
    document.getElementById('modal-team-select').innerHTML = teams.map(t => `<option value="${t.id}">${t.name}</option>`).join('');
}

function renderDevs(data) {
    const list = document.getElementById('devs-list');
    list.innerHTML = data.map(d => `
        <div class="item-row">
            <div><strong>${d.name}</strong> <span class="badge">${d.language}</span><br><small>${d.team ? d.team.name : 'Freelance'}</small></div>
            <div class="actions">
                <button class="btn-sm btn-success" onclick='editDev(${JSON.stringify(d)})'><i class="fas fa-edit"></i></button>
                <button class="btn-sm btn-del" onclick="deleteDev(${d.id})"><i class="fas fa-trash"></i></button>
            </div>
        </div>`).join('');
}

function filterDevs() {
    const val = document.getElementById('search').value.toLowerCase();
    renderDevs(allDevs.filter(d => d.name.toLowerCase().includes(val) || d.language.toLowerCase().includes(val)));
}

async function saveDev() {
    const id = document.getElementById('edit-id').value;
    const teamId = document.getElementById('dev-team-select').value;
    const body = { name: document.getElementById('dev-name').value, language: document.getElementById('dev-lang').value, team: teamId ? { id: parseInt(teamId) } : null };
    await fetch(id ? `/api/developers/${id}` : '/api/developers', { method: id ? 'PUT' : 'POST', headers, body: JSON.stringify(body) });
    resetForm(); loadAll();
}

function editDev(dev) {
    document.getElementById('form-title').innerText = "Editar";
    document.getElementById('edit-id').value = dev.id;
    document.getElementById('dev-name').value = dev.name;
    document.getElementById('dev-lang').value = dev.language;
    document.getElementById('dev-team-select').value = dev.team ? dev.team.id : '';
    document.getElementById('btn-cancel').style.display = "block";
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

function resetForm() {
    document.getElementById('form-title').innerText = "Registrar";
    document.getElementById('edit-id').value = ""; document.getElementById('dev-name').value = "";
    document.getElementById('dev-lang').value = ""; document.getElementById('btn-cancel').style.display = "none";
}

async function deleteDev(id) { if(confirm("¿Borrar?")) { await fetch(`/api/developers/${id}`, { method: 'DELETE', headers }); loadAll(); } }

async function deleteTeam(id) {
    teamToDeleteId = id;
    const miembros = allDevs.filter(d => d.team && d.team.id === id);
    if (miembros.length > 0) document.getElementById('delete-team-modal').style.display = "block";
    else if(confirm("¿Eliminar equipo vacío?")) ejecutarBorrado(id);
}

async function reubicarDevs(newTeamId) {
    const miembros = allDevs.filter(d => d.team && d.team.id === teamToDeleteId);
    for (let dev of miembros) {
        dev.team = newTeamId ? { id: parseInt(newTeamId) } : null;
        await fetch(`/api/developers/${dev.id}`, { method: 'PUT', headers, body: JSON.stringify(dev) });
    }
    ejecutarBorrado(teamToDeleteId);
    closeModal();
}

async function ejecutarBorrado(id) {
    await fetch(`/api/teams/${id}`, { method: 'DELETE', headers });
    loadAll();
}

function closeModal() { document.getElementById('delete-team-modal').style.display = "none"; }
async function createTeam() {
    const name = document.getElementById('team-name').value;
    await fetch('/api/teams', { method: 'POST', headers, body: JSON.stringify({ name }) });
    document.getElementById('team-name').value = ""; loadTeams();
}

loadAll();