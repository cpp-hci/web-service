package edu.cpp.hci.web.db.koofers;

import edu.cpp.hci.scrapers.koofers.model.rating.KoofersRatingDTO;

import java.util.List;

public interface KoofersRatingDB {
    List<KoofersRatingDTO> getRatings(Integer professorId);

    List<KoofersRatingDTO> putRatings(List<KoofersRatingDTO> ratings, Integer professorId);
}
