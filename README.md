# 🧾 Java Inventory Management (Task 5 – Internship )
A Java Swing desktop application with **MySQL database integration**. It allows users to **add and delete products and buyers** using a simple GUI and JDBC.

---

## ✅ Features

- Add new products with name, category, price, quantity, and description
- Add new buyers with name, email, phone, and address
- Delete products or buyers using their ID
- JDBC integration with MySQL
- GUI built using Java Swing and NetBeans IDE

---

## 🏗️ Project Structure
src/
├── db/
│   └── DatabaseConnection.java
├── model/
│   ├── Product.java
│   └── Buyer.java
└── ui/
    ├── AddProductForm.java
    ├── DeleteProductForm.java
    ├── AddBuyerForm.java
    └── DeleteBuyerForm.java
