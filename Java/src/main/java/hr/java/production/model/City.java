package hr.java.production.model;

public enum City {
    SPLIT("SPLIT",21000),
    ZAGREB("ZAGREB",10000),
    ZADAR("ZADAR",23000),
    DUBROVNIK("DUBROVNIK",20000),
    OSIJEK("OSIJEK",31000),
    RIJEKA("RIJEKA",51000);

    private final String cityName;
    private final Integer postalCode;

    City(String cityName, Integer postalCode) {
        this.cityName = cityName;
        this.postalCode = postalCode;
    }

    public String getCityName() {
        return cityName;
    }

    public Integer getPostalCode() {
        return postalCode;
    }
}
