package com.example.dllo.chanyouji.workbox.boxbean;

/**
 * Created by dllo on 16/7/9.
 */
public class ExRatEBean {


    /**
     * currency_from : TWD
     * currency_to : CNY
     * exchange_rate : 0.206
     */

    private String currency_from;
    private String currency_to;
    private String exchange_rate;

    public String getCurrency_from() {
        return currency_from;
    }

    public void setCurrency_from(String currency_from) {
        this.currency_from = currency_from;
    }

    public String getCurrency_to() {
        return currency_to;
    }

    public void setCurrency_to(String currency_to) {
        this.currency_to = currency_to;
    }

    public String getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(String exchange_rate) {
        this.exchange_rate = exchange_rate;
    }
}
