package com.trade.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppPropertyConfig {

    @Value("${app.specifiedAmountOfTime}")
    private long specifiedAmountOfTime;

    public long getSpecifiedAmountOfTime() {
        return specifiedAmountOfTime;
    }
}