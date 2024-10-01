package resources;

import org.example.ButtonsPanel;
import org.example.HintsPanel;

import javax.swing.*;

public class MainRiddlePanel extends JPanel {
    private final int panelWidth;
    private final int panelHeight;
    private HintsPanel hintsPanel;
    private ButtonsPanel buttonsPanel;
    private int x = 0;
    private int y = 0;

    public HintsPanel getHintsPanel() {
        return hintsPanel;
    }

    public ButtonsPanel getButtonsPanel() {
        return buttonsPanel;
    }

    public MainRiddlePanel() {
        this.buttonsPanel = new ButtonsPanel();
        this.buttonsPanel.setVisible(true);
        this.add(buttonsPanel);

        this.hintsPanel = new HintsPanel();
        this.hintsPanel.setVisible(false);
        this.add(hintsPanel);

        this.panelWidth = this.buttonsPanel.getWidth();
        this.panelHeight = this.buttonsPanel.getHeight();


        this.setSize(panelWidth, panelHeight);
        this.setLayout(null);
        this.setVisible(false);

        this.buttonsPanel.getHintsButton().addActionListener(e -> {
            this.buttonsPanel.setVisible(false);
            this.hintsPanel.setBounds(this.x, this.y, this.hintsPanel.getWidth(), this.hintsPanel.getHeight()); // הגדרת המיקום והגודל של hintsPanel
            this.hintsPanel.setVisible(true);
            this.hintsPanel.setFocusable(true);
            this.hintsPanel.requestFocus();
            this.hintsPanel.requestFocusInWindow();
        });
        this.buttonsPanel.getReturnButton().addActionListener(e -> {
            this.buttonsPanel.setVisible(true);
//            this.hintsPanel.setVisible(false);
            this.setVisible(false);
        });

    }
}
