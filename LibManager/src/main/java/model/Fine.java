package model;

import java.math.BigDecimal;

public class Fine {
    private int fineID;
    private int loanID;
    private String reason;
    private BigDecimal amount;

    // Constructors
    public Fine() {}

    public Fine(int fineID, int loanID, String reason, BigDecimal amount) {
        this.fineID = fineID;
        this.loanID = loanID;
        this.reason = reason;
        this.amount = amount;
    }

    // Getters and Setters
    public int getFineID() { return fineID; }
    public void setFineID(int fineID) { this.fineID = fineID; }

    public int getLoanID() { return loanID; }
    public void setLoanID(int loanID) { this.loanID = loanID; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    @Override
    public String toString() {
        return "Fine{" +
               "fineID=" + fineID +
               ", loanID=" + loanID +
               ", reason='" + reason + '\'' +
               ", amount=" + amount +
               '}';
    }
}
