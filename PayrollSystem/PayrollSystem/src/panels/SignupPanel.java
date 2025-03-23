package panels;

import main.PayrollSystem;
import main.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupPanel extends JPanel {

    public SignupPanel(PayrollSystem frame) {
        setLayout(new BorderLayout());
        setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Sign Up", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // Signup form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);
        JLabel nameLabel = new JLabel("Full Name:");
        JTextField nameField = new JTextField(15);
        JButton signupButton = new JButton("Sign Up");
//        JButton backButton = new JButton(new ImageIcon("src/resources/back_icon.png"));

        JButton backButton = new JButton("Back");

        formPanel.add(userLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(userField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(passLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(signupButton, gbc);
        gbc.gridx = 1;
        formPanel.add(backButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        signupButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            String fullName = nameField.getText();
            if (register(username, password, fullName)) {
                JOptionPane.showMessageDialog(null, "User Registered Successfully");
                frame.showDashboard();
            } else {
                JOptionPane.showMessageDialog(null, "Registration Failed");
            }
        });

        backButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new LoginPanel(frame));
            frame.revalidate();
            frame.repaint();
        });
    }

    private boolean register(String username, String password, String fullName) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO users (username, password, full_name) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullName);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
