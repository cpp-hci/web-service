package edu.cpp.hci.web.entity;

import edu.cpp.hci.scrapers.rmp.model.professor.RMPProfessorDTO;
import edu.cpp.hci.scrapers.rmp.model.professor.impl.RMPProfessor;
import edu.cpp.hci.scrapers.rmp.model.rating.RMPRatingDTO;
import edu.cpp.hci.scrapers.rmp.model.rating.impl.RMPRating;

public class EntityFactory {
    public static RMPRatingEntity toEntity(RMPRatingDTO dto, Integer professorId) {
        RMPRatingEntity rmpRatingEntity = new RMPRatingEntity();
        rmpRatingEntity.setAttendance(dto.getAttendance());
        rmpRatingEntity.setClassName(dto.getClassName());
        rmpRatingEntity.setDate(dto.getDate());
        rmpRatingEntity.setDescription(dto.getDescription());
        rmpRatingEntity.setForCredit(dto.getForCredit());
        rmpRatingEntity.setFoundHelpful(dto.getFoundHelpful());
        rmpRatingEntity.setFoundUnhelpful(dto.getFoundUnhelpful());
        rmpRatingEntity.setGradeReceived(dto.getGradeReceived());
        rmpRatingEntity.setLevelOfDifficulty(dto.getLevelOfDifficulty());
        rmpRatingEntity.setOverallQuality(dto.getOverallQuality());
        rmpRatingEntity.setProfessorId(professorId);
        rmpRatingEntity.setRatingText(dto.getRatingText());
        rmpRatingEntity.setTextBookUsed(dto.getTextBookUsed());
        rmpRatingEntity.setWouldTakeAgain(dto.getWouldTakeAgain());
        return rmpRatingEntity;
    }

    public static RMPRatingDTO toDto(RMPRatingEntity entity) {
        RMPRatingDTO rmpRatingDTO = new RMPRating();
        rmpRatingDTO.setAttendance(entity.getAttendance());
        rmpRatingDTO.setClassName(entity.getClassName());
        rmpRatingDTO.setDate(entity.getDate());
        rmpRatingDTO.setDescription(entity.getDescription());
        rmpRatingDTO.setForCredit(entity.getForCredit());
        rmpRatingDTO.setFoundHelpful(entity.getFoundHelpful());
        rmpRatingDTO.setFoundUnhelpful(entity.getFoundUnhelpful());
        rmpRatingDTO.setGradeReceived(entity.getGradeReceived());
        rmpRatingDTO.setLevelOfDifficulty(entity.getLevelOfDifficulty());
        rmpRatingDTO.setOverallQuality(entity.getOverallQuality());
        rmpRatingDTO.setRatingText(entity.getRatingText());
        rmpRatingDTO.setTextBookUsed(entity.getTextBookUsed());
        rmpRatingDTO.setWouldTakeAgain(entity.getWouldTakeAgain());
        rmpRatingDTO.setId(entity.getId());
        return rmpRatingDTO;
    }

    public static RMPProfessorDTO toDto(RMPProfessorEntity entity) {
        RMPProfessorDTO rating = new RMPProfessor();
        rating.setId(entity.getId());
        rating.setLevelOfDifficulty(entity.getLevelOfDifficulty());
        rating.setOverallQuality(entity.getOverallQuality());
        rating.setWouldTakeAgain(entity.getWouldTakeAgain());
        rating.setName(entity.getName());
        rating.setSchool(entity.getSchool());
        return rating;
    }

    public static RMPProfessorEntity toEntity(RMPProfessorDTO dto) {
        RMPProfessorEntity rmpProfessorEntity = new RMPProfessorEntity();
        rmpProfessorEntity.setId(dto.getId());
        rmpProfessorEntity.setLevelOfDifficulty(dto.getLevelOfDifficulty());
        rmpProfessorEntity.setName(dto.getName());
        rmpProfessorEntity.setOverallQuality(dto.getOverallQuality());
        rmpProfessorEntity.setSchool(dto.getSchool());
        rmpProfessorEntity.setWouldTakeAgain(dto.getWouldTakeAgain()); // TODO: 5/20/17 refactor would take again to be double
        return rmpProfessorEntity;
    }

}
