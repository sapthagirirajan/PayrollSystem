package panels;

import main.DatabaseConnection;
import main.PayrollSystem;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PayrollProcessingPanel extends JPanel {

    PayrollSystem frame;

    public PayrollProcessingPanel(PayrollSystem frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        // Title at the top
        JLabel title = new JLabel("Payroll Processing", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); // Adding spacing
        add(title, BorderLayout.NORTH);

        // Creating an options panel with buttons arranged side by side
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50)); // FlowLayout to arrange side-by-side
        buttonPanel.setBackground(new Color(60, 63, 65));

        // Creating buttons
        JButton processPayrollButton = new JButton("Process Payroll");
        JButton backButton = new JButton("Back to Dashboard");

        // Styling buttons
        styleButton(processPayrollButton);
        styleButton(backButton);

        // Adding buttons to panel
        buttonPanel.add(processPayrollButton);
        buttonPanel.add(backButton);

        // Adding button panel to the center
        add(buttonPanel, BorderLayout.CENTER);

        // Adding functionality for buttons
        processPayrollButton.addActionListener(e -> processPayroll());
        backButton.addActionListener(e -> frame.showDashboard());
    }

    // Functionality for processing payroll
    private void processPayroll() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO payroll (emp_id, pay_date, total_salary) SELECT emp_id, NOW(), salary FROM employees";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Payroll Processed Successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error in processing payroll.");
        }
    }

    // Method to style buttons
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setBackground(new Color(0, 153, 76)); // Custom green color for payroll processing
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
}
