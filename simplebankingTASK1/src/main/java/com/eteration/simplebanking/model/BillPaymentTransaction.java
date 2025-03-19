package com.eteration.simplebanking.model;

public class BillPaymentTransaction extends  Transaction{

    private String payee;
    public BillPaymentTransaction() {
        super();
    }
    @Override
    public void process(Account account) {
        if (account.getBalance() < this.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal");
        }
        account.setBalance(account.getBalance()-this.getAmount());
    }

    @Override
    public String toString() {
        return "BillPaymentTransaction{" +
                "approvalCode=" + getApprovalCode() +
                ", type='" + getType() + '\'' +
                ", date=" + getDate() +
                ", amount=" + getAmount() +
                ", accountNumber='" + getAccountNumber() + '\'' +
                ", payee='" + payee + '\'' +
                '}';
    }
}
