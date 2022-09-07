package hr.java.production.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Predstavlja entitet žitarica
 */

public class Zitarice extends Item implements Edible{
    private final int konstantaBrojKalorijaZitarice= 400;
    private BigDecimal weight;

    /**
     * Služi za kreiranje objekta koji predstavlja artikl žitarica
     * @param name predstavlja naziv žitarica
     * @param category predstavlja kategoriju žitarica
     * @param width predstavlja širinu žitarica
     * @param height predstavlja visinu žitarica
     * @param length predstavlja dužinu žitarica
     * @param productionCost predstavlja cijenu proizvodnje žitarica
     * @param sellingPrice predstavlja osnovnu prodajnu cijenu žitarica po kilogramu
     * @param weight predstavlja težinu pakiranja žitarica
     * @param popust predstavlja popust na cijenu žitarica
     * @param cijenaSPopustom predstavlja konačnu cijenu žitarica s obzirom na težinu pakiranja s uračunatim popustom
     * @param sifraArtikla predstavlja jedinstvenu šifru objekta
     */

    public Zitarice(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length,
                    BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal weight, BigDecimal popust, BigDecimal cijenaSPopustom, String sifraArtikla) {
        super(name, category, width, height, length, productionCost, sellingPrice, popust, cijenaSPopustom, sifraArtikla);
        this.weight=weight;
    }


    public int getKonstantaBrojKalorijaZitarice() {
        return konstantaBrojKalorijaZitarice;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public int calculateKilocalories() {
        return this.weight.intValue()*konstantaBrojKalorijaZitarice;
    }

    @Override
    public BigDecimal calculatePrice() {

        return this.weight.multiply(this.getSellingPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Zitarice)) return false;
        if (!super.equals(o)) return false;
        Zitarice zitarice = (Zitarice) o;
        return getKonstantaBrojKalorijaZitarice() == zitarice.getKonstantaBrojKalorijaZitarice() && getWeight().equals(zitarice.getWeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKonstantaBrojKalorijaZitarice(), getWeight());
    }
}
