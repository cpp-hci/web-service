package edu.cpp.hci.web.controller;

import edu.cpp.hci.scrapers.exceptions.NoProfessorException;
import edu.cpp.hci.scrapers.exceptions.NoResultsException;
import edu.cpp.hci.scrapers.koofers.KoofersWebScraper;
import edu.cpp.hci.scrapers.koofers.model.professor.KoofersProfessorDTO;
import edu.cpp.hci.scrapers.koofers.model.rating.KoofersRatingDTO;
import edu.cpp.hci.web.db.koofers.KoofersProfessorDB;
import edu.cpp.hci.web.db.koofers.KoofersRatingDB;
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
@RequestMapping("/api/koofers")
public class KoofersController {

    private Logger log = Logger.getLogger(KoofersController.class);

    @Autowired
    private KoofersProfessorDB professorDB;

    @Autowired
    private KoofersRatingDB ratingDB;

    @RequestMapping(method = RequestMethod.GET, path = "/scrape")
    public @ResponseBody
    List<KoofersProfessorDTO> scrape(@RequestParam(value = "name") String name, @RequestParam(value = "school") String school) {
        log.info("Koofers scrape request: " + name + " " + school);
        KoofersWebScraper scraper = new KoofersWebScraper(name, school);
        try {
            List<KoofersProfessorDTO> results = scraper.fetch();
            log.info(results);
            return professorDB.putProfessors(results);
        } catch (NoResultsException | IOException | NoProfessorException e) {
            log.trace(e);
        }
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/rating")
    public @ResponseBody
    List<KoofersRatingDTO> rating(@RequestParam(value = "professorId") Integer professorId) {
        log.info("Koofers Review request: " + professorId);
        return ratingDB.getRatings(professorId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/professor")
    public @ResponseBody
    List<KoofersProfessorDTO> professor(@RequestParam(value = "name") String name,
                                        @RequestParam(value = "school") String school) {
        log.info("Koofers Professor request: " + name + ", " + school);
        List<KoofersProfessorDTO> allByNameAndSchool = professorDB.getProfessorsByNameAndSchool(name, school);
        if (allByNameAndSchool.size() > 0) {
            log.info("Found professor info on DB, using cached results: " + allByNameAndSchool.toString());
            return allByNameAndSchool;
        } else {
            return scrape(name, school);
        }
    }
}
