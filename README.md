# 🚀 Dev Manager 2.0 - Java & MySQL

A robust developer management system built with **Java 21**, using **Maven** for dependency management and **MySQL/MariaDB** (running on Fedora) for data persistence.

## ✨ Key Features
- **Real-Time Persistence:** All data is stored and retrieved from a MariaDB/MySQL database.
- **Smart Search:** Quickly locate developers by name within the system.
- **Automated Data Loading:** The program synchronizes with the DB upon startup to restore the last state.
- **Profile Evaluation:** Built-in logic to categorize developers based on their experience levels.

## 🛠️ Requirements & Stack
- **Language:** Java 21 (OpenJDK)
- **Database:** MySQL 8.0+ / MariaDB
- **Build Tool:** Maven 3.9+
- **OS:** Fedora Linux

## 📋 Database Setup
To get this project running, execute the following script in your MySQL terminal:

```sql
CREATE DATABASE dev_manager;
USE dev_manager;

CREATE TABLE developers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    language VARCHAR(50),
    experience VARCHAR(20)
);
