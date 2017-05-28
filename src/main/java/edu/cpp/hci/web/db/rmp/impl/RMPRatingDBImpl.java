package edu.cpp.hci.web.db.rmp.impl;

import edu.cpp.hci.scrapers.rmp.model.rating.RMPRatingDTO;
import edu.cpp.hci.web.db.rmp.RMPRatingDB;
import edu.cpp.hci.web.db.rmp.RMPTagDB;
import edu.cpp.hci.web.db.rmp.repository.RMPRatingRepository;
import edu.cpp.hci.web.entity.rmp.RMPEntityFactory;
import edu.cpp.hci.web.entity.rmp.RMPRatingEntity;
import edu.cpp.hci.web.entity.rmp.RMPTagEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class RMPRatingDBImpl implements RMPRatingDB {

    @Autowired
    private RMPRatingRepository rmpRatingRepository;

    @Autowired
    private RMPTagDB rmpTagDB;

    @Override
    public List<RMPRatingDTO> getRatings(Integer professorId) {
        List<RMPRatingEntity> allByProfessorId = rmpRatingRepository.findAllByProfessorId(professorId);
        List<RMPRatingDTO> ratingDTOS = allByProfessorId.stream().map(RMPEntityFactory::toDto).collect(Collectors.toList());
        for (RMPRatingDTO ratingDTO : ratingDTOS) {
            ratingDTO.setTags(rmpTagDB.getTags(ratingDTO.getId()));
        }
        return ratingDTOS;
    }

    @Override
    public List<RMPRatingDTO> putRatings(List<RMPRatingDTO> ratings, Integer professorId) {
        for (int i = 0; i < ratings.size(); i++) {
            RMPRatingEntity rmpRatingEntity = RMPEntityFactory.toEntity(ratings.get(i), professorId);
            RMPRatingEntity save = rmpRatingRepository.save(rmpRatingEntity);
            RMPRatingDTO saved = RMPEntityFactory.toDto(save);
            List<String> tags = rmpTagDB.putTags(ratings.get(i).getTags(), save.getId()).stream()
                .map(RMPTagEntity::getText)
                .collect(Collectors.toList());
            ratings.set(i, saved);
            ratings.get(i).setTags(tags);
        }
        return ratings;
    }
}
