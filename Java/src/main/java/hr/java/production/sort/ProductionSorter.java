package hr.java.production.sort;

import hr.java.production.model.Item;

import java.util.Comparator;

public class ProductionSorter implements Comparator<Item> {
    @Override
    public int compare(Item item1, Item item2) {

        if (item1.getCijenaSPopustom().compareTo(item2.getCijenaSPopustom()) > 0) {
            return 1;
        } else if (item1.getCijenaSPopustom().compareTo(item2.getCijenaSPopustom()) < 0) {
            return -1;
        } else {
            if (item1.getName().compareTo(item2.getName()) > 0) {
                return 1;
            } else {
                return -1;
            }
        }

    }
}
