package frontend;

import backend.account;
import backend.bankmanagement;
import backend.transaction;

import javax.swing.*;
import java.awt.*;

public class UserFrame extends JFrame {

    private bankmanagement bank;
    private account user;

    JLabel balanceLabel;

    public UserFrame(bankmanagement bank, account user) {

        this.bank = bank;
        this.user = user;

        setTitle("JMAD BANK - USER");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel(
                "Welcome, " + user.getname(),
                SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 20));

        balanceLabel = new JLabel(
                "Current Balance : ₹" + user.getbalance(),
                SwingConstants.CENTER);

        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton transferBtn = new JButton("Transfer");
        JButton detailsBtn = new JButton("View Details");
        JButton historyBtn = new JButton("Transaction History");
        JButton logoutBtn = new JButton("Logout");

        JPanel panel = new JPanel(new GridLayout(6,1,10,10));

        panel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        panel.add(depositBtn);
        panel.add(withdrawBtn);
        panel.add(transferBtn);
        panel.add(detailsBtn);
        panel.add(historyBtn);
        panel.add(logoutBtn);

        add(title,BorderLayout.NORTH);
        add(balanceLabel,BorderLayout.CENTER);
        add(panel,BorderLayout.SOUTH);

        depositBtn.addActionListener(e->{

            String amt = JOptionPane.showInputDialog(
                    this,
                    "Enter Deposit Amount");

            if(amt==null) return;

            try{

                bank.depositMoney(
                        user.getacc_num(),
                        Double.parseDouble(amt));

                refresh();

            }catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                        "Invalid Amount");

            }

        });

        withdrawBtn.addActionListener(e->{

            String amt = JOptionPane.showInputDialog(
                    this,
                    "Enter Withdrawal Amount");

            if(amt==null) return;

            try{

                bank.withdrawMoney(
                        user.getacc_num(),
                        Double.parseDouble(amt));

                refresh();

            }catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                        "Invalid Amount");

            }

        });

        transferBtn.addActionListener(e->{

            String target = JOptionPane.showInputDialog(
                    this,
                    "Target Account Number");

            if(target==null) return;

            String amt = JOptionPane.showInputDialog(
                    this,
                    "Amount");

            if(amt==null) return;

            try{

                bank.transferMoney(
                        user.getacc_num(),
                        target,
                        Double.parseDouble(amt));

                refresh();

            }catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                        "Invalid Input");

            }

        });

        detailsBtn.addActionListener(e->{

            JOptionPane.showMessageDialog(
                    this,
                    user.display());

        });

        historyBtn.addActionListener(e->{

            StringBuilder sb = new StringBuilder();

            for(transaction t : user.getTransactionHistory()){

                sb.append(t.toString()).append("\n\n");

            }

            if(sb.length()==0){

                sb.append("No Transactions");

            }

            JTextArea area = new JTextArea(sb.toString());

            area.setEditable(false);

            JOptionPane.showMessageDialog(
                    this,
                    new JScrollPane(area),
                    "Transaction History",
                    JOptionPane.INFORMATION_MESSAGE);

        });

        logoutBtn.addActionListener(e->{

            new LoginFrame(bank);

            dispose();

        });

        setVisible(true);

    }

    private void refresh(){

        balanceLabel.setText(
                "Current Balance : ₹" + user.getbalance());

    }

}