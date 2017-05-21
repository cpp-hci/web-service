package edu.cpp.hci.web.db.impl;

import edu.cpp.hci.web.db.RMPTagDB;
import edu.cpp.hci.web.db.repository.rmp.RMPTagRepository;
import edu.cpp.hci.web.entity.RMPTagEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RMPTagDBImpl implements RMPTagDB {


    @Autowired
    private RMPTagRepository rmpTagRepository;

    @Override
    public List<String> getTags(Integer ratingId) {
        List<RMPTagEntity> tags = rmpTagRepository.findAllByRatingId(ratingId);
        return tags.stream().map(RMPTagEntity::getText).collect(Collectors.toList());
    }

    @Override
    public List<RMPTagEntity> putTags(List<String> tags, Integer reviewId) {
        ArrayList<RMPTagEntity> saved = new ArrayList<>();
        for (String tag : tags) {
            RMPTagEntity rmpTagEntity = new RMPTagEntity(reviewId, tag);
            saved.add(rmpTagRepository.save(rmpTagEntity));
        }
        return saved;
    }
}
