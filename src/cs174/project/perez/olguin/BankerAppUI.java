package cs174.project.perez.olguin;

import jdk.nashorn.internal.scripts.JD;
import  jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;

public class BankerAppUI {
    private JPanel panel;
    private JLabel WelcomeBanker;
    private JComboBox transcType;
    private static JFrame frame;

    public BankerAppUI(String bankerId) {
        panel.setSize(300, 300);

        String name = JDBCExample.getNameFromBankerId(bankerId);
        WelcomeBanker.setText("Welcome Banker from " + name + " !");
        //pull name from database with pin  and set welcome message on top
        //pull up dialog if more then one account
        //numOfAccounts(name,pin);  Implement this function to find out how many and which accounts are avaiable to pick
        //For now we assume only one


        String[] transactions = {
                " ",
                "Enter Check Transaction",
                "Generate Monthly Statement",
                "List Closed Accounts",
                "Generate Goverment Drug and Tax Evasion Report(DTER)",
                "Customer Report",
                "Add Interest",
                "Create Account",
                "Delete Closed Accounts and Customers",
                "Delete Transactions"
        };


        for (String s : transactions) {
            transcType.addItem(s);
        }
        //Depending on transaction selected call function that will populate necessary fields for the user to enter their info
        transcType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (transcType.getSelectedItem().equals("Enter Check Transaction")) {
                    System.out.println(transcType.getSelectedItem());
                    EnterCheckTransaction(bankerId);
                } else if (transcType.getSelectedItem().equals("Generate Monthly Statement")) {
                    System.out.println(transcType.getSelectedItem());
                    GenerateMonthlyStatement(bankerId);
                } else if (transcType.getSelectedItem().equals("List Closed Accounts")) {
                    System.out.println(transcType.getSelectedItem());
                    ListClosedAccounts(bankerId);
                } else if (transcType.getSelectedItem().equals("Generate Goverment Drug and Tax Evasion Report(DTER)")) {
                    System.out.println(transcType.getSelectedItem());
                    GenerateDTER(bankerId);
                } else if (transcType.getSelectedItem().equals("Customer Report")) {
                    System.out.println(transcType.getSelectedItem());
                    CustomerReport(bankerId);
                } else if (transcType.getSelectedItem().equals("Add Interest")) {
                    System.out.println(transcType.getSelectedItem());
                    AddInterest(bankerId);
                } else if (transcType.getSelectedItem().equals("Create Account")) {
                    System.out.println(transcType.getSelectedItem());
                    CreateAccount(bankerId);
                } else if (transcType.getSelectedItem().equals("Delete Closed Accounts and Customers")) {
                    System.out.println(transcType.getSelectedItem());
                    DeleteClosedAccounts(bankerId);
                } else if (transcType.getSelectedItem().equals("Delete Transactions")) {
                    System.out.println(transcType.getSelectedItem());
                    DeleteTransactions(bankerId);
                }

            }
        });

    }

    public void EnterCheckTransaction(String bankerid) {
        JTextField value = new JTextField();
        JTextField value2 = new JTextField();
        JTextField value3 = new JTextField();
        String cid;
        String tid;
        Double amount;
        Object message[] = {
                "Enter Customer's Account ID", value,
                "Enter Customer's Tax ID", value2,
                "Enter an Amount", value3
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            cid = value.getText();
            tid = value2.getText();
            amount = Double.parseDouble(value3.getText());
            JDBCExample.BankerEnterCheckTransaction(cid, tid, amount, "EnterCheck", bankerid);
        } else {
        }
    }

    public void GenerateMonthlyStatement(String bankerid) {
        String value = JOptionPane.showInputDialog("Enter Customer's Tax ID");
        String tid;
        String statement = "";
        String name;
        String address;
        HashMap<String, String> hashMap = new HashMap<>();
        HashMap<Timestamp, String> hashMap1 = new HashMap<>();
        try {
            tid = value;
            System.out.println(tid);
            hashMap = JDBCExample.getAllAccounts(tid);
            name = JDBCExample.getNameFromId(tid);
            address = JDBCExample.getAddressFromId(tid);
            statement += name + " " + address + " " + '\n';
            for (String key : hashMap.keySet()) {
                statement += key + '\n';
                String accountId = hashMap.get(key);
                hashMap1 = JDBCExample.getTransactionsFromAccount(accountId);
                ArrayList<Double> finLast = JDBCExample.getFinalInitialBalance(accountId);
                statement += '\t' + "   " + "Initial Balance: " + finLast.get(0) + '\n';
                if (hashMap1.size() == 0) {
                    statement += '\t' + "   " + "No transactions made in last 30 days for this account" + '\n';
                    statement += '\t' + "   " + "Final Balance: " + finLast.get(1) + '\n';
                } else {
                    for (Timestamp key1 : hashMap1.keySet()) {
                        statement += '\t' + "   " + hashMap1.get(key1) + ": " + key1 + '\n';
                    }
                    statement += '\t' + "   " + "Final Balance: " + finLast.get(1) + '\n';
                }
                if ((finLast.get(1) - finLast.get(0)) > 100000) {
                    statement += "Customer has reached the limit of the insurance!";
                }
                //JDBCExample.BankerGenerateMonthlyStatement(tid, bankerid);
            }
            JOptionPane.showMessageDialog(null, statement);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    public void ListClosedAccounts(String bankerid) {
        HashMap<String, String> hashMap = JDBCExample.BankerListClosedAccounts(bankerid);
        String statement = "Listing closed accounts in the format AccountType: AccountId" + '\n';
        for (String key : hashMap.keySet()) {
            statement += hashMap.get(key) + ": " + key + '\n';
        }
        JOptionPane.showMessageDialog(null, statement);
    }

    public void GenerateDTER(String bankerid) {
        ArrayList<String> accIds = JDBCExample.getAIDfromIncreaseTransactions(bankerid);
        //ArrayList<String> names = new ArrayList<>();
        String statement = '\t' + "      " + '\t' + "   " + "Generating Government Drug and Tax Evasion Report" + '\n';
        statement += "--------------------------------------------------------------------------" + '\n';
        statement += "Listing Account IDs for customers with sum of deposits, transfers, and wires over $10,000:" + '\n';
        for (int i = 0; i < accIds.size(); i++) {
            statement += "--> " + accIds.get(i) + '\n';
        }
        JOptionPane.showMessageDialog(null, statement);
    }

    public void CustomerReport(String bankerid) {
        String value = JOptionPane.showInputDialog("Enter Customer's Tax ID");
        ArrayList<ArrayList<String>> arrayList = JDBCExample.BankerCustomerReport(bankerid, value);
        String statement = "Generating a list of all accounts for customer with tax ID: " + value + '\n';
        for (ArrayList<String> temp : arrayList) {
            String aid = temp.get(0);
            String type = temp.get(1);
            String closed = temp.get(2);
            if (closed.equals("f")) {
                statement += aid + " " + type + " " + '\t' + '\t' + "Closed: False" + '\n';
            } else {
                statement += aid + " " + type + " " + '\t' + '\t' + "Closed: True" + '\n';
            }
        }
        JOptionPane.showMessageDialog(null, statement);
    }

    public void AddInterest(String bankerid) {
        JTextField value = new JTextField();
        JTextField value2 = new JTextField();
        JTextField value3 = new JTextField();
        String cid;
        String tid;
        Double amount;
        Object message[] = {
                "Enter Customer's Account ID", value,
                "Enter Customer's Tax ID", value2,
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            cid = value.getText();
            tid = value2.getText();
            Double interest = JDBCExample.getInterest(cid);
            Double interest2 = JDBCExample.AccrueInterest(cid, tid);
            JOptionPane.showMessageDialog(null, "Customer has accrued " + interest2 + " dollars");
            JDBCExample.CustomerAccruedInterestTransaction(tid, cid, interest2);
            JDBCExample.BankerAddInterest(bankerid);
        } else {
        }
    }

    public void CreateAccount(String bankerid) {
        //prompt for number of owners
        String value = (String) JOptionPane.showInputDialog("Enter the number of Owners");
        int numOfOwners = Integer.parseInt(value);
        System.out.println(numOfOwners);
        ArrayList<String> Owners = new ArrayList<>();
        Double interest = 0.0;
        //show entertaxid dialog that amount of times
        for (int i = 0; i < numOfOwners; i++) {
            String name = (String) JOptionPane.showInputDialog("Enter Owner " + (i + 1));
            Owners.add(name);
            System.out.println(name);

        }
        //show enter account info dialog
        JTextField accountTypeField = new JTextField();
        JTextField branchNameField = new JTextField();
        JTextField accountidfield = new JTextField();

        String accounttype;
        String branchname;
        String accountid;
        Object message[] = {
                "Enter Account Type", accountTypeField,
                "Enter branch name", branchNameField,
                "Enter your preferred accountid or leave blank for a random one", accountidfield

        };
        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            accounttype = accountTypeField.getText();
            if (accounttype.equals("Pocket")) {
                String taxid = JDBCExample.getIdFromName(Owners.get(0));
                int size = JDBCExample.getCheckingSavingAccounts(taxid).size();
                if (size == 0) {
                    JOptionPane.showMessageDialog(null, "You do not have an associated Checking or Savings account");
                } else {
                    branchname = branchNameField.getText();
                    accountid = accountidfield.getText();
                    if (accountid.length() == 0) {
                        accountid = JDBCExample.getUniqueAccountId();
                    }

                    for (int j = 0; j < numOfOwners; j++) {
                        JDBCExample.addAccount(accountid, Owners.get(j), 0.0, accounttype, branchname, interest);
                    }
                }

            }

            if (accounttype.equals("Checking") | accounttype.equals("Interest-Checking")) {
                interest = 5.5 / 12;
            }
            if (accounttype.equals("Savings")) {
                interest = 7.5 / 12;
            }

            branchname = branchNameField.getText();
            accountid = accountidfield.getText();
            if (accountid.length() == 0) {
                accountid = JDBCExample.getUniqueAccountId();
            }

            for (int j = 0; j < numOfOwners; j++) {
                JDBCExample.addAccount(accountid, Owners.get(j), 0.0, accounttype, branchname, interest);
            }
        }
    }

    public void DeleteClosedAccounts(String bankerid) {
        String statement = "Removing all closed accounts from the database and removing all customers who do not own any accounts.";
        JDBCExample.BankerDeleteClosedAccounts(bankerid);
        JOptionPane.showMessageDialog(null, statement);
    }

    public void DeleteTransactions(String bankerid) {
        String statement = "Deleting all transactions... Creating a fresh table for the new month";
        JOptionPane.showMessageDialog(null, statement);
        JDBCExample.BankerDeleteTransactions(bankerid);
    }


    public static void main(String[] args) {
        frame = new JFrame("BankerApp");
        frame.setContentPane(new BankerAppUI(args[0]).panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        WelcomeBanker = new JLabel();
        WelcomeBanker.setText("Label");
        panel.add(WelcomeBanker, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        transcType = new JComboBox();
        panel.add(transcType, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Enter a Banker Transaction");
        panel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
