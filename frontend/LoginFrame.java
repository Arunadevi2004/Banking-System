package frontend;

import backend.account;
import backend.bankmanagement;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private bankmanagement bank;

    private JTextField txtAccountNo;
    private JPasswordField txtPassword;

    private static final String ADMIN_PASSWORD = "JMAD@Bank2026";

    public LoginFrame(bankmanagement bank) {

        this.bank = bank;

        setTitle("JMAD BANK");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7,1,10,10));

        JLabel title = new JLabel("JMAD BANK",SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,22));

        txtAccountNo = new JTextField();

        txtPassword = new JPasswordField();

        JButton adminBtn = new JButton("Admin Login");
        JButton userBtn = new JButton("User Login");

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(title);

        panel.add(new JLabel("Account Number"));
        panel.add(txtAccountNo);

        panel.add(new JLabel("Admin Password"));
        panel.add(txtPassword);

        JPanel btnPanel = new JPanel();

        btnPanel.add(adminBtn);
        btnPanel.add(userBtn);

        add(panel,BorderLayout.CENTER);
        add(btnPanel,BorderLayout.SOUTH);

        // Admin Login
        adminBtn.addActionListener(e -> {

                JPasswordField passwordField = new JPasswordField();

                int option = JOptionPane.showConfirmDialog(
                    this,
                    passwordField,
                    "Enter Admin Password",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if(option == JOptionPane.OK_OPTION){

                String pass = new String(passwordField.getPassword());

                if(pass.equals(ADMIN_PASSWORD)){

                    new AdminFrame(bank);
                    dispose();

                }else{

                    JOptionPane.showMessageDialog(this,
                            "Invalid Admin Password");

                }
            }

        });

        // User Login
        userBtn.addActionListener(e->{

            String accNo = txtAccountNo.getText();

            account acc = bank.searchAcc(accNo);

            if(acc!=null){

                new UserFrame(bank,acc);
                dispose();

            }else{

                JOptionPane.showMessageDialog(this,
                        "Invalid Account Number");

            }

        });

        setVisible(true);

    }

}