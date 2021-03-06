package edu.cpp.hci.web.db.rmp.repository;

import edu.cpp.hci.web.entity.rmp.RMPRatingEntity;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RMPRatingRepository extends Repository<RMPRatingEntity, Integer> {

    @Transactional(readOnly = true)
    RMPRatingEntity findById(Integer id);

    @Transactional(readOnly = true)
    List<RMPRatingEntity> findAllByProfessorId(Integer professorId);

    RMPRatingEntity save(RMPRatingEntity entity);
}
