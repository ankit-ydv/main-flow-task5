package ui;

import javax.swing.*;

import src.db.DatabaseConnection;

import java.awt.event.*;
import java.sql.*;

public class AddProductForm extends JFrame {
    JTextField nameField, categoryField, priceField, quantityField;
    JTextArea descriptionArea;
    JButton submitButton;

    public AddProductForm() {
        setTitle("Add Product");
        setSize(300, 400);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 150, 25);
        add(nameField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(20, 60, 80, 25);
        add(categoryLabel);

        categoryField = new JTextField();
        categoryField.setBounds(100, 60, 150, 25);
        add(categoryField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 100, 80, 25);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(100, 100, 150, 25);
        add(priceField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 140, 80, 25);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(100, 140, 150, 25);
        add(quantityField);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(20, 180, 80, 25);
        add(descLabel);

        descriptionArea = new JTextArea();
        descriptionArea.setBounds(100, 180, 150, 60);
        add(descriptionArea);

        submitButton = new JButton("Add Product");
        submitButton.setBounds(80, 260, 120, 30);
        add(submitButton);

        submitButton.addActionListener(e -> addProduct());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void addProduct() {
        String name = nameField.getText();
        String category = categoryField.getText();
        String priceText = priceField.getText();
        String quantityText = quantityField.getText();
        String description = descriptionArea.getText();

        if (name.isEmpty() || category.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            int quantity = Integer.parseInt(quantityText);

            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO products (name, category, price, quantity, description) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setDouble(3, price);
            ps.setInt(4, quantity);
            ps.setString(5, description);

            int result = ps.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Product added successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add product.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            conn.close();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddProductForm();
    }
}
