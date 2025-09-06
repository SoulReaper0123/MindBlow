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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class home extends JFrame {

    private JPanel contentPane;
    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    home frame = new home();
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
    public home() {
        setTitle("Mind Blow Game");
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
        
        JLabel title = new JLabel("MIND BLOW");
        title.setForeground(new Color(255, 215, 0));
        title.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 60));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, 80, 800, 80);
        contentPane.add(title);
        
        // Create a diamond icon programmatically
        JLabel diamondIcon = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 191, 255));
                int[] xPoints = {getWidth()/2, getWidth()/2 + 25, getWidth()/2, getWidth()/2 - 25};
                int[] yPoints = {getHeight()/2 - 25, getHeight()/2, getHeight()/2 + 25, getHeight()/2};
                g2d.fillPolygon(xPoints, yPoints, 4);
                g2d.setColor(Color.WHITE);
                g2d.drawPolygon(xPoints, yPoints, 4);
            }
        };
        diamondIcon.setBounds(370, 170, 60, 60);
        contentPane.add(diamondIcon);
        
        // Create PLAY button with rounded corners
        RoundedButton play = new RoundedButton("PLAY");
        play.setFocusPainted(false);
        play.setBackground(new Color(70, 70, 120));
        play.setForeground(Color.WHITE);
        play.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30));
        play.setBorderPainted(false);
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Level1 level1 = new Level1();
                level1.setVisible(true);
                dispose();
            }
        });
        play.setBounds(300, 250, 200, 60);
        contentPane.add(play);
        
        // Create HOW TO PLAY button with rounded corners
        RoundedButton howtoplay = new RoundedButton("HOW TO PLAY");
        howtoplay.setFocusPainted(false);
        howtoplay.setBackground(new Color(70, 70, 120));
        howtoplay.setForeground(Color.WHITE);
        howtoplay.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        howtoplay.setBorderPainted(false);
        howtoplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QuestionMark howToPlay = new QuestionMark();
                howToPlay.setVisible(true);
                dispose();
            }
        });
        howtoplay.setBounds(300, 330, 200, 50);
        contentPane.add(howtoplay);
        
        // Create ABOUT button with rounded corners
        RoundedButton about = new RoundedButton("ABOUT");
        about.setFocusPainted(false);
        about.setBackground(new Color(70, 70, 120));
        about.setForeground(Color.WHITE);
        about.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        about.setBorderPainted(false);
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Information information = new Information();
                information.setVisible(true);
                dispose();
            }
        });
        about.setBounds(300, 400, 200, 50);
        contentPane.add(about);
        
        JLabel footerLabel = new JLabel("Find Diamonds • Avoid Bombs • Complete the Levels!");
        footerLabel.setForeground(new Color(200, 200, 255));
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        footerLabel.setBounds(0, 520, 800, 30);
        contentPane.add(footerLabel);
    }
    
    // Custom rounded button class
    class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Draw button background
            if (getModel().isPressed()) {
                g2.setColor(getBackground().darker());
            } else if (getModel().isRollover()) {
                g2.setColor(getBackground().brighter());
            } else {
                g2.setColor(getBackground());
            }
            
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            
            // Draw button border
            g2.setColor(new Color(100, 100, 150));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
            
            super.paintComponent(g);
        }
    }
}