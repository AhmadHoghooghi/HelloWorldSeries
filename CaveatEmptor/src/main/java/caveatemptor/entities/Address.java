package caveatemptor.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.InputStream;
import java.util.Objects;

@Embeddable
public class Address {
    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type addressType;


    protected Address() {
    }

    public Address(String street, String zipCode, String city, Type addressType) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.addressType = addressType;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public Type getAddressType() {
        return addressType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(city, address.city) &&
                addressType.equals(address.addressType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(street, zipCode, city, addressType);
    }

    public static enum Type {
        HOME, BILLING, SHIPPING
    }
}
