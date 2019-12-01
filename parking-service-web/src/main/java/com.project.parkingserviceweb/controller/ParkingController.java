/*
 * 1/12/19
 * jitendra.kumar@moveinsync.com
 */
package com.project.parkingserviceweb.controller;

import com.project.parkingserviceweb.FileUtil;
import com.project.parkingserviceweb.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping(value = "/parking")
public class ParkingController {

    @Autowired
    ParkingService parkingService;

    @PostMapping
    public void exicuteQuery(@RequestPart("input_file") MultipartFile inputFile){
        try{
            File file = FileUtil.multipartToFile(inputFile);
            parkingService.exicuteQuery(file);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
