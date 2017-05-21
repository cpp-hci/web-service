package edu.cpp.hci.web.db;

import edu.cpp.hci.web.entity.RMPTagEntity;

import java.util.List;

public interface RMPTagDB {
    List<String> getTags(Integer ratingId);

    List<RMPTagEntity> putTags(List<String> tags, Integer ratingId);
}
