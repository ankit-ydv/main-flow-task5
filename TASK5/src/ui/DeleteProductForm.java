package ui;

import javax.swing.*;

import src.db.DatabaseConnection;

import java.awt.event.*;
import java.sql.*;

public class DeleteProductForm extends JFrame {
    JTextField idField;
    JButton deleteButton;

    public DeleteProductForm() {
        setTitle("Delete Product");
        setSize(300, 150);
        setLayout(null);

        JLabel idLabel = new JLabel("Enter Product ID:");
        idLabel.setBounds(20, 20, 120, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(150, 20, 100, 25);
        add(idField);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(100, 60, 100, 30);
        add(deleteButton);

        deleteButton.addActionListener(e -> deleteProduct());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void deleteProduct() {
        String idText = idField.getText();
        try {
            int id = Integer.parseInt(idText);
            Connection conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM products WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int result = ps.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Product deleted successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Product not found.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

            conn.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DeleteProductForm();
    }
}

