package hr.java.production.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Predstavlja entitet brašna
 */

public class Brasno extends Item implements Edible{
    private BigDecimal weight;
    private final int konstantaBrojKalorijaBrasna =70;

    /**
     * Služi za kreiranje objekta koji predstavlja artikl brašna
     * @param name predstavlja naziv brašna
     * @param category predstavlja kategoriju brašna
     * @param width predstavlja sirinu brašna
     * @param height predstavlja visinu brašna
     * @param length predstavlja duzinu brašna
     * @param productionCost predstavlja cijenu proizvodnje brašna
     * @param sellingPrice predstavlja osnovnu prodajnu cijenu brašna po kilogramu
     * @param weight predstavlja tezinu pakiranja brašna
     * @param popust predstavlja popust na cijenu brašna
     * @param cijenaSPopustom predstavlja konačnu cijenu brašna s obzirom na tezinu pakiranja s uračunatim popustom
     * @param sifraArtikla predstavlja jedinstvenu šifru objekta
     */

    public Brasno(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length,
                  BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal weight, BigDecimal popust, BigDecimal cijenaSPopustom, String sifraArtikla) {
        super(name, category, width, height, length, productionCost, sellingPrice, popust, cijenaSPopustom, sifraArtikla);
        this.weight=weight;
    }


    public int getKonstantaBrojKalorijaBrasna() {
        return konstantaBrojKalorijaBrasna;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public int calculateKilocalories() {

        return this.weight.intValue()*(konstantaBrojKalorijaBrasna);
    }

    @Override
    public BigDecimal calculatePrice() {

        return this.weight.multiply(this.getSellingPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brasno)) return false;
        if (!super.equals(o)) return false;
        Brasno brasno = (Brasno) o;
        return getKonstantaBrojKalorijaBrasna() == brasno.getKonstantaBrojKalorijaBrasna() && getWeight().equals(brasno.getWeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWeight(), getKonstantaBrojKalorijaBrasna());
    }
}
