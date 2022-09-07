package hr.java.production.model;

import java.math.BigDecimal;
import java.util.Objects;

public record Discount(BigDecimal discountAmount) {

    public Discount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;
        Discount discount = (Discount) o;
        return discountAmount.equals(discount.discountAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountAmount);
    }
}
