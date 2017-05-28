package edu.cpp.hci.web.db.koofers.repository;

import edu.cpp.hci.web.entity.koofers.KoofersProfessorEntity;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KoofersProfessorRepository extends Repository<KoofersProfessorEntity, Integer> {
    @Transactional(readOnly = true)
    KoofersProfessorEntity findById(Integer id);

    @Transactional(readOnly = true)
    List<KoofersProfessorEntity> findAllByNameAndSchool(String name, String school);

    KoofersProfessorEntity save(KoofersProfessorEntity entity);
}
