package hr.java.production.model;

public sealed interface Tehnical permits Laptop {
    int calculateGaranciju();
}
