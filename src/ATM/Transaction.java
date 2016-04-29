package ATM;

import java.sql.Timestamp;

public class Transaction {
    private final String type;
    private final double amount;
    private final Long destinationAccountNumber;
    private final long originAccountNumber;
    private final Timestamp time;
    
    public Transaction(String type, double amount, long accountNumber, Timestamp time, Long destinationAccountNumber){
            this.type= type;
            this.amount = amount;
            this.originAccountNumber = accountNumber;
            this.time = time;
            this.destinationAccountNumber = destinationAccountNumber;

    }

    public long getDestinationAccountNumber() {
        return this.destinationAccountNumber;
    }

    public String getType() {
        return this.type;
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
    
    public Object[] getAsArray(){
        Object []ar = new Object[4];
        /* time type whoTo amount*/
        ar[0] = time;
        ar[1] = type;
        ar[2] = destinationAccountNumber;
        ar[3] = (String.format("%.2f", amount));
        return ar;
    }

    
    
    
}
