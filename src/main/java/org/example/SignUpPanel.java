package org.example;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;

public class SignUpPanel extends JPanel {
    private final JButton returnButton;
//    private JTextField nicknamePlayer1;
//    private JTextField nicknamePlayer2;
    private JTextField teamName;
    private JPasswordField teamPassword;
    private JPasswordField teamPasswordVerification;
    private JCheckBox showPassword1CheckBox;
    private JCheckBox showPassword2CheckBox;
    private JLabel welcomeLabel;
    private JButton registerButton;
    private int width;
    private int height;
    private final ImageIcon signUpBackground;
    private int heightTextFields = 65;
    private int space = 40;
    private int labelWidth = 200;
    private int fieldWidth = 450;



    public SignUpPanel(int width, int height) {
        this.signUpBackground = new ImageIcon("src/main/java/resources/signUpBackground.png");
        this.width = width;
        this.height = height;
//        this.setBackground(Color.yellow);
        this.setSize(this.width, this.height);
        this.setLayout(null);
        this.setVisible(true);

        this.welcomeLabel = new JLabel("Sign up: ");
        this.welcomeLabel.setFont(new Font("Arial", Font.ITALIC , 50));
        this.welcomeLabel.setBounds((width - labelWidth) / 2, 140, labelWidth, 70);
        this.welcomeLabel.setForeground(Color.white);
        this.add(welcomeLabel);

        this.teamName = new JTextField();
        this.teamName.setFont(new Font("Arial", Font.ITALIC, 25));
        this.teamName.setBounds((width - fieldWidth) / 2, welcomeLabel.getY() + heightTextFields + space, fieldWidth,heightTextFields);
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
        this.teamPassword.setBounds((width - fieldWidth) / 2, teamName.getY() + heightTextFields + space, fieldWidth, heightTextFields);
        this.teamPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(null, "Team password:", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.RIGHT),
                BorderFactory.createEmptyBorder(5, 5, 5,5)
        ));
        this.teamPassword.addActionListener(_ -> {
            teamPasswordVerification.requestFocus();
        });

        this.add(teamPassword);
        this.showPassword1CheckBox = new JCheckBox("Show");
        this.showPassword1CheckBox.setBounds(teamPassword.getX() - 70, teamPassword.getY() + 20, 60, 25);
        this.showPassword1CheckBox.setBackground(new Color(0, 0, 60));
        this.showPassword1CheckBox.setForeground(Color.WHITE);
        this.showPassword1CheckBox.setFocusPainted(false);
        this.showPassword1CheckBox.addActionListener(e -> {
            if (showPassword1CheckBox.isSelected()) {
                teamPassword.setEchoChar((char) 0);
            } else {
                teamPassword.setEchoChar('\u2022');
            }
        });
        this.add(showPassword1CheckBox);

        this.teamPasswordVerification = new JPasswordField();
        this.teamPasswordVerification.setFont(new Font("Arial", Font.ITALIC, 25));
        this.teamPasswordVerification.setBounds((width - fieldWidth) / 2, teamPassword.getY() + heightTextFields + space, fieldWidth,heightTextFields);
        this.teamPasswordVerification.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(null, "Re-enter team password:", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.RIGHT),
                BorderFactory.createEmptyBorder(5, 5, 5,5)
        ));
        this.teamPasswordVerification.addActionListener(e -> {
            registerButton.doClick();
        });

        this.add(teamPasswordVerification);

        this.showPassword2CheckBox = new JCheckBox("Show");
        this.showPassword2CheckBox.setBounds(teamPasswordVerification.getX() - 70, teamPasswordVerification.getY() + 20, 60, 25);
        this.showPassword2CheckBox.setBackground(new Color(0, 0, 60));
        this.showPassword2CheckBox.setForeground(Color.WHITE);
        this.showPassword2CheckBox.setFocusPainted(false);
        this.showPassword2CheckBox.addActionListener(e -> {
            if (showPassword2CheckBox.isSelected()) {
                teamPasswordVerification.setEchoChar((char) 0);
            } else {
                teamPasswordVerification.setEchoChar('\u2022');
            }
        });
        this.add(showPassword2CheckBox);

//        this.showPasswordCheckBox = new JCheckBox("Show Password");
//        this.showPasswordCheckBox.setBounds((width - 600) / 2, teamPassword.getY() + 80, 150, heightTextFields);
//        this.showPasswordCheckBox.setBackground(Color.cyan);
//        this.showPasswordCheckBox.setFocusPainted(false);
//        this.showPasswordCheckBox.addActionListener(e -> {
//            if (showPasswordCheckBox.isSelected()) {
//                teamPassword.setEchoChar((char) 0);
//            } else {
//                teamPassword.setEchoChar('\u2022');
//            }
//        });
//        this.add(showPasswordCheckBox);

        this.registerButton = new JButton("Register");
        this.registerButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.registerButton.setBounds((width - 140) / 2,600,140,70);
        this.registerButton.setFocusPainted(false);
        this.registerButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.add(registerButton);

        this.returnButton = new JButton("return");
        this.returnButton.setFont(new Font("Arial", Font.BOLD, 25));
        this.returnButton.setBounds((width - 140) / 20,620,90,50);
        this.returnButton.setFocusPainted(false);
        this.returnButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        this.add(returnButton);
    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (this.signUpBackground != null){
            this.signUpBackground.paintIcon(null, graphics, 0, 0);
        }

    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public boolean isVerifiedPassword() {
        return Arrays.equals(this.teamPassword.getPassword(), this.teamPasswordVerification.getPassword());
    }
    public boolean hasEmptyField() {
        return
//                nicknamePlayer1.getText().isEmpty() || nicknamePlayer2.getText().isEmpty()||
                teamName.getText().isEmpty() || teamPassword.getText().isEmpty() || teamPasswordVerification.getText().isEmpty();
    }
    public void resetPanel() {
        this.teamName.setText(null);
        this.teamPassword.setText(null);
        this.showPassword1CheckBox.setSelected(false);
        this.teamPassword.setEchoChar('\u2022');
        this.teamPasswordVerification.setText(null);
        this.showPassword2CheckBox.setSelected(false);
        this.teamPasswordVerification.setEchoChar('\u2022');

    }

    public String getTeamName() {
        return teamName.getText();
    }
    public String getTeamPassword() {
        return teamPassword.getText();
    }

    public AbstractButton getReturnButton() {
        return returnButton;
    }
}
