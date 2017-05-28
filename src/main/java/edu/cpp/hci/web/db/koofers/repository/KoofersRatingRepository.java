package edu.cpp.hci.web.db.koofers.repository;

import edu.cpp.hci.web.entity.koofers.KoofersRatingEntity;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KoofersRatingRepository extends Repository<KoofersRatingEntity, Integer> {

    @Transactional(readOnly = true)
    KoofersRatingEntity findById(Integer id);

    @Transactional(readOnly = true)
    List<KoofersRatingEntity> findAllByProfessorId(Integer professorId);

    KoofersRatingEntity save(KoofersRatingEntity entity);
}
