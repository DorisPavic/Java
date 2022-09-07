package hr.java.production.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Predstavlja entitet laptopa
 */

public final class Laptop extends Item implements Tehnical{
    int trajanjeGarantnogRoka;

    /**
     * Služi za kreiranje objekta koji predstavlja artikl laptopa
     * @param name predstavlja naziv laptopa
     * @param category predstavlja kategoriju laptopa
     * @param width predstavlja širinu laptopa
     * @param height predstavlja visinu laptopa
     * @param length predstavlja dužinu laptopa
     * @param productionCost predstavlja cijenu proizvodnje laptopa
     * @param sellingPrice predstavlja osnovnu prodajnu cijenu laptopa
     * @param popust predstavlja popust na cijenu laptopa
     * @param cijenaSPopustom predstavlja konačnu cijenu laptopa s popustom
     * @param trajanjeGarantnogRoka predstavlja garantni rok izražen u mjesecima
     * @param sifraArtikla predstavlja jedinstvenu šifru objekta
     */

    public Laptop(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal popust, BigDecimal cijenaSPopustom, int trajanjeGarantnogRoka, String sifraArtikla) {
        super(name, category, width, height, length, productionCost, sellingPrice, popust, cijenaSPopustom, sifraArtikla);
        this.trajanjeGarantnogRoka = trajanjeGarantnogRoka;
    }

    public int getTrajanjeGarantnogRoka() {
        return trajanjeGarantnogRoka;
    }

    public void setTrajanjeGarantnogRoka(int trajanjeGarantnogRoka) {
        this.trajanjeGarantnogRoka = trajanjeGarantnogRoka;
    }

    @Override
    public int calculateGaranciju() {
        return this.trajanjeGarantnogRoka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Laptop)) return false;
        if (!super.equals(o)) return false;
        Laptop laptop = (Laptop) o;
        return getTrajanjeGarantnogRoka() == laptop.getTrajanjeGarantnogRoka();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTrajanjeGarantnogRoka());
    }
}
