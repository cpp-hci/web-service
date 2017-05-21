package edu.cpp.hci.web.db;


import edu.cpp.hci.scrapers.rmp.model.rating.RMPRatingDTO;
import edu.cpp.hci.web.entity.RMPRatingEntity;

import java.util.List;

public interface RMPRatingDB {
    List<RMPRatingDTO> getRatings(Integer professorId);
    List<RMPRatingDTO> putRatings(List<RMPRatingDTO> ratings, Integer professorId);
}