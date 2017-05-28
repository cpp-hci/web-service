package edu.cpp.hci.web.db.rmp;

import edu.cpp.hci.web.entity.rmp.RMPTagEntity;

import java.util.List;

public interface RMPTagDB {
    List<String> getTags(Integer ratingId);

    List<RMPTagEntity> putTags(List<String> tags, Integer ratingId);
}
