package edu.cpp.hci.web.db;


import edu.cpp.hci.scrapers.rmp.model.professor.RMPProfessorDTO;

import java.util.List;

public interface RMPProfessorDB {
    RMPProfessorDTO getProfessor(Integer professorId);

    List<RMPProfessorDTO> putProfessors(List<RMPProfessorDTO> professors);

    List<RMPProfessorDTO> getProfessorsByNameAndSchool(String name, String school);
}
