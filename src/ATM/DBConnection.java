package ATM;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author Ian Fennen
 */
public class DBConnection {

    private Connection conn = null;
    private final Properties DBProps;

    public DBConnection() {
        DBProps = new Properties();
        DBProps.put("user", "root");
        DBProps.put("password", "f0102379");
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", DBProps);

            // importFromFile(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    /* 
     * Only used when first intializing DB can be deleted.
     * 
     * */
 /*
    public void importFromFile(Connection con)throws SQLException{
        Statement stmnt = con.createStatement();
        HashMap <Long, String>accounts=new HashMap<Long, String>();
        try{
            Scanner file = new Scanner(new File("LoginInformation.txt"));
            while(file.hasNext()){
                accounts.put(file.nextLong(),file.nextLine().trim());
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
            
        try{
            Scanner file = new Scanner(new File("AccountInformation.txt"));
            while(file.hasNext()){
                long ID = file.nextLong();
                file.nextLine();
                String firstName = file.nextLine().trim();
                String lastName = file.nextLine().trim();
                double bal = file.nextDouble();
                file.nextLine();
                String act = file.nextLine().trim();
                boolean active;
                if(act.equals("Active")){
                    active = true;}
                else{
                    active = false;}
                stmnt.executeUpdate("INSERT INTO mysql.accounts (`LastName`, `AccountNumber`, `FirstName`, `Balance`, `active`, `password`)"
                        +"VALUES ('"+lastName+"', "+ID+", '"+firstName+"', "+bal+", "+active+", '"+accounts.get(ID)+"')");

            }
            stmnt.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not Found: AccountInformation.txt");
        }
        
        
    
    }
     */
    public long cypher(String pass, byte salt) {
        byte[] ar = new byte[pass.length()];
        for (int i = 0; i < pass.length(); i++) {
            byte c = (byte) (pass.charAt(i));
            c += salt;
            ar[i] = (byte) (c % 128);
        }
        long crypt = 0;
        for (byte i : ar) {
            crypt += i;
        }

        return crypt;
    }

    public static void main(String args[]) {
        new DBConnection();

    }
}
