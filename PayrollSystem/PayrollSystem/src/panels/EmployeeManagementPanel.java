package panels;

import main.DatabaseConnection;
import main.PayrollSystem;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeManagementPanel extends JPanel {

    PayrollSystem frame;

    public EmployeeManagementPanel(PayrollSystem frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Employee Management", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.BLUE);
        add(title, BorderLayout.NORTH);

        // Buttons
        JButton addEmployeeButton = new JButton("Add Employee", new ImageIcon("src/resources/"));
        JButton viewEmployeesButton = new JButton("View Employees");
        JButton backButton = new JButton("Back", new ImageIcon("src/resources/back_icon.png"));

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(addEmployeeButton);
        buttonPanel.add(viewEmployeesButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Add Employee Action
        addEmployeeButton.addActionListener(e -> {
            String firstName = JOptionPane.showInputDialog("Enter First Name:");
            String lastName = JOptionPane.showInputDialog("Enter Last Name:");
            String position = JOptionPane.showInputDialog("Enter Position:");
            double salary = Double.parseDouble(JOptionPane.showInputDialog("Enter Salary:"));

            if (addEmployee(firstName, lastName, position, salary)) {
                JOptionPane.showMessageDialog(null, "Employee Added Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Error Adding Employee");
            }
        });

        // View Employees Action
        viewEmployeesButton.addActionListener(e -> displayEmployees());

        // Back button action
        backButton.addActionListener(e -> frame.showDashboard());
    }

    // Method to add employee to DB
    private boolean addEmployee(String firstName, String lastName, String position, double salary) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO employees (first_name, last_name, position, salary) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, position);
            ps.setDouble(4, salary);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Method to display employees
    private void displayEmployees() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM employees";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            StringBuilder employeeList = new StringBuilder();
            while (rs.next()) {
                employeeList.append("ID: ").append(rs.getInt("emp_id"))
                        .append(", Name: ").append(rs.getString("first_name"))
                        .append(" ").append(rs.getString("last_name"))
                        .append(", Position: ").append(rs.getString("position"))
                        .append(", Salary: ").append(rs.getDouble("salary")).append("\n");
            }

            JTextArea textArea = new JTextArea(employeeList.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setEditable(false);

            JFrame displayFrame = new JFrame("Employee List");
            displayFrame.setSize(400, 400);
            displayFrame.add(scrollPane);
            displayFrame.setVisible(true);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
