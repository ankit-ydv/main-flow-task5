import javax.swing.*;

import src.db.DatabaseConnection;

import java.awt.event.*;
import java.sql.*;

public class AddBuyerForm extends JFrame {
    private JTextField nameField, emailField, phoneField, addressField;
    private JButton addButton;

    public AddBuyerForm() {
        setTitle("Add Buyer");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 80, 25);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(120, 30, 200, 25);
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 70, 80, 25);
        add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(120, 70, 200, 25);
        add(emailField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(30, 110, 80, 25);
        add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBounds(120, 110, 200, 25);
        add(phoneField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(30, 150, 80, 25);
        add(addressLabel);
        addressField = new JTextField();
        addressField.setBounds(120, 150, 200, 25);
        add(addressField);

        addButton = new JButton("Add Buyer");
        addButton.setBounds(120, 200, 150, 30);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();

                try (Connection conn = DatabaseConnection.getConnection()) {
                    String sql = "INSERT INTO buyers (name, email, phone, address) VALUES (?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, name);
                    stmt.setString(2, email);
                    stmt.setString(3, phone);
                    stmt.setString(4, address);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Buyer added successfully!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddBuyerForm();
    }
}

