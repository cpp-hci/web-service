package edu.cpp.hci.web.db.rmp.repository;

import edu.cpp.hci.web.entity.rmp.RMPProfessorEntity;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RMPProfessorRepository extends Repository<RMPProfessorEntity, Integer> {
    @Transactional(readOnly = true)
    RMPProfessorEntity findById(Integer id);

    @Transactional(readOnly = true)
    List<RMPProfessorEntity> findAllByNameAndSchool(String name, String school);

    RMPProfessorEntity save(RMPProfessorEntity entity);

}
