package mindblowgame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * BaseLevel provides shared UI and gameplay logic for levels.
 * Child classes only specify level title, grid size, gem/bomb counts, and next level supplier.
 */
public abstract class BaseLevel extends JFrame {
    protected final int rows;
    protected final int cols;
    protected final int totalGems;
    protected final int totalBombs;
    protected final Supplier<JFrame> nextLevelSupplier; // can be null for the last level

    private JPanel root;
    private JPanel gridPanel;
    private JLabel statusLabel;
    private JLabel levelLabel;
    private JButton restartButton;
    private JButton homeButton;

    private int foundGems = 0;
    private boolean gameOver = false;

    private CellButton[][] cells;

    // Emojis used for visuals
    private static final String ICON_UNKNOWN = "❓";
    private static final String ICON_GEM = "💎";
    private static final String ICON_BOMB = "💣";

    protected BaseLevel(String title,
                        int rows,
                        int cols,
                        int totalGems,
                        int totalBombs,
                        Supplier<JFrame> nextLevelSupplier) {
        super(title);
        this.rows = rows;
        this.cols = cols;
        this.totalGems = totalGems;
        this.totalBombs = totalBombs;
        this.nextLevelSupplier = nextLevelSupplier;

        initLookAndFeel();
        initFrameBasics();
        buildUI();
        populateBoard();
        updateStatus();
    }

