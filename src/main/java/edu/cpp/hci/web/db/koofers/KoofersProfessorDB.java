package edu.cpp.hci.web.db.koofers;

import edu.cpp.hci.scrapers.koofers.model.professor.KoofersProfessorDTO;

import java.util.List;

public interface KoofersProfessorDB {
    KoofersProfessorDTO getProfessor(Integer professorId);

    List<KoofersProfessorDTO> putProfessors(List<KoofersProfessorDTO> professors);

    List<KoofersProfessorDTO> getProfessorsByNameAndSchool(String name, String school);
}
