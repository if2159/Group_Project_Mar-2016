package ATM;
//TODO write new transactions to the DB
//e.g. from deposit, transfer, and withdraw
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AccountScreen extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton tranBtn, depositBtn, chngPassBtn, balanceBtn, withdrawBtn, exitBtn, reactivateBtn,viewTransactions;
    private final Connection conn;
    private final long Account_Number;
    private String First_Name;
    private String Last_Name;
    private double Balance;
    private boolean Active;

    /**
     * Create the frame.
     */
    public AccountScreen(long accountNum, Connection con) {
        conn = con;
        Account_Number = accountNum;
        startUp();
        this.setTitle("Welcome " + First_Name + " " + Last_Name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        drawHomeScreen();

    }

    private void drawHomeScreen() {
        

        
        depositBtn = new JButton("Deposit");
        depositBtn.setToolTipText("Deposit money into account");
        depositBtn.setEnabled(true);
        depositBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        depositBtn.setBounds(10, 30, 125, 75);
        contentPane.add(depositBtn);

        chngPassBtn = new JButton("<html><center>Change<br>Password</center></html>");
        chngPassBtn.setToolTipText("Change Your Password");
        chngPassBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        chngPassBtn.setEnabled(true);
        chngPassBtn.setBounds(10, 230, 125, 75);
        contentPane.add(chngPassBtn);

        balanceBtn = new JButton("<html><center>Check<br>Balance</center></html>");
        balanceBtn.setToolTipText("Check Balance of Account");
        balanceBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        balanceBtn.setEnabled(true);
        balanceBtn.setBounds(10, 130, 125, 75);
        contentPane.add(balanceBtn);

        withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setToolTipText("");
        withdrawBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        withdrawBtn.setEnabled(true);
        withdrawBtn.setBounds(399, 30, 125, 75);
        contentPane.add(withdrawBtn);

        tranBtn = new JButton("<html><center>Make a<br>Transfer</center><html>");
        tranBtn.setToolTipText("Transfer Money to Another Account");
        tranBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        tranBtn.setEnabled(true);
        tranBtn.setBounds(399, 130, 125, 75);
        contentPane.add(tranBtn);

        exitBtn = new JButton("Exit");
        exitBtn.setToolTipText("Exit and Logout");
        exitBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        exitBtn.setEnabled(true);
        exitBtn.setBounds(399, 230, 125, 75);
        contentPane.add(exitBtn);

        exitBtn.addActionListener(this);
        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        balanceBtn.addActionListener(this);
        chngPassBtn.addActionListener(this);
        tranBtn.addActionListener(this);

                /*
		 * If account is not active it disables all the buttons
		 * Also creates the reactivate account button
		 * */
        if (!Active) {
            withdrawBtn.setEnabled(false);
            tranBtn.setEnabled(false);
            depositBtn.setEnabled(false);
            balanceBtn.setEnabled(false);

            reactivateBtn = new JButton("<html><center>Reactivate<br>Account</center></html>");
            reactivateBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
            reactivateBtn.setBounds(204, 130, 125, 75);
            contentPane.add(reactivateBtn);
            reactivateBtn.addActionListener(this);
        }
        else if(Active){
            viewTransactions =new JButton("<html><center>View<br>Transactions</center></html>");
            viewTransactions.setHorizontalAlignment(SwingConstants.CENTER);
            viewTransactions.setFont(new Font("Tahoma", Font.BOLD, 18));
            viewTransactions.setBounds(188, 130, 150, 75);
            contentPane.add(viewTransactions);
            viewTransactions.addActionListener(this);
        }
    }

        /*
	 * Used to debug to console.
	 * */
 /*
	private void Debug(){
		System.out.println("Account Number: "+Account_Number);
		System.out.println("Last Name: "+Last_Name);
		System.out.println("First Name: " + First_Name);
		System.out.println("Balance: " + Balance);
		System.out.println("Active: " + Active);
	}
     */
        /*
	 * Reads all account info from file.
	 * Does not load other account details.
	 * Also checks if the account is active
	 * */
    private void startUp() {

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT accountNumber,LastName,FirstName,Balance,active FROM accounts WHERE accountNumber=?;");
            ps.setLong(1, Account_Number);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                First_Name = rs.getString("FirstName");
                Last_Name = rs.getString("LastName");
                Balance = rs.getDouble("Balance");
                Active = rs.getBoolean("active");
            }

        } catch (SQLException e) {
            System.out.println("File Not Found: Account Information");
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (tranBtn == event.getSource()) {
            transfer();
        } else if (transferButton == event.getSource()) {
            try {
                transfer(Long.parseLong(accntNumberField.getText()), Double.parseDouble(amntField.getText()));
            } catch (NumberFormatException e) {
                alert("Invalid Input.");
            }
        } else if (exitBtn == event.getSource()) {
            exit();
        } else if (balanceBtn == event.getSource()) {
            checkBalance();
        } else if (withdrawBtn == event.getSource()) {
            withdraw();

        } else if (withdrawButton == event.getSource()) {
            try {
                withdraw(Double.parseDouble(withdrawField.getText()));
            } catch (NumberFormatException e) {
                alert("Invalid Amount.");
            }
        } else if (chngPassBtn == event.getSource()) {
            changePassword();
        } // Checks if the passwords are the same. 
        // Also checks if there is a password at all
        // Can easily change to require larger password size requirements
        else if (changePasswordButton == event.getSource()) {
            if (passwordField.getText().equals(confirmPasswordField.getText())) {
                String newPass = passwordField.getText();
                if (newPass.length() > 0) {
                    changePassword(newPass);

                } else {
                    alert("No password entered.");
                }
            } else {
                alert("Passwords do not match.");
            }
        } else if (depositBtn == event.getSource()) {
            deposit();
        } else if (depositButton == event.getSource()) {
            try {
                deposit(Double.parseDouble(depositField.getText()));
            } catch (NumberFormatException e) {
                alert("Please enter an ammount to deposit field.");
            }
        } else if (reactivateBtn == event.getSource()) {
            activate();
            drawHomeScreen();
            repaint();
        } 
        else if(viewTransactions == event.getSource()){
            System.out.println("poob");
            viewTrans();
        }
        //When pressed it hides all the other components from other scenes
        //Then is displays the home screen
        else if (returnBtn == event.getSource()) {
            clearScreen();

            drawHomeScreen();
            
            repaint();
        }

    }
    //The following methods activate when the buttons are press
    //Allows the display of the relevant scenes
    //TODO
    /*
	 * Passwords cannot contain spaces.
	 * */
    private JTextField passwordField, confirmPasswordField;
    private JButton changePasswordButton;

    private void changePassword() {//You can look at writeAccnt() and Main.login() to see how to do this.
        clearScreen();

        returnBtn = new JButton("\u2190 Return");
        returnBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        returnBtn.setBounds(10, 11, 134, 23);
        contentPane.add(returnBtn);
        returnBtn.addActionListener(this);

        inbeddedLabel = new JLabel("Enter Password Again:");
        inbeddedLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        inbeddedLabel.setBounds(10, 187, 216, 20);
        contentPane.add(inbeddedLabel);

        inbeddedLabel2 = new JLabel("Enter New Password:");
        inbeddedLabel2.setFont(new Font("Tahoma", Font.BOLD, 18));
        inbeddedLabel2.setBounds(10, 160, 216, 20);
        contentPane.add(inbeddedLabel2);

        confirmPasswordField = new JTextField();
        confirmPasswordField.setBounds(219, 190, 134, 20);
        contentPane.add(confirmPasswordField);
        confirmPasswordField.setColumns(10);

        changePasswordButton = new JButton("<html><center>Confirm<br>Change</center></html>");
        changePasswordButton.addActionListener(this);
        changePasswordButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        changePasswordButton.setBounds(386, 218, 121, 53);
        contentPane.add(changePasswordButton);

        passwordField = new JTextField();
        passwordField.setColumns(10);
        passwordField.setBounds(219, 163, 134, 20);
        contentPane.add(passwordField);
    }
    private void viewTrans(){
        clearScreen();
        
        ArrayList<Object[]> transList = loadTransactions();      
        Object [][]transMat = new Object[transList.size()][3];
        ArrayList<Integer> rowsToBeColored = new ArrayList<Integer>();
        for(int r = 0; r<transMat.length; r++){
            for(int c = 0; c<3;c++){
                transMat[r][c] = transList.get(r)[c];
            }
        }
        String[] columnNames = {"Time Stamp", "Description", "Amount"};
        JTable table = new JTable(transMat,columnNames);
        table.setBounds(386, 218, 120, 53);
        table.getColumnModel().getColumn(0).setPreferredWidth(75);
        table.getColumnModel().getColumn(1).setPreferredWidth(180);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(25, 50, 500, 300);
        contentPane.add(scrollPane);
        scrollPane.setVisible(true);
        table.setVisible(true);
        
        returnBtn = new JButton("\u2190 Return");
        returnBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        returnBtn.setBounds(10, 11, 134, 23);
        contentPane.add(returnBtn);
        returnBtn.addActionListener(this);
        
        System.out.println("boop");
        repaint();
        
        
    }
    
    public ArrayList<Object[]> loadTransactions(){
        ArrayList<Object[]> transList= new ArrayList<Object[]>();
        try{
            PreparedStatement ps3 = conn.prepareStatement("SELECT count(1)\n"
                    + "FROM transactions\n"
                    + "WHERE originAccountNumber =? OR destinationAccount=?;");
            ps3.setLong(1, Account_Number);
            ps3.setLong(2, Account_Number);
            ResultSet rs3 = ps3.executeQuery();
            if (rs3.next()) {
                if (rs3.getInt(1) > 0) {
                    PreparedStatement ps = conn.prepareStatement("SELECT * FROM transactions WHERE originAccountNumber = ? OR destinationAccount=?;");
                    ps.setLong(1, Account_Number);
                    ps.setLong(2, Account_Number);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        String desc = rs.getString("Description");
                        double amount = rs.getDouble("amount");
                        long destAccntNum = rs.getLong("destinationAccount");
                        Timestamp time = rs.getTimestamp("timeStamp");
                        transList.add(new Transaction(desc,amount,time,Account_Number,destAccntNum).getAsArray());
                    }
            
                    
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return transList;
    }
    
    /*
	 * Check to make sure they can withdraw that amount. Compare to $Balance
	 * Withdraw amount from balance. Open file and write change to file.
     */
    private JButton withdrawButton, returnBtn;
    private JTextField withdrawField;
    private JLabel inbeddedLabel;

    private void withdraw() {
        clearScreen();

        inbeddedLabel = new JLabel("Amount to Withdraw: $");
        inbeddedLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        inbeddedLabel.setBounds(10, 160, 216, 20);
        contentPane.add(inbeddedLabel);

        withdrawField = new JTextField();
        withdrawField.setBounds(225, 163, 165, 20);
        contentPane.add(withdrawField);
        withdrawField.setColumns(10);

        returnBtn = new JButton("\u2190 Return");
        returnBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        returnBtn.setBounds(10, 11, 134, 23);
        contentPane.add(returnBtn);
        returnBtn.addActionListener(this);

        withdrawButton = new JButton("<html><center>Withdraw<br>Amount</center></html>");
        withdrawButton.addActionListener(this);
        withdrawButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        withdrawButton.setBounds(403, 195, 121, 53);
        contentPane.add(withdrawButton);

    }

    /*
	 * Only transfer to existing accounts
	 * */
    private JTextField accntNumberField, amntField;
    private JButton transferButton;
    private JLabel inbeddedLabel2;

    private void transfer() {
        clearScreen();
        returnBtn = new JButton("\u2190 Return");
        returnBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        returnBtn.setBounds(10, 11, 134, 23);
        contentPane.add(returnBtn);
        returnBtn.addActionListener(this);

        inbeddedLabel = new JLabel("Amount to Deposit: $");
        inbeddedLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        inbeddedLabel.setBounds(10, 187, 216, 20);
        contentPane.add(inbeddedLabel);

        inbeddedLabel2 = new JLabel("Transfer Fund to: ");
        inbeddedLabel2.setFont(new Font("Tahoma", Font.BOLD, 18));
        inbeddedLabel2.setBounds(10, 160, 216, 20);
        contentPane.add(inbeddedLabel2);

        amntField = new JTextField();
        amntField.setBounds(209, 187, 134, 20);
        contentPane.add(amntField);
        amntField.setColumns(10);

        accntNumberField = new JTextField();
        accntNumberField.setColumns(10);
        accntNumberField.setBounds(209, 160, 134, 20);
        contentPane.add(accntNumberField);

        transferButton = new JButton("<html><center>Transfer</center></html>");
        transferButton.addActionListener(this);
        transferButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        transferButton.setBounds(386, 218, 121, 53);
        contentPane.add(transferButton);

    }

    private JTextField depositField;
    private JButton depositButton;

    private void deposit() {
        clearScreen();
        inbeddedLabel = new JLabel("Amount to Deposit: $");
        inbeddedLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        inbeddedLabel.setBounds(10, 160, 216, 20);
        contentPane.add(inbeddedLabel);

        depositField = new JTextField();
        depositField.setBounds(225, 163, 165, 20);
        contentPane.add(depositField);
        depositField.setColumns(10);

        returnBtn = new JButton("\u2190 Return");
        returnBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        returnBtn.setBounds(10, 11, 134, 23);
        contentPane.add(returnBtn);
        returnBtn.addActionListener(this);

        depositButton = new JButton("<html><center>Deposit<br>Amount</center></html>");
        depositButton.addActionListener(this);
        depositButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        depositButton.setBounds(403, 195, 121, 53);
        contentPane.add(depositButton);
        repaint();

    }
    //END OF DISPLAY METHODS
    //TODO

    /*
	 * Used to reactivate the user's account.
	 * Re-enables the accounts functionality.
	 * */
    private void activate() {
        Active = true;
        writeAccnt(Account_Number, Balance);
        withdrawBtn.setEnabled(true);
        tranBtn.setEnabled(true);
        depositBtn.setEnabled(true);
        balanceBtn.setEnabled(true);
        reactivateBtn.setVisible(false);
        alert("Account Reactivated");
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET active=? WHERE accountNumber=?;");
            ps.setBoolean(1, Active);
            ps.setLong(2, Account_Number);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
	 * Changes global variable Balance 
	 * Does not allow for insufficient funds to be withdrawn
	 * Writes new balance to file.
	 * */
    private void withdraw(double amnt) {
        if (amnt > 0) {
            if (Balance - amnt >= 0) {
                Balance -= amnt;
                alert("Amount Succesfully Withdrawn");
                Transaction t = new Transaction("Withdraw from atm", amnt, Transaction.getCurrentTimestamp(), Account_Number, -1);
                Transaction.submitTransactions(conn, t);
                writeAccnt(Account_Number, Balance);
            } else {
                alert("Withdraw Failed: Insufficient Funds");
            }

        } else {
            alert("Amount cannot be negative");
        }
    }

    /*
	 * Uses the Alert class to tell user of any pertinent information
	 * This opens a new screen that needs to be acknowledged
	 * */
    private void alert(String text) {
        Alert a = new Alert(text);
        a.setVisible(true);

    }

    /*
	 * Adds amnt to the current balance
	 * then writes the balance to the DB
	 * */
    private void deposit(Double amnt) {
        if (amnt > 0) {
            Balance += amnt;
            alert("Amount Deposited. New Balance is $" + (String.format("%.2f", Balance)) + ".");
            Transaction t = new Transaction("Deposit at ATM", amnt, Transaction.getCurrentTimestamp(), Account_Number, -1);
            Transaction.submitTransactions(conn, t);
            writeAccnt(Account_Number, Balance);
        } else {
            alert("Amount cannot be negative");
        }
    }

    /*
	 * Writes new password to file and checks if it fits criteria
	 * Does not allow spaces to be used
	 * Allows for any other character to be used.
	 * Alerts user to any changes or errors that occur
	 * */
    private void changePassword(String newPass) {
        if (newPass.split(" ").length == 1) {
            try {
                PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET password=? WHERE accountNumber=?;");
                ps.setString(1, newPass);
                ps.setLong(2, Account_Number);
                ps.executeUpdate();
                alert("Password has been updated.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            alert("Password not changed: Cannot contain spaces");
        }
    }

    /*
	 * This is used to hide all of the buttons on screen when changing scenes.
	 * e.g. going from homescreen to balance screen and going back to home screen
	 * */
    private void clearScreen() {//Used to hide home screen buttons
        for (Component c : contentPane.getComponents()) {
            c.setVisible(false);
        }
    }

    /*
	 * Displays the user's current balance.
	 * Uses string format to allow for when the user has more or less that 2 decimal places.
	 * */
    private void checkBalance() {
        clearScreen();
        returnBtn = new JButton("\u2190 Return");
        returnBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        returnBtn.setBounds(10, 11, 134, 23);
        contentPane.add(returnBtn);
        returnBtn.addActionListener(this);
        returnBtn.setVisible(true);
        inbeddedLabel = new JLabel("Your Balance is: $" + ((String.format("%.2f", Balance))));
        inbeddedLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        inbeddedLabel.setBounds(10, 160, 550, 20);
        contentPane.add(inbeddedLabel);

    }

    //Used to exit the application.
    private void exit() {
        System.exit(0);
    }

    /*
	 * Transfers funds to another account.
	 * Will only transfer if other account exists otherwise it fails.
	 * User must have sufficient funds.
	 * Cannot transfer to the users own account.
	 * Cannot use negative amounts
	 * Uses its own write method because writeAccnt() cannot handle writing data for other accounts.
	 * */
    private void transfer(long accntNumber, double amnt) {
        if (Balance < amnt) {
            alert("Insuffcient Funds: Transfer Cancelled.");
        } else if (accntNumber == Account_Number) {
            alert("Transfer Failed: Cannot Transfer to own Account");
        } else if (checkExist(accntNumber)) {
            if (amnt > 0) {
                Balance -= amnt;
                double newBalance = 0.0;
                try {
                    PreparedStatement ps = conn.prepareStatement("SELECT Balance FROM accounts WHERE accountNumber=?;");
                    ps.setLong(1, accntNumber);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        newBalance = rs.getDouble("Balance");
                    }
                    newBalance += amnt;
                    writeAccnt(accntNumber, newBalance);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Transaction t = new Transaction("Transfer from " + Account_Number +" to "+accntNumber , amnt, Transaction.getCurrentTimestamp(), Account_Number, accntNumber);
                Transaction.submitTransactions(conn, t);
                writeAccnt(Account_Number, Balance);
                alert("Transfer Successful");
            }
            /* else if (amnt < 0 && Account_Number == 8675309) {
                Balance -= amnt;
                writeAccnt(Account_Number, Balance);
                alert("Transfer Successful");
            } */
            else {
                alert("Cannot transfer negative amounts");
            }
        } else {
            alert("Account does not exist. Please check Account Number and try again.");

        }
    }

    /*
	 * Writes all current information, name, balance, and whether it is active or not.
	 * Does not write the password because that is in a different file
	 * */
    private void writeAccnt(long accntNum, double newBal) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET balance=? WHERE accountNumber=?;");
            ps.setDouble(1, newBal);
            ps.setLong(2, accntNum);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
	 * Used to check an account exists
	 * Used when you want this account to interact with other account holders
	 * Does not check if that user is Active.
	 * */
    private boolean checkExist(long accntNumber) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT count(1)\n"
                    + "FROM accounts\n"
                    + "WHERE accountNumber =?;");
            ps.setLong(1, accntNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
