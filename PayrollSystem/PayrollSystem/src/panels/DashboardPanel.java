package panels;

import main.PayrollSystem;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel(PayrollSystem frame) {
        setLayout(new BorderLayout());

        // Adding a Menu Bar at the Top
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem logoutItem = new JMenuItem("Logout");
        JMenuItem settingsItem = new JMenuItem("Settings");
        
        menu.add(settingsItem);
        menu.add(logoutItem);
        menuBar.add(menu);

        // Add action listeners to menu items
        logoutItem.addActionListener(e -> {
            // Logic for logout
            JOptionPane.showMessageDialog(frame, "You have logged out.");
            frame.showLoginPanel();  // Going back to the login screen
        });

        settingsItem.addActionListener(e -> {
            // Logic for settings
            JOptionPane.showMessageDialog(frame, "Settings are not implemented yet.");
        });

        frame.setJMenuBar(menuBar);  // Set menu bar to the frame

        // Title at the top
        JLabel welcomeLabel = new JLabel("Payroll Management Dashboard", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); // Adding spacing
        add(welcomeLabel, BorderLayout.NORTH);

        // Main options panel with buttons in a grid layout (side by side)
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 3, 50, 50)); // 1 row, 3 columns, spacing
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // External padding
        optionsPanel.setBackground(new Color(60, 63, 65));

        // Adding buttons
        JButton employeeMgmtButton = new JButton("Employee Management");
        JButton payrollButton = new JButton("Payroll Processing");
        JButton viewPayrollButton = new JButton("View Payroll History");

        // Styling buttons
        styleButton(employeeMgmtButton);
        styleButton(payrollButton);
        styleButton(viewPayrollButton);

        // Adding buttons to the options panel
        optionsPanel.add(employeeMgmtButton);
        optionsPanel.add(payrollButton);
        optionsPanel.add(viewPayrollButton);

        add(optionsPanel, BorderLayout.CENTER);

        // Action Listeners for buttons
        employeeMgmtButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new EmployeeManagementPanel(frame));
            frame.revalidate();
            frame.repaint();
        });

        payrollButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new PayrollProcessingPanel(frame));
            frame.revalidate();
            frame.repaint();
        });

        viewPayrollButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new ViewPayrollPanel(frame));
            frame.revalidate();
            frame.repaint();
        });
    }

    // Method to style buttons
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setBackground(new Color(30, 144, 255)); // DodgerBlue color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 120, 215), 2),  // Blue border
                BorderFactory.createEmptyBorder(15, 20, 15, 20)  // Padding
        ));
    }
}
