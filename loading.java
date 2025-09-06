package mindblowgame;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class loading extends JFrame {

    private JPanel contentPane;
    static JProgressBar progressBar;
    static JLabel label1;
    private JLabel label;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        int x;
        loading bar = new loading();
        bar.setVisible(true);
        try {
            for (x = 0; x <= 100; x++) {
                loading.progressBar.setValue(x);
                Thread.sleep(75);
                loading.label1.setText(Integer.toString(x) + "%");
                
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }            
            home frame = new home();
            frame.setVisible(true);
            bar.dispose();
    }
    
    /**
     * Create the frame.
     */
    public loading() {
        setTitle("MindBlow");
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
        
        progressBar = new JProgressBar();
        progressBar.setBackground(new Color(30, 30, 50));
        progressBar.setForeground(new Color(0, 191, 255));
        progressBar.setBounds(50, 501, 700, 20);
        progressBar.setBorderPainted(false);
        contentPane.add(progressBar);
        
        label1 = new JLabel("0%");
        label1.setForeground(new Color(255, 215, 0));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 28));
        label1.setBounds(0, 455, 800, 36);
        contentPane.add(label1);
        
        JLabel titleLabel = new JLabel("Mind Blow Game");
        titleLabel.setForeground(new Color(255, 215, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 42));
        titleLabel.setBounds(0, 150, 800, 60);
        contentPane.add(titleLabel);
        
        JLabel loadingLabel = new JLabel("Loading...");
        loadingLabel.setForeground(new Color(200, 200, 255));
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loadingLabel.setBounds(0, 210, 800, 30);
        contentPane.add(loadingLabel);
        
        // Create a simple diamond icon programmatically
        JLabel diamondIcon = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 191, 255));
                int[] xPoints = {getWidth()/2, getWidth()/2 + 30, getWidth()/2, getWidth()/2 - 30};
                int[] yPoints = {getHeight()/2 - 30, getHeight()/2, getHeight()/2 + 30, getHeight()/2};
                g2d.fillPolygon(xPoints, yPoints, 4);
                g2d.setColor(Color.WHITE);
                g2d.drawPolygon(xPoints, yPoints, 4);
            }
        };
        diamondIcon.setBounds(350, 280, 100, 100);
        contentPane.add(diamondIcon);
    }
}