package caveatemptor.entities;

import javax.persistence.Column;

public class CreditCard extends BillingDetails{
    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String expMonth;

    @Column(nullable = false)
    private String exmYear;

    protected CreditCard() {
    }

    public CreditCard(String owner,User user, String number, String expMonth, String exmYear) {
        super(owner,user);
        this.number = number;
        this.expMonth = expMonth;
        this.exmYear = exmYear;
    }

    public String getNumber() {
        return number;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public String getExmYear() {
        return exmYear;
    }
}
