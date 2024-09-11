package org.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SignInPanel extends JPanel {
    private final JButton signUpButton;
    private final JTextField nickname;
    private JTextField teamName;
    private JPasswordField teamPassword;
    private final JCheckBox showPasswordCheckBox;
    private final JLabel welcomeLabel;
    private JButton enterButton;
    private int width;
    private int height;

    public SignInPanel(int width, int height) {
        this.width = width;
        this.height = height;
        this.setBackground(Color.cyan);
        this.setSize(this.width, this.height);
        this.setLayout(null);
        this.setVisible(true);

        this.welcomeLabel = new JLabel("Sign in: ");
        this.welcomeLabel.setFont(new Font("Arial", Font.ITALIC , 40));
        this.welcomeLabel.setBounds((width - 150) / 2, 150, 150, 70);

        this.add(welcomeLabel);

        this.nickname = new JTextField();
        this.nickname.setFont(new Font("Arial", Font.ITALIC, 25));
        this.nickname.setBounds((width - 600) / 2, welcomeLabel.getY() + 100, 600,70);
        this.nickname.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(null, "Your nickname:", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.RIGHT),
                BorderFactory.createEmptyBorder(5, 5, 5,5)
        ));
        this.nickname.addActionListener(e -> {
            teamName.requestFocus();
        });

        this.add(nickname);

        this.teamName = new JTextField();
        this.teamName.setFont(new Font("Arial", Font.ITALIC, 25));
        this.teamName.setBounds((width - 600) / 2, nickname.getY() + 100, 600,70);
        this.teamName.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(null, "Team name:", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.RIGHT),
                BorderFactory.createEmptyBorder(5, 5, 5,5)
        ));
        this.teamName.addActionListener(e -> {
            teamPassword.requestFocus();
        });

        this.add(teamName);

        this.teamPassword = new JPasswordField();
        this.teamPassword.setFont(new Font("Arial", Font.ITALIC, 25));
        this.teamPassword.setBounds((width - 600) / 2, teamName.getY() + 100, 600,70);
        this.teamPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(null, "Team password:", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.RIGHT),
                BorderFactory.createEmptyBorder(5, 5, 5,5)
        ));
        this.teamPassword.addActionListener(e -> {
            enterButton.doClick();
        });

        this.add(teamPassword);

        this.showPasswordCheckBox = new JCheckBox("Show Password");
        this.showPasswordCheckBox.setBounds((width - 600) / 2, teamPassword.getY() + 80, 150, 30);
        this.showPasswordCheckBox.setBackground(Color.cyan);
        this.showPasswordCheckBox.setFocusPainted(false);
        this.showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                teamPassword.setEchoChar((char) 0);
            } else {
                teamPassword.setEchoChar('\u2022');
            }
        });
        this.add(showPasswordCheckBox);

        this.enterButton = new JButton("ENTER");
        this.enterButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.enterButton.setBounds((width - 140) / 2,600,140,70);
        this.enterButton.setFocusPainted(false);
        this.enterButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.add(enterButton);

        this.signUpButton = new JButton("sign up");
        this.signUpButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.signUpButton.setBounds(teamPassword.getX() + teamPassword.getWidth() - 115, showPasswordCheckBox.getY(),115,30);
        this.signUpButton.setFocusPainted(false);
        this.signUpButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.add(signUpButton);
    }

    public JButton getEnterButton() {
        return enterButton;
    }

    public JButton getSignUpButton() {
        return signUpButton;
    }

    public boolean hasEmptyField() {
        return this.nickname.getText().isEmpty() || this.teamName.getText().isEmpty() || this.teamPassword.getText().isEmpty();
    }
    public void restartPanel() {
        this.nickname.setText(null);
        this.teamName.setText(null);
        this.teamPassword.setText(null);
        this.showPasswordCheckBox.setSelected(false);
        this.teamPassword.setEchoChar('\u2022');

    }
    public String getTeamName() {
        return teamName.getText();
    }
    public String getPassword(){
        return teamPassword.getText();
    }
}
