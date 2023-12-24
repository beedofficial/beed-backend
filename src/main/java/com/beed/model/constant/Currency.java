package com.beed.model.constant;

public enum Currency {
    TURKISH_LIRA("₺", "TL"),
    US_DOLLAR("$", "USD"),
    EURO("€", "EUR");

    private final String symbol;
    private final String code;

    private Currency(String symbol, String code) {
        this.code = code;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCode() {
        return code;
    }
}
