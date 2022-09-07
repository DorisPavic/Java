package hr.java.production.model;

import java.math.BigDecimal;

public interface Edible {
    int calculateKilocalories();
    BigDecimal calculatePrice();
}
