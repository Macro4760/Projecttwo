package Summoner.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	    @Value("${riot.api.key:NOT_FOUND}")
	    private String riotApiKey;

	    @GetMapping("/riotKey")
	    public String getRiotApiKey() {
	        return "API Key: " + riotApiKey;
	    }
	}

