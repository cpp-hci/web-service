package edu.cpp.hci.web.db.rmp;


import edu.cpp.hci.scrapers.rmp.model.rating.RMPRatingDTO;

import java.util.List;

public interface RMPRatingDB {
    List<RMPRatingDTO> getRatings(Integer professorId);

    List<RMPRatingDTO> putRatings(List<RMPRatingDTO> ratings, Integer professorId);
}
