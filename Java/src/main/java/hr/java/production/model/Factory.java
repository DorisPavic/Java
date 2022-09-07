package hr.java.production.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Predstavlja entitet tvornice
 */

public class Factory extends NamedEntity{
    private Address  address;
    private Set<Item> items;
    private City grad;

    /**
     * Služi za kreiranje objekta koji predstavlja konkretnu tvornicu
     * @param name predstavlja ime tvornice
     * @param address predstavlja adresu tvornice
     * @param items predstavlja artikle koji se proizvode u tvornici
     */

    public Factory(String name, Address address, Set<Item> items, City grad) {
        super(name);
        this.address = address;
        this.items = items;
        this.grad = grad;
    }

    public City getGrad() {
        return grad;
    }

    public void setGrad(City grad) {
        this.grad = grad;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Factory)) return false;
        if (!super.equals(o)) return false;
        Factory factory = (Factory) o;
        return getAddress().equals(factory.getAddress()) && getItems().equals(factory.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAddress(), getItems());
    }
}
