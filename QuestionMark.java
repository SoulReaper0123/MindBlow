package mindblowgame;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class QuestionMark extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuestionMark frame = new QuestionMark();
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
    public QuestionMark() {
        setTitle("How to Play - Mind Blow Game");
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
        
        JLabel titleLabel = new JLabel("How to Play");
        titleLabel.setForeground(new Color(255, 215, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 36));
        titleLabel.setBounds(0, 30, 800, 50);
        contentPane.add(titleLabel);
        
        JPanel instructionsPanel = new JPanel() {
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
        instructionsPanel.setOpaque(false);
        instructionsPanel.setBounds(50, 100, 700, 430);
        instructionsPanel.setLayout(null);
        contentPane.add(instructionsPanel);
        
        JLabel step1 = new JLabel("1. Click the PLAY button to start the game");
        step1.setForeground(new Color(200, 200, 255));
        step1.setFont(new Font("Arial", Font.BOLD, 16));
        step1.setBounds(30, 20, 640, 25);
        instructionsPanel.add(step1);
        
        JLabel step2 = new JLabel("2. Question mark boxes will appear on the grid");
        step2.setForeground(new Color(200, 200, 255));
        step2.setFont(new Font("Arial", Font.BOLD, 16));
        step2.setBounds(30, 60, 640, 25);
        instructionsPanel.add(step2);
        
        JLabel step3 = new JLabel("3. Click boxes to reveal either diamonds or bombs");
        step3.setForeground(new Color(200, 200, 255));
        step3.setFont(new Font("Arial", Font.BOLD, 16));
        step3.setBounds(30, 100, 640, 25);
        instructionsPanel.add(step3);
        
        JLabel step4 = new JLabel("4. Find all diamonds to win and progress to next level");
        step4.setForeground(new Color(200, 200, 255));
        step4.setFont(new Font("Arial", Font.BOLD, 16));
        step4.setBounds(30, 140, 640, 25);
        instructionsPanel.add(step4);
        
        JLabel step5 = new JLabel("5. Avoid bombs - they will end your game");
        step5.setForeground(new Color(200, 200, 255));
        step5.setFont(new Font("Arial", Font.BOLD, 16));
        step5.setBounds(30, 180, 640, 25);
        instructionsPanel.add(step5);
        
        JLabel step6 = new JLabel("6. Use memory and strategy to avoid bombs");
        step6.setForeground(new Color(200, 200, 255));
        step6.setFont(new Font("Arial", Font.BOLD, 16));
        step6.setBounds(30, 220, 640, 25);
        instructionsPanel.add(step6);
        
        JLabel step7 = new JLabel("7. Progress through increasingly difficult levels");
        step7.setForeground(new Color(200, 200, 255));
        step7.setFont(new Font("Arial", Font.BOLD, 16));
        step7.setBounds(30, 260, 640, 25);
        instructionsPanel.add(step7);
        
        JLabel step8 = new JLabel("8. Try to reach the highest level possible");
        step8.setForeground(new Color(200, 200, 255));
        step8.setFont(new Font("Arial", Font.BOLD, 16));
        step8.setBounds(30, 300, 640, 25);
        instructionsPanel.add(step8);
        
        // Add visual examples
        JPanel examplePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw diamond example
                g2d.setColor(new Color(0, 191, 255));
                int[] xPoints = {40, 70, 40, 10};
                int[] yPoints = {10, 40, 70, 40};
                g2d.fillPolygon(xPoints, yPoints, 4);
                g2d.setColor(Color.WHITE);
                g2d.drawPolygon(xPoints, yPoints, 4);
                
                g2d.setColor(Color.WHITE);
                g2d.drawString("Diamond = Good", 80, 45);
                
                // Draw bomb example
                g2d.setColor(Color.BLACK);
                g2d.fillOval(250, 15, 40, 40);
                g2d.setColor(Color.RED);
                g2d.fillRect(263, 5, 14, 15);
                
                g2d.setColor(Color.WHITE);
                g2d.drawString("Bomb = Game Over", 300, 45);
                
                // Draw question mark
                g2d.setColor(new Color(255, 215, 0));
                g2d.setFont(new Font("Arial", Font.BOLD, 30));
                g2d.drawString("?", 460, 45);
                
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.PLAIN, 14));
                g2d.drawString("Hidden Box", 500, 45);
            }
        };
        examplePanel.setOpaque(false);
        examplePanel.setBounds(30, 340, 640, 70);
        instructionsPanel.add(examplePanel);
        
        JLabel tipLabel = new JLabel("Tip: Remember bomb positions when you lose to improve next time!");
        tipLabel.setForeground(new Color(255, 215, 0));
        tipLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tipLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
        tipLabel.setBounds(0, 550, 800, 20);
        contentPane.add(tipLabel);
    }
}