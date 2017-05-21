package edu.cpp.hci.web.controller;

import edu.cpp.hci.scrapers.exceptions.NoResultsException;
import edu.cpp.hci.scrapers.rmp.RateMyProfessorWebScaper;
import edu.cpp.hci.scrapers.rmp.model.professor.RMPProfessorDTO;
import edu.cpp.hci.scrapers.rmp.model.rating.RMPRatingDTO;
import edu.cpp.hci.web.db.RMPProfessorDB;
import edu.cpp.hci.web.db.RMPRatingDB;
import edu.cpp.hci.web.db.RMPTagDB;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Logger log = Logger.getLogger(RMPController.class);

    @Autowired
    private RMPTagDB tagDB;

    @Autowired
    private RMPProfessorDB professorDB;

    @Autowired
    private RMPRatingDB ratingDB;

    @RequestMapping(method = RequestMethod.GET, path = "/scrape")
    public @ResponseBody List<RMPProfessorDTO> scrape(@RequestParam(value = "name") String name, @RequestParam(value = "school") String school) {
        log.info("RMP scrape request: " + name + " " + school);
        RateMyProfessorWebScaper rateMyProfessorWebScaper = new RateMyProfessorWebScaper(name, school);
        try {
            List<RMPProfessorDTO> results = rateMyProfessorWebScaper.fetch();
            log.info(results);
            return professorDB.putProfessors(results);
        } catch (NoResultsException | IOException e) {
            log.trace(e);
        }
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/rating")
    public @ResponseBody List<RMPRatingDTO> rating(@RequestParam(value = "professorId") Integer professorId) {
        log.info("RMP Review request: " + professorId);
        return ratingDB.getRatings(professorId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/professor")
    public @ResponseBody List<RMPProfessorDTO> professor(@RequestParam(value = "name") String name,
                                    @RequestParam(value = "school") String school) {
        log.info("RMP Professor request: " + name + ", " + school);
        List<RMPProfessorDTO> allByNameAndSchool = professorDB.getProfessorsByNameAndSchool(name, school);
        if (allByNameAndSchool.size() > 0) {
            log.info("Found professor info on DB, using cached results: " + allByNameAndSchool.toString());
            return allByNameAndSchool;
        } else {
            return scrape(name, school);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/tag")
    public @ResponseBody List<String> tag(@RequestParam(value = "ratingId") Integer ratingId) {
        log.info("RMP Tag request: " + ratingId);
        return tagDB.getTags(ratingId);
    }
}
