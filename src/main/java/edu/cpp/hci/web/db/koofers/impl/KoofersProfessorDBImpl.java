package edu.cpp.hci.web.db.koofers.impl;

import edu.cpp.hci.scrapers.koofers.model.professor.KoofersProfessorDTO;
import edu.cpp.hci.scrapers.koofers.model.rating.KoofersRatingDTO;
import edu.cpp.hci.scrapers.rmp.model.professor.RMPProfessorDTO;
import edu.cpp.hci.web.db.koofers.KoofersProfessorDB;
import edu.cpp.hci.web.db.koofers.KoofersRatingDB;
import edu.cpp.hci.web.db.koofers.repository.KoofersProfessorRepository;
import edu.cpp.hci.web.db.rmp.RMPRatingDB;
import edu.cpp.hci.web.db.rmp.impl.RMPProfessorDBImpl;
import edu.cpp.hci.web.db.rmp.repository.RMPProfessorRepository;
import edu.cpp.hci.web.entity.koofers.KoofersEntityFactory;
import edu.cpp.hci.web.entity.koofers.KoofersProfessorEntity;
import edu.cpp.hci.web.entity.rmp.RMPEntityFactory;
import edu.cpp.hci.web.entity.rmp.RMPProfessorEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KoofersProfessorDBImpl implements KoofersProfessorDB{
    private Logger log = Logger.getLogger(RMPProfessorDBImpl.class);

    @Autowired
    private KoofersProfessorRepository professorRepository;

    @Autowired
    private KoofersRatingDB ratingDB;


    @Override
    public KoofersProfessorDTO getProfessor(Integer professorId) {
        KoofersProfessorEntity professorEntity = professorRepository.findById(professorId);
        KoofersProfessorDTO professorDTO = KoofersEntityFactory.toDto(professorEntity);
        professorDTO.setRatings(ratingDB.getRatings(professorId));
        return professorDTO;
    }

    @Override
    public List<KoofersProfessorDTO> putProfessors(List<KoofersProfessorDTO> professors) {
        for (int i = 0; i < professors.size(); i++) {
            KoofersProfessorDTO result = professors.get(i);
            KoofersProfessorEntity professorEntity = KoofersEntityFactory.toEntity(result);
            KoofersProfessorEntity save = professorRepository.save(professorEntity);
            professors.set(i, KoofersEntityFactory.toDto(save));
            professors.get(i).setRatings(ratingDB.putRatings(result.getRatings(), save.getId()));
        }
        return professors;
    }

    @Override
    public List<KoofersProfessorDTO> getProfessorsByNameAndSchool(String name, String school) {
        List<KoofersProfessorEntity> allByNameAndSchool = professorRepository.findAllByNameAndSchool(name, school);
        List<KoofersProfessorDTO> list = new ArrayList<>();
        for (KoofersProfessorEntity koofersProfessorEntity : allByNameAndSchool) {
            int professorId = koofersProfessorEntity.getId();
            KoofersProfessorDTO koofersProfessorDTO = KoofersEntityFactory.toDto(koofersProfessorEntity);
            koofersProfessorDTO.setRatings(ratingDB.getRatings(professorId));
        }
        return list;
    }
}
