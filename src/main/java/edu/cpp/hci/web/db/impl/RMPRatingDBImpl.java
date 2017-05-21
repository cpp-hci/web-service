package edu.cpp.hci.web.db.impl;

import edu.cpp.hci.scrapers.rmp.model.rating.RMPRatingDTO;
import edu.cpp.hci.web.db.RMPRatingDB;
import edu.cpp.hci.web.db.RMPTagDB;
import edu.cpp.hci.web.db.repository.rmp.RMPRatingRepository;
import edu.cpp.hci.web.entity.EntityFactory;
import edu.cpp.hci.web.entity.RMPRatingEntity;
import edu.cpp.hci.web.entity.RMPTagEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class RMPRatingDBImpl implements RMPRatingDB{

    @Autowired
    private RMPRatingRepository rmpRatingRepository;

    @Autowired
    private RMPTagDB rmpTagDB;

    @Override
    public List<RMPRatingDTO> getRatings(Integer professorId) {
        List<RMPRatingEntity> allByProfessorId = rmpRatingRepository.findAllByProfessorId(professorId);
        List<RMPRatingDTO> ratingDTOS = allByProfessorId.stream().map(EntityFactory::toDto).collect(Collectors.toList());
        for (RMPRatingDTO ratingDTO : ratingDTOS) {
            ratingDTO.setTags(rmpTagDB.getTags(ratingDTO.getId()));
        }
        return ratingDTOS;
    }

    @Override
    public List<RMPRatingDTO> putRatings(List<RMPRatingDTO> ratings, Integer professorId) {
        for (int i = 0; i < ratings.size(); i++) {
            RMPRatingEntity rmpRatingEntity = EntityFactory.toEntity(ratings.get(i), professorId);
            RMPRatingEntity save = rmpRatingRepository.save(rmpRatingEntity);
            RMPRatingDTO saved = EntityFactory.toDto(save);
            List<String> tags = rmpTagDB.putTags(ratings.get(i).getTags(), save.getId()).stream()
                .map(RMPTagEntity::getText)
                .collect(Collectors.toList());
            ratings.set(i, saved);
            ratings.get(i).setTags(tags);
        }
        return ratings;
    }
}