    private void initLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
    }

    private void initFrameBasics() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        try {
            // Try to set icon if exists in classpath; otherwise ignore
            ImageIcon icon = new ImageIcon(getClass().getResource("/icon.png"));
            setIconImage(icon.getImage());
        } catch (Exception ignored) {}
    }

    private void buildUI() {
        root = new JPanel(new BorderLayout());
        root.setBackground(new Color(24, 24, 24));
        root.setBorder(new EmptyBorder(12, 12, 12, 12));

        // Top toolbar
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);

        levelLabel = new JLabel(getTitle(), SwingConstants.LEFT);
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setFont(levelFont());
        topBar.add(levelLabel, BorderLayout.WEST);

        JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 4));
        rightButtons.setOpaque(false);
        restartButton = createToolButton("↻ Restart", new Color(70, 130, 180));
        homeButton = createToolButton("⌂ Home", new Color(100, 149, 237));
        rightButtons.add(restartButton);
        rightButtons.add(homeButton);

        topBar.add(rightButtons, BorderLayout.EAST);

        // Status bar
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setOpaque(false);
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(new Color(200, 200, 200));
        statusLabel.setFont(statusFont());
        statusBar.add(statusLabel, BorderLayout.CENTER);

        // Grid
        gridPanel = new JPanel(new GridLayout(rows, cols, 8, 8));
        gridPanel.setOpaque(false);
        gridPanel.setBorder(new EmptyBorder(16, 0, 0, 0));

        // Root assembly
        root.add(topBar, BorderLayout.NORTH);
        root.add(gridPanel, BorderLayout.CENTER);
        root.add(statusBar, BorderLayout.SOUTH);

        setContentPane(root);

        // Actions
        restartButton.addActionListener(e -> restartLevel());
        homeButton.addActionListener(e -> goHome());
    }

    private JButton createToolButton(String text, Color baseColor) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(baseColor);
        btn.setFont(uiFont());
        btn.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        return btn;
    }

    private void populateBoard() {
        // prepare list of cell types
        List<CellType> pool = new ArrayList<>();
        for (int i = 0; i < totalGems; i++) pool.add(CellType.GEM);
        for (int i = 0; i < totalBombs; i++) pool.add(CellType.BOMB);
        int totalCells = rows * cols;
        while (pool.size() < totalCells) pool.add(CellType.EMPTY);
        Collections.shuffle(pool);

        cells = new CellButton[rows][cols];
        gridPanel.removeAll();

        int k = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                CellType type = pool.get(k++);
                CellButton btn = new CellButton(type);
                styleCell(btn);
                gridPanel.add(btn);
                cells[r][c] = btn;
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void styleCell(CellButton btn) {
        btn.setText(ICON_UNKNOWN);
        btn.setFont(cellFont());
        btn.setFocusPainted(false);
        btn.setForeground(new Color(255, 215, 0)); // gold
        btn.setBackground(new Color(30, 30, 30));
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60)));
        btn.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                onCellClicked(btn);
            }
        });
    }

    private void onCellClicked(CellButton btn) {
        if (gameOver || btn.revealed) return;
        btn.revealed = true;
        switch (btn.type) {
            case GEM:
                btn.setText(ICON_GEM);
                btn.setForeground(new Color(0, 191, 255));
                btn.setBackground(Color.WHITE);
                foundGems++;
                updateStatus();
                if (foundGems >= totalGems) onWin();
                break;
            case BOMB:
                btn.setText(ICON_BOMB);
                btn.setForeground(Color.BLACK);
                btn.setBackground(Color.WHITE);
                onGameOver();
                break;
            case EMPTY:
                btn.setText(" ");
                btn.setForeground(new Color(200, 200, 200));
                btn.setBackground(new Color(45, 45, 45));
                break;
        }
    }

    private void onGameOver() {
        gameOver = true;
        revealAll();
        JOptionPane.showMessageDialog(this, "GAME OVER", "MindBlow", JOptionPane.ERROR_MESSAGE);
        int choice = JOptionPane.showConfirmDialog(this, "Try again?", "MindBlow", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            restartLevel();
        } else {
            goHome();
        }
    }

    private void onWin() {
        gameOver = true;
        revealAllGemsOnly();
        int choice;
        if (nextLevelSupplier != null) {
            JOptionPane.showMessageDialog(this, "Congratulations! You Win!", "MindBlow", JOptionPane.INFORMATION_MESSAGE);
            choice = JOptionPane.showConfirmDialog(this, "Proceed to next level?", "MindBlow", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                JFrame next = nextLevelSupplier.get();
                next.setVisible(true);
                dispose();
            } else {
                goHome();
            }
        } else {
            JOptionPane.showMessageDialog(this, "You finished the last level!", "MindBlow", JOptionPane.INFORMATION_MESSAGE);
            goHome();
        }
    }

    private void updateStatus() {
        statusLabel.setText("Gems found: " + foundGems + " / " + totalGems + "    |    Bombs: " + totalBombs);
    }

    private void restartLevel() {
        foundGems = 0;
        gameOver = false;
        populateBoard();
        updateStatus();
    }

    private void goHome() {
        try {
            JFrame h = new home();
            h.setVisible(true);
        } catch (Throwable t) {
            // Fallback: just close
        }
        dispose();
    }

    private void revealAll() {
        forEachCell(btn -> {
            if (!btn.revealed) {
                btn.revealed = true;
                switch (btn.type) {
                    case GEM:
                        btn.setText(ICON_GEM);
                        btn.setForeground(new Color(0, 191, 255));
                        btn.setBackground(Color.WHITE);
                        break;
                    case BOMB:
                        btn.setText(ICON_BOMB);
                        btn.setForeground(Color.BLACK);
                        btn.setBackground(Color.WHITE);
                        break;
                    case EMPTY:
                        btn.setText(" ");
                        btn.setForeground(new Color(200, 200, 200));
                        btn.setBackground(new Color(45, 45, 45));
                        break;
                }
            }
        });
    }

    private void revealAllGemsOnly() {
        forEachCell(btn -> {
            if (!btn.revealed && btn.type == CellType.GEM) {
                btn.revealed = true;
                btn.setText(ICON_GEM);
                btn.setForeground(new Color(0, 191, 255));
                btn.setBackground(Color.WHITE);
            }
        });
    }

    private void forEachCell(CellVisitor v) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                v.visit(cells[r][c]);
            }
        }
    }

    private static Font levelFont() {
        return new Font("Segoe UI", Font.BOLD, 22);
    }

    private static Font uiFont() {
        return new Font("Segoe UI", Font.PLAIN, 14);
    }

    private static Font statusFont() {
        return new Font("Segoe UI", Font.PLAIN, 16);
    }

    private static Font cellFont() {
        return new Font("Segoe UI Emoji", Font.BOLD, 26);
    }

    enum CellType { GEM, BOMB, EMPTY }

    static class CellButton extends JButton {
        CellType type;
        boolean revealed = false;
        CellButton(CellType type) { this.type = type; }
    }

    interface CellVisitor { void visit(CellButton btn); }
}