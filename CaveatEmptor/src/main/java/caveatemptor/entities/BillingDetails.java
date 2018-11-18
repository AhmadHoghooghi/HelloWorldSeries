package caveatemptor.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetails {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR_NAME)
    private Long id;

    @Column(nullable = false)
    protected String owner;

    @ManyToOne(optional = false)
    private User user;

    public BillingDetails() {
    }

    public BillingDetails(String owner, User user) {
        this.owner = owner;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public User getUser() {
        return user;
    }
}
