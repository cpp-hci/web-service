package edu.cpp.hci.web.db.rmp.repository;

import edu.cpp.hci.web.entity.rmp.RMPTagEntity;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RMPTagRepository extends Repository<RMPTagEntity, Integer> {

    @Transactional(readOnly = true)
    RMPTagEntity findById(Integer id);

    @Transactional(readOnly = true)
    List<RMPTagEntity> findAllByRatingId(Integer ratingId);

    RMPTagEntity save(RMPTagEntity entity);
}
