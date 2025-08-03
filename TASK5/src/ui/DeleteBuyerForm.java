import javax.swing.*;

import src.db.DatabaseConnection;

import java.awt.event.*;
import java.sql.*;

public class DeleteBuyerForm extends JFrame {
    private JTextField idField;
    private JButton deleteButton;

    public DeleteBuyerForm() {
        setTitle("Delete Buyer");
        setSize(300, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel idLabel = new JLabel("Buyer ID:");
        idLabel.setBounds(30, 30, 80, 25);
        add(idLabel);
        idField = new JTextField();
        idField.setBounds(120, 30, 130, 25);
        add(idField);

        deleteButton = new JButton("Delete Buyer");
        deleteButton.setBounds(80, 80, 140, 30);
        add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    try (Connection conn = DatabaseConnection.getConnection()) {
                        String sql = "DELETE FROM buyers WHERE id=?";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, id);
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Buyer deleted successfully!");
                        } else {
                            JOptionPane.showMessageDialog(null, "No such buyer ID found.");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID format.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new DeleteBuyerForm();
    }
}

