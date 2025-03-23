package main;

import panels.DashboardPanel;
import panels.LoginPanel;
import javax.swing.*;
import java.awt.*;

public class PayrollSystem extends JFrame {
    public PayrollSystem() {
        setTitle("Payroll System");
        setLocationRelativeTo(null);
        // Load the background image
        ImageIcon backgroundIcon = new ImageIcon("src/resources/add_icon.png");
        
        // Create a panel that draws the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image scaled to fit the panel
                g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());  // Layout to position components

        // Create a login panel (this should be transparent to allow the background to show)
        JPanel loginPanel = new LoginPanel(this);
        loginPanel.setOpaque(false);  // Make the login panel transparent so the background is visible

        // Add login panel on top of the background
        backgroundPanel.add(loginPanel, new GridBagConstraints());

        // Add the background panel to the frame
        setContentPane(backgroundPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    public void showDashboard() {
        getContentPane().removeAll();
        getContentPane().add(new DashboardPanel(this));
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PayrollSystem frame = new PayrollSystem();
            frame.setVisible(true);
        });
    }
    
    public void showLoginPanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void add(ImageIcon backgroundIcon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
