/*
 * 30/11/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.parkingserviceweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping
    public String test(){
        return "parking service is running";
    }
}
