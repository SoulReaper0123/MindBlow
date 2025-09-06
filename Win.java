package mindblowgame;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Win extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Win frame = new Win();
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
    public Win() {
        setTitle("Victory - Mind Blow Game");
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
        
        JButton backButton = new JButton("← Back to Home");
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
        backButton.setBounds(30, 20, 150, 35);
        contentPane.add(backButton);
        
        JLabel congratsLabel = new JLabel("CONGRATULATIONS!");
        congratsLabel.setForeground(new Color(255, 215, 0));
        congratsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        congratsLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 42));
        congratsLabel.setBounds(0, 80, 800, 60);
        contentPane.add(congratsLabel);
        
        JLabel finishedLabel = new JLabel("You Finished The Game!");
        finishedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        finishedLabel.setForeground(new Color(0, 191, 255));
        finishedLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32));
        finishedLabel.setBounds(0, 140, 800, 50);
        contentPane.add(finishedLabel);
        
        JLabel thanksLabel = new JLabel("Thank You For Playing!");
        thanksLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thanksLabel.setForeground(new Color(200, 200, 255));
        thanksLabel.setFont(new Font("Arial", Font.BOLD, 24));
        thanksLabel.setBounds(0, 200, 800, 40);
        contentPane.add(thanksLabel);
        
        JPanel trophyPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw trophy
                g2d.setColor(new Color(255, 215, 0)); // Gold color
                
                // Trophy base
                g2d.fillRoundRect(getWidth()/2 - 40, getHeight()/2 - 20, 80, 20, 10, 10);
                
                // Trophy stem
                g2d.fillRect(getWidth()/2 - 10, getHeight()/2 - 100, 20, 80);
                
                // Trophy cup
                int[] xPoints = {getWidth()/2 - 50, getWidth()/2 + 50, getWidth()/2 + 30, getWidth()/2 - 30};
                int[] yPoints = {getHeight()/2 - 100, getHeight()/2 - 100, getHeight()/2 - 140, getHeight()/2 - 140};
                g2d.fillPolygon(xPoints, yPoints, 4);
            }
        };
        trophyPanel.setOpaque(false);
        trophyPanel.setBounds(300, 250, 200, 200);
        contentPane.add(trophyPanel);
        
    }
}