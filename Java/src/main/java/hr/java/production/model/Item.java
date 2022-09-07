package hr.java.production.model;

import java.math.BigDecimal;
import java.util.Objects;


/**
 * Predstavlja entitet artikla
 */


public class Item extends NamedEntity implements Comparable<Item>{
    private Category category;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal length;
    private BigDecimal productionCost;
    private BigDecimal sellingPrice;
    private BigDecimal popust;
    private BigDecimal cijenaSPopustom;
    private Discount discount;
    private String sifraArtikla;

    /**
     * Služi za kreiranje objekta koji predstavlja konkretan artikl
     * @param name predstavlja naziv artikla
     * @param category predstavlja kategoriju artikla
     * @param width predstavlja širinu artikla
     * @param height predstavlja visinu artikla
     * @param length predstavlja dužinu artikla
     * @param productionCost predstavlja cijenu proizvodnje artikla
     * @param sellingPrice predstavlja osnovnu prodajnu cijenu artikla
     * @param popust predstavlja popust na osnovnu cijenu artikla
     * @param cijenaSPopustom predstavlja konačnu cijenu artikla s popustom
     * @param sifraArtikla predstavlja jedinstvenu šifru objekta
     */

    public Item(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length,
                BigDecimal productionCost, BigDecimal sellingPrice, BigDecimal popust, BigDecimal cijenaSPopustom, String sifraArtikla) {
        super(name);
        this.category = category;
        this.width = width;
        this.height = height;
        this.length = length;
        this.productionCost = productionCost;
        this.sellingPrice = sellingPrice;
        this.discount=new Discount(popust);
        this.cijenaSPopustom=cijenaSPopustom;
        this.sifraArtikla = sifraArtikla;
    }

    public String getSifraArtikla() {
        return sifraArtikla;
    }

    public void setSifraArtikla(String sifraArtikla) {
        this.sifraArtikla = sifraArtikla;
    }

    public Discount getDiscount() {
        return discount;
    }

    public BigDecimal getPopust() {
        return popust;
    }

    public void setPopust(BigDecimal popust) {
        this.popust = popust;
    }

    public BigDecimal getCijenaSPopustom() {
        return cijenaSPopustom;
    }

    public void setCijenaSPopustom(BigDecimal cijenaSPopustom) {
        this.cijenaSPopustom = cijenaSPopustom;
    }



    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(BigDecimal productionCost) {
        this.productionCost = productionCost;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }


    @Override
    public int compareTo(Item compareItem) {
        int comparePrice =((Item) compareItem).getCijenaSPopustom().intValue();
        if(this.sellingPrice.intValue() - comparePrice == 0){
            String compareName=((Item) compareItem).getName();
            return this.name.compareTo(compareName);
        }
        return this.sellingPrice.intValue() - comparePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return getCategory().equals(item.getCategory()) && getWidth().equals(item.getWidth()) && getHeight().equals(item.getHeight()) && getLength().equals(item.getLength()) && getProductionCost().equals(item.getProductionCost()) && getSellingPrice().equals(item.getSellingPrice()) && getPopust().equals(item.getPopust()) && getCijenaSPopustom().equals(item.getCijenaSPopustom()) && getDiscount().equals(item.getDiscount()) && getSifraArtikla().equals(item.getSifraArtikla());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCategory(), getWidth(), getHeight(), getLength(), getProductionCost(), getSellingPrice(), getPopust(), getCijenaSPopustom(), getDiscount(), getSifraArtikla());
    }

    @Override
    public String toString() {
        return "Item{" +
                "category=" + category +
                ", width=" + width +
                ", height=" + height +
                ", length=" + length +
                ", productionCost=" + productionCost +
                ", sellingPrice=" + sellingPrice +
                ", popust=" + popust +
                ", cijenaSPopustom=" + cijenaSPopustom +
                ", discount=" + discount +
                ", sifraArtikla='" + sifraArtikla + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
