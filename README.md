# 🚀 Dev Manager 2.0 - Java & MySQL

A robust developer management system built with **Java 21**, using **Maven** for dependency management and **MySQL/MariaDB** (running on Fedora) for data persistence. This version implements the **DAO (Data Access Object)** pattern for professional database management.

## ✨ Key Features
- **Full CRUD Support:** Create, Read, Update, and Delete developers directly from the console.
- **Real-Time Persistence:** All data is stored and retrieved from a MariaDB/MySQL database.
- **Smart Search & Analysis:** Quickly locate developers and get an automated rank evaluation (Junior, Mid, Senior).
- **Data Validation:** Robust input handling to prevent crashes when entering experience years.
- **DAO Architecture:** Clean separation between the database logic and the user interface.



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