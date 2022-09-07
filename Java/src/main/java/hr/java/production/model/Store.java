package hr.java.production.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 * Predstavlja entitet trgovine
 */

public class Store extends NamedEntity {
    private String webAddress;
    private Set<Item> items;

    /**
     * Služi za kreiranje objekta koji predstavlja konkretnu trgovinu
     * @param name predstavlja ime trgovine
     * @param webAddress predstavlja web adresu trgovine
     * @param items predstavlja artikle koji se prodaju u trgovini
     */

    public Store(String name, String webAddress, Set<Item> items) {
        super(name);
        this.webAddress = webAddress;
        this.items = items;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
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
        if (!(o instanceof Store)) return false;
        if (!super.equals(o)) return false;
        Store store = (Store) o;
        return getWebAddress().equals(store.getWebAddress()) && getItems().equals(store.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWebAddress(), getItems());
    }
}
