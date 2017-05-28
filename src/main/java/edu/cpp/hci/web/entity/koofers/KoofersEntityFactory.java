package edu.cpp.hci.web.entity.koofers;

import edu.cpp.hci.scrapers.koofers.model.professor.KoofersProfessorDTO;
import edu.cpp.hci.scrapers.koofers.model.professor.impl.KoofersProfessor;
import edu.cpp.hci.scrapers.koofers.model.rating.KoofersRatingDTO;
import edu.cpp.hci.scrapers.koofers.model.rating.impl.KoofersRating;

public class KoofersEntityFactory {
    public static KoofersProfessorEntity toEntity(KoofersProfessorDTO professorDTO) {
        KoofersProfessorEntity koofersProfessorEntity = new KoofersProfessorEntity();
        koofersProfessorEntity.setName(professorDTO.getName());
        koofersProfessorEntity.setDepartment(professorDTO.getDepartment());
        koofersProfessorEntity.setOverallGpa(professorDTO.getOverallGPA());
        koofersProfessorEntity.setSchool(professorDTO.getSchool());
        koofersProfessorEntity.setOverallRating(professorDTO.getOverallRating());
        koofersProfessorEntity.setPeriod(professorDTO.getPeriod());
        return koofersProfessorEntity;
    }

    public static KoofersRatingEntity toEntity(KoofersRatingDTO ratingDTO, Integer professorId) {
        KoofersRatingEntity koofersRatingEntity = new KoofersRatingEntity();
        koofersRatingEntity.setOverallRating(ratingDTO.getOverallGPA());
        koofersRatingEntity.setOverallGpa(ratingDTO.getOverallRating());
        koofersRatingEntity.setCourseName(ratingDTO.getCourseName());
        koofersRatingEntity.setPeriod(ratingDTO.getPeriod());
        koofersRatingEntity.setReviewText(ratingDTO.getReviewText());
        koofersRatingEntity.setProfessorId(professorId);
        return koofersRatingEntity;

    }

    public static KoofersRatingDTO toDto(KoofersRatingEntity entity) {
        KoofersRatingDTO koofersRating = new KoofersRating();
        koofersRating.setPeriod(entity.getPeriod());
        koofersRating.setOverallGPA(entity.getOverallGpa());
        koofersRating.setOverallRating(entity.getOverallRating());
        koofersRating.setCourseName(entity.getCourseName());
        koofersRating.setPeriod(entity.getPeriod());
        koofersRating.setReviewText(entity.getReviewText());
        return koofersRating;
    }

    public static KoofersProfessorDTO toDto(KoofersProfessorEntity entity) {
        KoofersProfessor koofersProfessor = new KoofersProfessor();
        koofersProfessor.setName(entity.getName());
        koofersProfessor.setDepartment(entity.getDepartment());
        koofersProfessor.setOverallGPA(entity.getOverallGpa());
        koofersProfessor.setSchool(entity.getSchool());
        koofersProfessor.setOverallRating(entity.getOverallRating());
        koofersProfessor.setPeriod(entity.getPeriod());
        return koofersProfessor;
    }
}
