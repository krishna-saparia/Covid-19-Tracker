package com.krishnasaparia.Covid19.controllers;

import com.krishnasaparia.Covid19.models.LocationState;
import com.krishnasaparia.Covid19.services.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CovidDataService covidDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationState> allstats = covidDataService.getAllStats();
        int totalCases = allstats.stream().mapToInt(stat -> stat.getLatestCases()).sum();
        model.addAttribute("locationstate",allstats);
        model.addAttribute("totalReportedCases",totalCases);
        return "index";
    }
}
