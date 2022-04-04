package com.trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trade.model.AppPropertyConfig;
/**
 * Application configuration specific configuration
 * @author Vipul
 */
@Controller
public class AppConfigController {

    /**
     * application property config file is loading from application.properties file
     */
	@Autowired
    private AppPropertyConfig appPropertyConfig;

    /**
     * Just to check config value on UI. 
     * @return specified amount of time from application.properties
     */
	@GetMapping(path = "/getSpecifiedAmountOfTime")
    @ResponseBody
    public long getSpecifiedAmountOfTime() {
        return appPropertyConfig.getSpecifiedAmountOfTime();
    }
}