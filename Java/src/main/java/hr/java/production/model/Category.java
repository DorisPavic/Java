package hr.java.production.model;

import java.util.Objects;

public class Category extends NamedEntity{
    private String description;

    public Category(String name, String description) {
        super(name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return getDescription().equals(category.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDescription());
    }

    @Override
    public String toString() {
        return "Category{" +
                "description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
