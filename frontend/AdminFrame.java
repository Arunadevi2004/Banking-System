package frontend;

import backend.account;
import backend.bankmanagement;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {

    private bankmanagement bank;

    public AdminFrame(bankmanagement bank) {

        this.bank = bank;

        setTitle("JMAD BANK - ADMIN");
        setSize(500,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6,1,15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        JLabel title = new JLabel("ADMIN DASHBOARD",SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,22));

        JButton createBtn = new JButton("Create Account");
        JButton viewBtn = new JButton("View All Accounts");
        JButton searchBtn = new JButton("Search Account");
        JButton deleteBtn = new JButton("Delete Account");
        JButton totalBtn = new JButton("Total Accounts");
        JButton logoutBtn = new JButton("Logout");

        panel.add(createBtn);
        panel.add(viewBtn);
        panel.add(searchBtn);
        panel.add(deleteBtn);
        panel.add(totalBtn);
        panel.add(logoutBtn);

        add(title,BorderLayout.NORTH);
        add(panel,BorderLayout.CENTER);

        

        // Create Account
        createBtn.addActionListener(e -> {

            String name = JOptionPane.showInputDialog(this,
                    "Enter Account Holder Name");

            if(name==null) return;

            String amt = JOptionPane.showInputDialog(this,
                    "Enter Initial Deposit");

            if(amt==null) return;

            try{

                double amount = Double.parseDouble(amt);

                if(amount<1000){

                    JOptionPane.showMessageDialog(this,
                            "Minimum Deposit is ₹1000");

                    return;
                }

                bank.addAcc(name,amount);

                JOptionPane.showMessageDialog(this,
                        "Account Created Successfully");

            }catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                        "Invalid Amount");

            }

        });

        // View Accounts
        viewBtn.addActionListener(e -> {

            StringBuilder sb = new StringBuilder();

            for(account a : bank.getAccounts()){

                sb.append(a.display()).append("\n\n");

            }

            if(sb.length()==0){

                sb.append("No Accounts Found");

            }

            JTextArea area = new JTextArea(sb.toString());

            area.setEditable(false);

            JOptionPane.showMessageDialog(this,
                    new JScrollPane(area),
                    "All Accounts",
                    JOptionPane.INFORMATION_MESSAGE);

        });

        // Search
        searchBtn.addActionListener(e -> {

            String acc = JOptionPane.showInputDialog(this,
                    "Enter Account Number");

            if(acc==null) return;

            account obj = bank.searchAcc(acc);

            if(obj!=null){

                JOptionPane.showMessageDialog(this,
                        obj.display());

            }else{

                JOptionPane.showMessageDialog(this,
                        "Account Not Found");

            }

        });

        // Delete
        deleteBtn.addActionListener(e -> {

            String acc = JOptionPane.showInputDialog(this,
                    "Enter Account Number");

            if(acc==null) return;

            bank.deleteAcc(acc);

        });

        // Total Accounts
        totalBtn.addActionListener(e -> {

            JOptionPane.showMessageDialog(this,
                    "Total Accounts : "
                    + bank.getAccounts().size());

        });

        // Logout
        logoutBtn.addActionListener(e -> {

            new LoginFrame(bank);

            dispose();

        });

        setVisible(true);

    }

}