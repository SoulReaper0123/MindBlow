package mindblowgame;
import java.awt.EventQueue;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JToggleButton;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;

public class Level1 extends JFrame {

    private JPanel contentPane;
    private int numOClicked = 0;
    final int totalOButtons = 15;
    private JLabel progressLabel;
    private JButton[][] buttons = new JButton[5][5];
    private boolean[][] isBomb = new boolean[5][5];
    private boolean[][] isRevealed = new boolean[5][5];
    private ImageIcon questionIcon, diamondIcon, bombIcon, statsDiamondIcon, statsBombIcon;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Level1 frame = new Level1();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Level1() {
        setTitle("Mind Blow Game - Level 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 700);
        setLocationRelativeTo(null);
        
        // Load icons (using text as fallback if images not available)
        try {
            // Try to create simple icons
            diamondIcon = createDiamondIcon();
            bombIcon = createBombIcon();
            questionIcon = createQuestionIcon();
            statsDiamondIcon = createStatsDiamondIcon();
            statsBombIcon = createStatsBombIcon();
        } catch (Exception e) {
            // Fallback to text if icons can't be created
            System.out.println("Icons not available, using text fallback");
        }
        
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
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel gamePanel = new JPanel() {
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
        gamePanel.setOpaque(false);
        gamePanel.setBounds(200, 150, 500, 400);
        contentPane.add(gamePanel);
        gamePanel.setLayout(null);
        
        Font buttonFont = new Font("Arial Rounded MT Bold", Font.BOLD, 20);
        Color buttonForeground = new Color(255, 215, 0);
        Color buttonBackground = new Color(30, 30, 50);
        Color diamondColor = new Color(0, 191, 255);
        Color bombColor = new Color(220, 50, 50);
        
        // Initialize the grid
        initializeGameGrid(gamePanel, buttonFont, buttonForeground, buttonBackground, diamondColor, bombColor);
        
        JLabel lblNewLabel = new JLabel("Level 1");
        lblNewLabel.setForeground(new Color(255, 215, 0));
        lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 42));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(200, 30, 500, 60);
        contentPane.add(lblNewLabel);
        
        progressLabel = new JLabel("Progress: 0/15 Diamonds");
        progressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        progressLabel.setForeground(new Color(200, 200, 255));
        progressLabel.setFont(new Font("Arial", Font.BOLD, 20));
        progressLabel.setBounds(200, 90, 500, 30);
        contentPane.add(progressLabel);
        
        JPanel leftPanel = createStatsPanel("Diamonds", "23", statsDiamondIcon, new Color(0, 191, 255));
        leftPanel.setBounds(30, 150, 150, 250);
        contentPane.add(leftPanel);
        
        JPanel rightPanel = createStatsPanel("Bombs", "2", statsBombIcon, new Color(220, 50, 50));
        rightPanel.setBounds(720, 150, 150, 250);
        contentPane.add(rightPanel);
        
        JButton back = new JButton("← Back");
        back.setFocusPainted(false);
        back.setBackground(new Color(70, 70, 120));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.setBorderPainted(false);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Yes", "No"};
                int choice = JOptionPane.showOptionDialog(null, "Do you want to exit the game?", "Exit Game",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (choice == JOptionPane.YES_OPTION) {
                    home level = new home();
                    level.setVisible(true);
                    dispose();
                }
            }
        });
        back.setBounds(30, 30, 100, 35);
        contentPane.add(back);
        
        JLabel instructionLabel = new JLabel("Find 15 Diamonds to Win! Avoid the Bombs!");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionLabel.setForeground(new Color(200, 200, 255));
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        instructionLabel.setBounds(200, 570, 500, 40);
        contentPane.add(instructionLabel);
    }
    
    private ImageIcon createDiamondIcon() {
        // Create a simple diamond icon programmatically
        return new ImageIcon() {
            @Override
            public synchronized void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 191, 255));
                int[] xPoints = {x + 15, x + 30, x + 15, x};
                int[] yPoints = {y, y + 15, y + 30, y + 15};
                g2d.fillPolygon(xPoints, yPoints, 4);
                g2d.setColor(Color.WHITE);
                g2d.drawPolygon(xPoints, yPoints, 4);
            }
            
            @Override
            public int getIconWidth() {
                return 30;
            }
            
            @Override
            public int getIconHeight() {
                return 30;
            }
        };
    }
    
    private ImageIcon createStatsDiamondIcon() {
        // Create a larger diamond icon for stats panel
        return new ImageIcon() {
            @Override
            public synchronized void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 191, 255));
                int[] xPoints = {x + 30, x + 60, x + 30, x};
                int[] yPoints = {y, y + 30, y + 60, y + 30};
                g2d.fillPolygon(xPoints, yPoints, 4);
                g2d.setColor(Color.WHITE);
                g2d.drawPolygon(xPoints, yPoints, 4);
            }
            
            @Override
            public int getIconWidth() {
                return 60;
            }
            
            @Override
            public int getIconHeight() {
                return 60;
            }
        };
    }
    
    private ImageIcon createBombIcon() {
        // Create a simple bomb icon programmatically
        return new ImageIcon() {
            @Override
            public synchronized void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.BLACK);
                g2d.fillOval(x + 5, y + 5, 20, 20);
                g2d.setColor(Color.RED);
                g2d.fillRect(x + 13, y, 4, 8);
            }
            
            @Override
            public int getIconWidth() {
                return 30;
            }
            
            @Override
            public int getIconHeight() {
                return 30;
            }
        };
    }
    
    private ImageIcon createStatsBombIcon() {
        // Create a larger bomb icon for stats panel
        return new ImageIcon() {
            @Override
            public synchronized void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.BLACK);
                g2d.fillOval(x + 10, y + 10, 40, 40);
                g2d.setColor(Color.RED);
                g2d.fillRect(x + 25, y, 8, 15);
            }
            
            @Override
            public int getIconWidth() {
                return 60;
            }
            
            @Override
            public int getIconHeight() {
                return 60;
            }
        };
    }
    
    private ImageIcon createQuestionIcon() {
        // Create a simple question mark icon programmatically
        return new ImageIcon() {
            @Override
            public synchronized void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(255, 215, 0));
                g2d.setFont(new Font("Arial", Font.BOLD, 24));
                g2d.drawString("?", x + 10, y + 22);
            }
            
            @Override
            public int getIconWidth() {
                return 30;
            }
            
            @Override
            public int getIconHeight() {
                return 30;
            }
        };
    }
    
    private void initializeGameGrid(JPanel gamePanel, Font buttonFont, Color buttonForeground, 
                                  Color buttonBackground, Color diamondColor, Color bombColor) {
        // Create all buttons first with question marks
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                JButton button = new JButton();
                if (questionIcon != null) {
                    button.setIcon(questionIcon);
                } else {
                    button.setText("?");
                    button.setFont(buttonFont);
                    button.setForeground(buttonForeground);
                }
                button.setBackground(buttonBackground);
                button.setFocusPainted(false);
                button.setBorderPainted(false);
                button.setOpaque(true);
                button.setBorder(new javax.swing.border.LineBorder(new Color(100, 100, 150), 2, true));
                button.setBounds(10 + col * 96, 10 + row * 76, 80, 70);
                
                buttons[row][col] = button;
                gamePanel.add(button);
            }
        }
        
        // Create a list of all positions
        List<int[]> positions = new ArrayList<>();
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                positions.add(new int[]{row, col});
            }
        }
        
        // Shuffle the positions
        Collections.shuffle(positions);
        
        // Set 2 bombs
        for (int i = 0; i < 2; i++) {
            int[] pos = positions.get(i);
            isBomb[pos[0]][pos[1]] = true;
        }
        
        // Set up action listeners for all buttons
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                final int r = row;
                final int c = col;
                
                buttons[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (isRevealed[r][c]) return;
                        
                        isRevealed[r][c] = true;
                        
                        if (isBomb[r][c]) {
                            if (bombIcon != null) {
                                buttons[r][c].setIcon(bombIcon);
                                buttons[r][c].setText("");
                            } else {
                                buttons[r][c].setText("BOMB");
                            }
                            buttons[r][c].setForeground(Color.WHITE);
                            buttons[r][c].setBackground(bombColor);
                            showGameOverDialog();
                        } else {
                            if (diamondIcon != null) {
                                buttons[r][c].setIcon(diamondIcon);
                                buttons[r][c].setText("");
                            } else {
                                buttons[r][c].setText("DIAMOND");
                            }
                            buttons[r][c].setForeground(Color.WHITE);
                            buttons[r][c].setBackground(diamondColor);
                            numOClicked++;
                            progressLabel.setText("Progress: " + numOClicked + "/15 Diamonds");
                            checkWinCondition();
                        }
                    }
                });
            }
        }
        
        // Shuffle button positions
        List<Rectangle> setBoundsList = new ArrayList<>();
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                setBoundsList.add(buttons[row][col].getBounds());
            }
        }
        Collections.shuffle(setBoundsList);
        
        int index = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                Rectangle newBounds = setBoundsList.get(index);
                buttons[row][col].setBounds(newBounds);
                index++;
            }
        }
    }
    
    private JPanel createStatsPanel(String title, String count, ImageIcon icon, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setOpaque(false);
        panel.setBackground(new Color(40, 40, 70, 200));
        panel.setBorder(new javax.swing.border.LineBorder(new Color(80, 80, 120), 2, true));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
        titleLabel.setBounds(0, 20, 150, 30);
        panel.add(titleLabel);
        
        JLabel countLabel = new JLabel(count);
        countLabel.setForeground(Color.WHITE);
        countLabel.setHorizontalAlignment(SwingConstants.CENTER);
        countLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32));
        countLabel.setBounds(0, 150, 150, 40);
        panel.add(countLabel);
        
        if (icon != null) {
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
            iconLabel.setBounds(0, 60, 150, 90);
            panel.add(iconLabel);
        } else {
            // Fallback to text if icon is not available
            JLabel iconLabel = new JLabel(title.equals("Diamonds") ? "♦" : "☢");
            iconLabel.setForeground(color);
            iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
            iconLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 50));
            iconLabel.setBounds(0, 60, 150, 90);
            panel.add(iconLabel);
        }
        
        return panel;
    }
    
    private void checkWinCondition() {
        if (numOClicked == totalOButtons) {
            JOptionPane.showMessageDialog(null, "Congratulations! You Win!");
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to proceed to the next level?", "Level Complete", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                Level2 level = new Level2();
                level.setVisible(true);
                dispose();
            } else if (choice == JOptionPane.NO_OPTION) {
                home level = new home();
                level.setVisible(true);
                dispose();
            }	
        }
    }
    
    private void showGameOverDialog() {
        // Reveal all bombs
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (isBomb[row][col]) {
                    if (bombIcon != null) {
                        buttons[row][col].setIcon(bombIcon);
                        buttons[row][col].setText("");
                    } else {
                        buttons[row][col].setText("BOMB");
                    }
                    buttons[row][col].setForeground(Color.WHITE);
                    buttons[row][col].setBackground(new Color(220, 50, 50));
                }
            }
        }
        
        JOptionPane.showMessageDialog(null, "GAME OVER");
        JOptionPane.showMessageDialog(null, "Your Highest Level: 1");
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to try again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            Level1 level = new Level1();
            level.setVisible(true);
            dispose();
        } else if (choice == JOptionPane.NO_OPTION) {
            home level = new home();
            level.setVisible(true);
            dispose();
        }
    }
}