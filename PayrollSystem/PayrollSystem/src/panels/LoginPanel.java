package panels;
import main.PayrollSystem;
import main.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPanel extends JPanel {

    public LoginPanel(PayrollSystem frame) {
        setLayout(new BorderLayout());
        setBackground(new Color(60, 179, 113));

        JLabel title = new JLabel("Payroll System - Login", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // Login form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

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
        formPanel.add(loginButton, gbc);
        gbc.gridx = 1;
        formPanel.add(signupButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if (authenticate(username, password)) {
                frame.showDashboard();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Login Credentials");
            }
        });

        signupButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new SignupPanel(frame));
            frame.revalidate();
            frame.repaint();
        });
    }

    private boolean authenticate(String username, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
        }
        return false;
    }
}
