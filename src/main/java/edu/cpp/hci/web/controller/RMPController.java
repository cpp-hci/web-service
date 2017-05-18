package edu.cpp.hci.web.controller;

import edu.cpp.hci.scrapers.exceptions.NoResultsException;
import edu.cpp.hci.scrapers.rmp.RateMyProfessorWebScaper;
import edu.cpp.hci.scrapers.rmp.model.professor.RMPProfessorDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/rmp")
public class RMPController {

    @RequestMapping(method = RequestMethod.GET, path = "/scrape")
    public @ResponseBody List<RMPProfessorDTO> scrape(@RequestParam(value="name") String name, @RequestParam(value = "school") String school){
        RateMyProfessorWebScaper rateMyProfessorWebScaper = new RateMyProfessorWebScaper(name, school);
        try {
            return rateMyProfessorWebScaper.fetch();
        } catch (NoResultsException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
