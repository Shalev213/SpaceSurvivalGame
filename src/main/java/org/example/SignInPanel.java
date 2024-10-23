package org.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SignInPanel extends JPanel {
    private final JButton signUpButton;
    private final JTextField nickname;
    private final ImageIcon signInBackground;
    private JTextField teamName;
    private JPasswordField teamPassword;
    private final JCheckBox showPasswordCheckBox;
    private final JLabel welcomeLabel;
    private JButton loginButton;
    private int width;
    private int height;
    private int labelWidth = 190;



    public SignInPanel(int width, int height) {
        this.signInBackground = new ImageIcon("src/main/java/resources/signInBackground.png");
        this.width = width;
        this.height = height;
//        this.setBackground(Color.cyan);
        this.setSize(this.width, this.height);
        this.setLayout(null);
        this.setVisible(true);

        this.welcomeLabel = new JLabel("Sign in: ");
        this.welcomeLabel.setFont(new Font("Arial", Font.ITALIC , 50));
        this.welcomeLabel.setBounds((width - labelWidth) / 2, 150, labelWidth, 70);
        this.welcomeLabel.setForeground(Color.white);

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
            loginButton.doClick();
        });

        this.add(teamPassword);

        this.showPasswordCheckBox = new JCheckBox("Show Password");
        this.showPasswordCheckBox.setFont(new Font("Arial", Font.BOLD, 14));
        this.showPasswordCheckBox.setBounds((width - 600) / 2, teamPassword.getY() + 80, 140, 25);
        this.showPasswordCheckBox.setForeground(Color.white);
        this.showPasswordCheckBox.setBackground(new Color(0,0,0));
        this.showPasswordCheckBox.setFocusPainted(false);
        this.showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                teamPassword.setEchoChar((char) 0);
            } else {
                teamPassword.setEchoChar('\u2022');
            }
        });
        this.add(showPasswordCheckBox);

        this.loginButton = new JButton("Login");
        this.loginButton.setFont(new Font("Arial", Font.BOLD, 35));
        this.loginButton.setBounds((width - 140) / 2,600,140,70);
        this.loginButton.setFocusPainted(false);
        this.loginButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.add(loginButton);

        this.signUpButton = new JButton("Sign up");
        this.signUpButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.signUpButton.setBounds(teamPassword.getX() + teamPassword.getWidth() - 115, showPasswordCheckBox.getY(),115,35);
        this.signUpButton.setFocusPainted(false);
        this.signUpButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.add(signUpButton);
    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (signInBackground != null){
            this.signInBackground.paintIcon(null, graphics, 0, 0);
        }

    }

    public JButton getLoginButton() {
        return loginButton;
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
