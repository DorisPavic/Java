package hr.java.production.model;

import java.util.Objects;

/**
 * Predstavlja entitet adrese
 */

public class Address {

    private City city;
    private String street;
    private String houseNumber;

    /**
     * Služi za kreiranje objekta adrese
     */

    public static class Builder {

        private City city;
        private String street;
        private String houseNumber;

        public Builder(City city) {
            this.city = city;
            this.street = street;
            this.houseNumber = houseNumber;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder houseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }


        public Address build() {
            Address address = new Address();
            address.street = this.street;
            address.houseNumber = this.houseNumber;
            return address;
        }
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getCity() == address.getCity() && getStreet().equals(address.getStreet()) && getHouseNumber().equals(address.getHouseNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getHouseNumber());
    }
}
