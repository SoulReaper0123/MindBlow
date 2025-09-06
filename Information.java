package mindblowgame;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Information extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Information frame = new Information();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Information() {
        setTitle("Information - Mind Blow Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                Color color1 = new Color(40, 40, 80);
                Color color2 = new Color(10, 10, 30);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton backButton = new JButton("← Back");
        backButton.setFocusPainted(false);
        backButton.setBackground(new Color(70, 70, 120));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBorderPainted(false);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                home homeFrame = new home();
                homeFrame.setVisible(true);
                dispose();
            }
        });
        backButton.setBounds(30, 20, 100, 35);
        contentPane.add(backButton);
        
        JLabel titleLabel = new JLabel("Game Information");
        titleLabel.setForeground(new Color(255, 215, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 36));
        titleLabel.setBounds(0, 30, 800, 50);
        contentPane.add(titleLabel);
        
        JPanel infoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color color1 = new Color(30, 30, 60);
                Color color2 = new Color(20, 20, 40);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                g2d.setColor(new Color(100, 100, 200, 30));
                g2d.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 18, 18);
            }
        };
        infoPanel.setOpaque(false);
        infoPanel.setBounds(50, 100, 700, 200);
        infoPanel.setLayout(null);
        contentPane.add(infoPanel);
        
        JLabel rule1 = new JLabel("1. Click on boxes to reveal diamonds or bombs");
        rule1.setForeground(new Color(200, 200, 255));
        rule1.setFont(new Font("Arial", Font.BOLD, 16));
        rule1.setBounds(30, 20, 640, 25);
        infoPanel.add(rule1);
        
        JLabel rule2 = new JLabel("2. Find all diamonds to win the level");
        rule2.setForeground(new Color(200, 200, 255));
        rule2.setFont(new Font("Arial", Font.BOLD, 16));
        rule2.setBounds(30, 50, 640, 25);
        infoPanel.add(rule2);
        
        JLabel rule3 = new JLabel("3. Avoid bombs - they will end your game");
        rule3.setForeground(new Color(200, 200, 255));
        rule3.setFont(new Font("Arial", Font.BOLD, 16));
        rule3.setBounds(30, 80, 640, 25);
        infoPanel.add(rule3);
        
        JLabel rule4 = new JLabel("4. Progress through levels with increasing difficulty");
        rule4.setForeground(new Color(200, 200, 255));
        rule4.setFont(new Font("Arial", Font.BOLD, 16));
        rule4.setBounds(30, 110, 640, 25);
        infoPanel.add(rule4);
        
        JLabel rule5 = new JLabel("5. Use strategy to maximize your chances of winning");
        rule5.setForeground(new Color(200, 200, 255));
        rule5.setFont(new Font("Arial", Font.BOLD, 16));
        rule5.setBounds(30, 140, 640, 25);
        infoPanel.add(rule5);
        
        JLabel developersTitle = new JLabel("Developed by:");
        developersTitle.setForeground(new Color(255, 215, 0));
        developersTitle.setHorizontalAlignment(SwingConstants.CENTER);
        developersTitle.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 28));
        developersTitle.setBounds(0, 320, 800, 40);
        contentPane.add(developersTitle);
        
        JPanel devPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color color1 = new Color(30, 30, 60);
                Color color2 = new Color(20, 20, 40);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                g2d.setColor(new Color(100, 100, 200, 30));
                g2d.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 18, 18);
            }
        };
        devPanel.setOpaque(false);
        devPanel.setBounds(200, 370, 400, 80);
        devPanel.setLayout(null);
        contentPane.add(devPanel);
        
        JLabel dev1 = new JLabel("Louis Andre Dy Sarino");
        dev1.setForeground(new Color(200, 200, 255));
        dev1.setFont(new Font("Arial", Font.BOLD, 16));
        dev1.setBounds(100, 20, 200, 25);
        devPanel.add(dev1);
        
    }
}