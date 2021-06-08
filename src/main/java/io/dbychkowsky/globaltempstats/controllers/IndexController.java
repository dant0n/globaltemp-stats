package io.dbychkowsky.globaltempstats.controllers;

import io.dbychkowsky.globaltempstats.models.TempStats;
import io.dbychkowsky.globaltempstats.services.GlobalStatsDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    private final GlobalStatsDataService globalStatsDataService;

    public IndexController(GlobalStatsDataService globalStatsDataService) {
        this.globalStatsDataService = globalStatsDataService;
    }

    @GetMapping("/")
    public String index(Model model) {
        //TODO: Add switcher to a different data source
        List<TempStats> tempStats = globalStatsDataService.getTempStats();
        model.addAttribute("tempStats", tempStats);
        model.addAttribute("currentTemp", tempStats.get(0).getMeanTemp());
        return "index";
    }
}
