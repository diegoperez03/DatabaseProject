package cs174.project.perez.olguin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class BankerAppUI {
    private JPanel panel;
    private JLabel WelcomeBanker;
    private JComboBox transcType;
    private static JFrame frame;

    public BankerAppUI(String bankerId) {
        panel.setSize(300, 300);

        String name = JDBCExample.getNameFromBankerId(bankerId);
        WelcomeBanker.setText("Welcome Banker from " + name + " !");

//        String id = JDBCExample.getIdFromPin(customerPin);
//        HashMap<String, String> example = JDBCExample.getAvailableAccounts(id);
        //pull name from database with pin  and set welcome message on top
        //pull up dialog if more then one account
        //numOfAccounts(name,pin);  Implement this function to find out how many and which accounts are avaiable to pick
        //For now we assume only one


        String[] transactions = {
                " ",
                "Enter Check Transaction",
                "Generate Monthly Statement",
                "List Closed Accounts",
                "Generate Government Drug and Tax Evasion Report(DTER)",
                "Transfer",
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
                    EnterCheckTransaction();
                } else if (transcType.getSelectedItem().equals("Generate Monthly Statement")) {
                    System.out.println(transcType.getSelectedItem());
                    //TopUp(pin);
                } else if (transcType.getSelectedItem().equals("List Closed Accounts")) {
                    System.out.println(transcType.getSelectedItem());
                    //Withdrawal(pin);
                } else if (transcType.getSelectedItem().equals("Generate Goverment Drug and Tax Evasion Report(DTER)")) {
                    System.out.println(transcType.getSelectedItem());
                    //Transfer(pin);
                } else if (transcType.getSelectedItem().equals("Transfer")) {
                    System.out.println(transcType.getSelectedItem());
                    //Wire(pin);
                } else if (transcType.getSelectedItem().equals("Customer Report")) {
                    System.out.println(transcType.getSelectedItem());
                    //PayFriend(pin);
                } else if (transcType.getSelectedItem().equals("Add Interest")) {
                    System.out.println(transcType.getSelectedItem());
                    //QuickCash(pin);
                } else if (transcType.getSelectedItem().equals("Create Account")) {
                    System.out.println(transcType.getSelectedItem());
                    //Purchase(pin);
                } else if (transcType.getSelectedItem().equals("Delete Closed Accounts and Customers")) {
                    System.out.println(transcType.getSelectedItem());
                    //Collect(pin);
                } else if (transcType.getSelectedItem().equals("Delete Transactions")) {
                    System.out.println(transcType.getSelectedItem());
                    //Collect(pin);
                }

            }
        });

    }

    public void EnterCheckTransaction() {
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
            JDBCExample.BankerEnterCheckTransaction(cid, tid, amount, "EnterCheck");
        } else {

        }
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
