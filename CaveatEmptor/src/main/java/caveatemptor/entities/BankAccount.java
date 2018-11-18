package caveatemptor.entities;

import javax.persistence.Column;

public class BankAccount extends BillingDetails {
    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String swift;


    protected BankAccount() {
    }

    public BankAccount(String owner,User user, String account, String bankName, String swift) {
        super(owner,user);
        this.account = account;
        this.bankName = bankName;
        this.swift = swift;
    }

    public String getAccount() {
        return account;
    }

    public String getBankName() {
        return bankName;
    }

    public String getSwift() {
        return swift;
    }
}
