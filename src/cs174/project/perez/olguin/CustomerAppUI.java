package cs174.project.perez.olguin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CustomerAppUI {
    private JLabel welcome;
    private JPanel panel;
    private JComboBox transactionType;
    private static JFrame frame;

    public CustomerAppUI(String pin) {
        panel.setSize(300, 300);

        String name = JDBCExample.getNameFromPin(pin);
        welcome.setText("Welcome " + name + " !");
        //pull name from database with pin  and set welcome message on top
        //pull up dialog if more then one account
        //numOfAccounts(name,pin);  Implement this function to find out how many and which accounts are avaiable to pick
        //For now we assume only one
        String account;
        int numAccounts = 2;
        if (numAccounts > 1) {
            String[] options = {
                    "Checkings",
                    "Savings"
            };

            account = (String) JOptionPane.showInputDialog(null, "Chose which account you want to access",
                    "Choose Account", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            System.out.println(account);

        } else {
            account = "Checking";
        }

        String[] transactions = {
                "Deposit",
                "Top-Up",
                "Withdrawal",
                "Purchase",
                "Transfer",
                "Collect",
                "Wire",
                "Pay-friend",
                "Quick-cash"
        };


        for (String s : transactions) {
            transactionType.addItem(s);
        }
        //Depending on transaction selected call function that will populate necessary fields for the user to enter their info
        transactionType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (transactionType.getSelectedItem().equals("Deposit")) {
                    System.out.println(transactionType.getSelectedItem());
                    //Deposit(pin);
                } else if (transactionType.getSelectedItem().equals("Top-Up")) {
                    System.out.println(transactionType.getSelectedItem());
                    //TopUp(pin);
                } else if (transactionType.getSelectedItem().equals("Withdrawal")) {
                    System.out.println(transactionType.getSelectedItem());
                    //Withdrawal(pin);
                } else if (transactionType.getSelectedItem().equals("Transfer")) {
                    System.out.println(transactionType.getSelectedItem());
                    //Transfer(pin);
                } else if (transactionType.getSelectedItem().equals("Wire")) {
                    System.out.println(transactionType.getSelectedItem());
                    //Wire(pin);
                } else if (transactionType.getSelectedItem().equals("Pay-friend")) {
                    System.out.println(transactionType.getSelectedItem());
                    //PayFriend(pin);
                } else if (transactionType.getSelectedItem().equals("Quick-cash")) {
                    System.out.println(transactionType.getSelectedItem());
                    //QuickCash(pin);
                } else if (transactionType.getSelectedItem().equals("Purchase")) {
                    System.out.println(transactionType.getSelectedItem());
                    //Purchase(pin);
                } else if (transactionType.getSelectedItem().equals("Collect")) {
                    System.out.println(transactionType.getSelectedItem());
                    //Collect(pin);
                }
            }

        });

        //Deposit, Top-Up, Withdrawl, Purchase, Transfer, collect, Wire , Pay-friend,quick-cash
        //
    }

    public static void main(String[] args) {
        frame = new JFrame("CustomerApp");
        frame.setContentPane(new CustomerAppUI(args[0]).panel);
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
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        welcome = new JLabel();
        welcome.setText("Label");
        panel.add(welcome, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        transactionType = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        transactionType.setModel(defaultComboBoxModel1);
        panel.add(transactionType, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Enter your desired Transaction");
        panel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}