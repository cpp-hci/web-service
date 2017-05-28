package edu.cpp.hci.web.db.rmp.impl;

import edu.cpp.hci.scrapers.rmp.model.professor.RMPProfessorDTO;
import edu.cpp.hci.web.db.rmp.RMPProfessorDB;
import edu.cpp.hci.web.db.rmp.RMPRatingDB;
import edu.cpp.hci.web.db.rmp.repository.RMPProfessorRepository;
import edu.cpp.hci.web.entity.rmp.RMPEntityFactory;
import edu.cpp.hci.web.entity.rmp.RMPProfessorEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class RMPProfessorDBImpl implements RMPProfessorDB {


    private Logger log = Logger.getLogger(RMPProfessorDBImpl.class);

    @Autowired
    private RMPProfessorRepository professorRepository;

    @Autowired
    private RMPRatingDB ratingDB;


    @Override
    public RMPProfessorDTO getProfessor(Integer professorId) {
        RMPProfessorEntity professorEntity = professorRepository.findById(professorId);
        RMPProfessorDTO professorDTO = RMPEntityFactory.toDto(professorEntity);
        professorDTO.setRatings(ratingDB.getRatings(professorId));
        return professorDTO;
    }

    @Override
    public List<RMPProfessorDTO> putProfessors(List<RMPProfessorDTO> professors) {
        for (int i = 0; i < professors.size(); i++) {
            RMPProfessorDTO result = professors.get(i);
            RMPProfessorEntity rmpProfessorEntity = RMPEntityFactory.toEntity(result);
            RMPProfessorEntity save = professorRepository.save(rmpProfessorEntity);
            professors.set(i, RMPEntityFactory.toDto(save));
            professors.get(i).setRatings(ratingDB.putRatings(result.getRatings(), save.getId()));
        }
        return professors;
    }

    @Override
    public List<RMPProfessorDTO> getProfessorsByNameAndSchool(String name, String school) {
        List<RMPProfessorEntity> allByNameAndSchool = professorRepository.findAllByNameAndSchool(name, school);
        List<RMPProfessorDTO> professorDTOS = allByNameAndSchool.stream().map(RMPEntityFactory::toDto).collect(Collectors.toList());
        for (RMPProfessorDTO professorDTO : professorDTOS) {
            int professorId = professorDTO.getId();
            professorDTO.setRatings(ratingDB.getRatings(professorId));
        }
        return professorDTOS;
    }
}
