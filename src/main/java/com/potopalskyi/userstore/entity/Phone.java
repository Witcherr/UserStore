package com.potopalskyi.userstore.entity;

public class Phone {
    private long id;
    private String countryCode;
    private String innerNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getInnerNumber() {
        return innerNumber;
    }

    public void setInnerNumber(String innerNumber) {
        this.innerNumber = innerNumber;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", innerNumber='" + innerNumber + '\'' +
                '}';
    }
}
