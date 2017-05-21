package edu.cpp.hci.web.controller;

import edu.cpp.hci.scrapers.exceptions.NoResultsException;
import edu.cpp.hci.scrapers.rmp.RateMyProfessorWebScaper;
import edu.cpp.hci.scrapers.rmp.model.professor.RMPProfessorDTO;
import edu.cpp.hci.scrapers.rmp.model.rating.RMPRatingDTO;
import edu.cpp.hci.web.entity.EntityFactory;
import edu.cpp.hci.web.entity.RMPProfessorEntity;
import edu.cpp.hci.web.entity.RMPRatingEntity;
import edu.cpp.hci.web.entity.RMPTagEntity;
import edu.cpp.hci.web.repository.rmp.RMPProfessorRepository;
import edu.cpp.hci.web.repository.rmp.RMPRatingRepository;
import edu.cpp.hci.web.repository.rmp.RMPTagRepository;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/rmp")
public class RMPController {

    private final RMPRatingRepository rmpRatingRepository;

    private final RMPProfessorRepository rmpProfessorRepository;

    private final RMPTagRepository rmpTagRepository;

    private Logger log = Logger.getLogger(RMPController.class);

    @Autowired
    public RMPController(RMPRatingRepository rmpRatingRepository, RMPProfessorRepository rmpProfessorRepository,
                         RMPTagRepository rmpTagRepository) {
        this.rmpRatingRepository = rmpRatingRepository;
        this.rmpProfessorRepository = rmpProfessorRepository;
        this.rmpTagRepository = rmpTagRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/scrape")
    public @ResponseBody List<RMPProfessorDTO> scrape(@RequestParam(value = "name") String name, @RequestParam(value = "school") String school) {
        log.info("RMP scrape request: " + name + " " + school);
        RateMyProfessorWebScaper rateMyProfessorWebScaper = new RateMyProfessorWebScaper(name, school);
        try {
            List<RMPProfessorDTO> results = rateMyProfessorWebScaper.fetch();
            log.info(results);
            return saveProfessors(results);
        } catch (NoResultsException | IOException e) {
            log.trace(e);
        }
        return new ArrayList<>();
    }

    private List<RMPProfessorDTO> saveProfessors(List<RMPProfessorDTO> professors) { // TODO: 5/21/17 these should not go here
        log.info("Saving RMP Professors to DB: " + professors.toString());
        for (int i = 0; i < professors.size(); i++) {
            RMPProfessorDTO result = professors.get(i);
            RMPProfessorEntity rmpProfessorEntity = EntityFactory.toEntity(result);
            RMPProfessorEntity save = rmpProfessorRepository.save(rmpProfessorEntity);
            professors.set(i, EntityFactory.toDto(save));
            professors.get(i).setRatings(saveRatings(result.getRatings(), save.getId())); // TODO: 5/21/17 tags
        }
        return professors;
    }

    private List<RMPRatingDTO> saveRatings(List<RMPRatingDTO> ratings, Integer professorId) {
        log.info("Saving RMP Ratings to DB: " + ratings.toString());
        for (int i = 0; i < ratings.size(); i++) {
            RMPRatingEntity rmpRatingEntity = EntityFactory.toEntity(ratings.get(i), professorId);
            RMPRatingEntity save = rmpRatingRepository.save(rmpRatingEntity);
            RMPRatingDTO saved = EntityFactory.toDto(save);
            List<String> tags = saveTags(ratings.get(i).getTags(), save.getId()).stream()
                .map(RMPTagEntity::getText)
                .collect(Collectors.toList());
            ratings.set(i, saved);
            ratings.get(i).setTags(tags);
        }
        return ratings;
    }

    private List<RMPTagEntity> saveTags(List<String> tags, Integer reviewId) {
        ArrayList<RMPTagEntity> saved = new ArrayList<>();
        for (String tag : tags) {
            RMPTagEntity rmpTagEntity = new RMPTagEntity(reviewId, tag);
            saved.add(rmpTagRepository.save(rmpTagEntity));
        }
        return saved;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/rating")
    public @ResponseBody List<RMPRatingDTO> rating(@RequestParam(value = "professorId") Integer professorId) {
        log.info("RMP Review request: " + professorId);
        List<RMPRatingEntity> allByProfessorId = rmpRatingRepository.findAllByProfessorId(professorId);
        List<RMPRatingDTO> ratingDTOS = allByProfessorId.stream().map(EntityFactory::toDto).collect(Collectors.toList());
        for (RMPRatingDTO ratingDTO : ratingDTOS) {
            ratingDTO.setTags(tag(ratingDTO.getId()).stream().map(RMPTagEntity::getText).collect(Collectors.toList()));
        }
        return ratingDTOS;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/professor")
    public @ResponseBody List<RMPProfessorDTO> professor(@RequestParam(value = "name") String name,
                                    @RequestParam(value = "school") String school) {
        log.info("RMP Professor request: " + name + ", " + school);
        List<RMPProfessorEntity> allByNameAndSchool = rmpProfessorRepository.findAllByNameAndSchool(name, school);
        if (allByNameAndSchool.size() > 0) {
            return useCachedProfessor(allByNameAndSchool);
        } else {
            return scrape(name, school);
        }
    }

    private List<RMPProfessorDTO> useCachedProfessor(List<RMPProfessorEntity> allByNameAndSchool) {
        List<RMPProfessorDTO> professorDTOS = allByNameAndSchool.stream().map(EntityFactory::toDto).collect(Collectors.toList());
        for (RMPProfessorDTO professorDTO : professorDTOS) {
            int professorId = professorDTO.getId();
            List<RMPRatingDTO> ratings = rating(professorId);
            for (RMPRatingDTO rating : ratings) {
                int ratingId = rating.getId();
                List<RMPTagEntity> tags = tag(ratingId);
                rating.setTags(tags.stream().map(RMPTagEntity::getText).collect(Collectors.toList()));
            }
            professorDTO.setRatings(ratings);

        }
        log.info("Found professor info on DB, using cached results: " + professorDTOS.toString()); // TODO: 5/21/17 fix toString method
        return professorDTOS;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/tag")
    public @ResponseBody List<RMPTagEntity> tag(@RequestParam(value = "ratingId") Integer ratingId) {
        log.info("RMP Tag request: " + ratingId);
        return rmpTagRepository.findAllByRatingId(ratingId);
    }
}
