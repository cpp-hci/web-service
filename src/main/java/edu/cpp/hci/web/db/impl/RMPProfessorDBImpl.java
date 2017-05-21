package edu.cpp.hci.web.db.impl;

import edu.cpp.hci.scrapers.rmp.model.professor.RMPProfessorDTO;
import edu.cpp.hci.web.db.RMPProfessorDB;
import edu.cpp.hci.web.db.RMPRatingDB;
import edu.cpp.hci.web.db.repository.rmp.RMPProfessorRepository;
import edu.cpp.hci.web.entity.EntityFactory;
import edu.cpp.hci.web.entity.RMPProfessorEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class RMPProfessorDBImpl implements RMPProfessorDB{


    private Logger log = Logger.getLogger(RMPProfessorDBImpl.class);

    @Autowired
    private RMPProfessorRepository professorRepository;

    @Autowired
    private RMPRatingDB ratingDB;


    @Override
    public RMPProfessorDTO getProfessor(Integer professorId) {
        RMPProfessorEntity professorEntity = professorRepository.findById(professorId);
        RMPProfessorDTO professorDTO = EntityFactory.toDto(professorEntity);
        professorDTO.setRatings(ratingDB.getRatings(professorId));
        return professorDTO;
    }

    @Override
    public List<RMPProfessorDTO> putProfessors(List<RMPProfessorDTO> professors) {
        for (int i = 0; i < professors.size(); i++) {
            RMPProfessorDTO result = professors.get(i);
            RMPProfessorEntity rmpProfessorEntity = EntityFactory.toEntity(result);
            RMPProfessorEntity save = professorRepository.save(rmpProfessorEntity);
            professors.set(i, EntityFactory.toDto(save));
            professors.get(i).setRatings(ratingDB.putRatings(result.getRatings(), save.getId()));
        }
        return professors;
    }

    @Override
    public List<RMPProfessorDTO> getProfessorsByNameAndSchool(String name, String school) {
        List<RMPProfessorEntity> allByNameAndSchool = professorRepository.findAllByNameAndSchool(name, school);
        List<RMPProfessorDTO> professorDTOS = allByNameAndSchool.stream().map(EntityFactory::toDto).collect(Collectors.toList());
        for (RMPProfessorDTO professorDTO : professorDTOS) {
            int professorId = professorDTO.getId();
            professorDTO.setRatings(ratingDB.getRatings(professorId));
        }
        return professorDTOS;
    }
}
