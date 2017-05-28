package edu.cpp.hci.web.db.koofers.impl;

import edu.cpp.hci.scrapers.koofers.model.rating.KoofersRatingDTO;
import edu.cpp.hci.web.controller.RMPController;
import edu.cpp.hci.web.db.koofers.KoofersRatingDB;
import edu.cpp.hci.web.db.koofers.repository.KoofersRatingRepository;
import edu.cpp.hci.web.entity.koofers.KoofersEntityFactory;
import edu.cpp.hci.web.entity.koofers.KoofersRatingEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class KoofersRatingDBImpl implements KoofersRatingDB {

    private Logger log = Logger.getLogger(KoofersRatingDBImpl.class);

    @Autowired
    private KoofersRatingRepository ratingRepository;

    @Override
    public List<KoofersRatingDTO> getRatings(Integer professorId) {
        List<KoofersRatingEntity> ratings = ratingRepository.findAllByProfessorId(professorId);
        return ratings.stream().map(KoofersEntityFactory::toDto).collect(Collectors.toList());
    }

    @Override
    public List<KoofersRatingDTO> putRatings(List<KoofersRatingDTO> ratings, Integer professorId) {
        return ratings.stream()
            .map(e -> KoofersEntityFactory.toEntity(e,professorId))
            .map(e -> ratingRepository.save(e))
            .map(KoofersEntityFactory::toDto)
            .collect(Collectors.toList());
    }
}
