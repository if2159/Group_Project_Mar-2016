package ATM;

import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Transaction {
    private final double amount;
    private final Long destinationAccountNumber;
    private final long originAccountNumber;
    private final Timestamp time;
    private final String description;
    public Transaction(String desc, double amount, Timestamp time, long accountNumber, long destinationAccountNumber){
            this.amount = amount;
            this.originAccountNumber = accountNumber;
            this.destinationAccountNumber = destinationAccountNumber;
            this.time = time;
            this.description = desc;
    }

    public long getDestinationAccountNumber() {
        return this.destinationAccountNumber;
    }



    public double getAmount() {
        return this.amount;
    }

    public long getOriginAccountNumber() {
        return this.originAccountNumber;
    }

    public Timestamp getTime() {
        return this.time;
    }
    
    public static void submitTransactions(Connection conn, Transaction t){
        try{   
            PreparedStatement ps = conn.prepareStatement("INSERT INTO mysql.transactions (`originAccountNumber`, `destinationAccount`, amount, `Description`, `timeStamp`) "
                    + "VALUES (?,?,?,?,?);");

            
            ps.setLong(1, t.getOriginAccountNumber());
            ps.setLong(2, t.getDestinationAccountNumber());
            ps.setDouble(3, t.getAmount());
            ps.setString(4,t.getDescription());
            ps.setTimestamp(5, t.getTime());
            
            ps.executeUpdate();
            System.out.println("Success");

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public String getDescription(){
        return this.description;
    }
    public static Timestamp getCurrentTimestamp() {

	Date today = new Date();
	return new java.sql.Timestamp(today.getTime());

}
    
    public Object[] getAsArray(){
        Object []ar = new Object[3];
        /* time type whoTo amount*/
        ar[0] = time;
        ar[1] = description;
        ar[2] = (String.format("%.2f", amount));
        return ar;
    }

    
    
    
}
