package com.jaguars.core.enumerate;

public enum APIMProductEnum {
    JAGUARS("jaguars"), 
    ;

    private String productName;

    private APIMProductEnum(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return this.productName;
    }
}
