package panels;

import main.DatabaseConnection;
import main.PayrollSystem;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewPayrollPanel extends JPanel {

    PayrollSystem frame;

    public ViewPayrollPanel(PayrollSystem frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Payroll History", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); // Adding spacing
        add(title, BorderLayout.NORTH);

        // Payroll History Panel
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBackground(new Color(60, 63, 65));

        // Payroll List (Area to show payroll history)
        JTextArea payrollListArea = new JTextArea();
        payrollListArea.setFont(new Font("Arial", Font.PLAIN, 14));
        payrollListArea.setEditable(false);
        payrollListArea.setForeground(Color.WHITE);
        payrollListArea.setBackground(new Color(30, 30, 30)); // Dark background for the text area
        JScrollPane scrollPane = new JScrollPane(payrollListArea);
        historyPanel.add(scrollPane, BorderLayout.CENTER);

        add(historyPanel, BorderLayout.CENTER);

        // Back button (aligned at the bottom)
        JButton backButton = new JButton("Back to Dashboard");
        styleButton(backButton);
        add(backButton, BorderLayout.SOUTH);

        backButton.addActionListener(e -> frame.showDashboard());

        // Load payroll history on load
        loadPayrollHistory(payrollListArea);
    }

    // Method to load payroll history from the database
    private void loadPayrollHistory(JTextArea payrollListArea) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM payroll";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            StringBuilder payrollList = new StringBuilder();
            payrollList.append("Payroll History:\n\n");
            while (rs.next()) {
                payrollList.append("Payroll ID: ").append(rs.getInt("payroll_id"))
                        .append(", Employee ID: ").append(rs.getInt("emp_id"))
                        .append(", Pay Date: ").append(rs.getDate("pay_date"))
                        .append(", Total Salary: ").append(rs.getDouble("total_salary")).append("\n");
            }

            payrollListArea.setText(payrollList.toString());

        } catch (SQLException ex) {
            ex.printStackTrace();
            payrollListArea.setText("Error loading payroll history.");
        }
    }

    // Styling buttons
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setBackground(new Color(0, 153, 76)); // Custom green color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
}
