package caveatemptor.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR_NAME)
    private Long id;

    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BillingDetails> billingDetailsList = new ArrayList<>();

    @ElementCollection
    //@CollectionTable(name = "ADDRESS", uniqueConstraints = @UniqueConstraint(columnNames = {"USER_ID","ADDRESSTYPE"}))
    private Set<Address> addresses = new HashSet<>();


    protected User() {
    }

    public User(String userName, String firstName, String lastName, List<BillingDetails> billingDetailsList) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.billingDetailsList = billingDetailsList;
    }

    public boolean hasAddressOfType(Address.Type type){
        return addresses.stream().anyMatch(address -> address.getAddressType().equals(type));
    }

    public void addNewAddressOfType(Address address){
        if(hasAddressOfType(address.getAddressType())){
            addresses.remove(address);
        }
        addresses.add(address);
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<BillingDetails> getBillingDetailsList() {
        return billingDetailsList;
    }

    public void setBillingDetailsList(List<BillingDetails> billingDetailsList) {
        this.billingDetailsList = billingDetailsList;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }
}
