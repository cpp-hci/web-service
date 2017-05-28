package edu.cpp.hci.web.entity.rmp;

import edu.cpp.hci.web.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rmp_rating")
public class RMPRatingEntity extends BaseEntity {

    @Column
    private Integer professorId;

    @Column
    private String date;

    @Column
    private String description;

    @Column
    private Integer overallQuality;

    @Column
    private Integer levelOfDifficulty;

    @Column
    private String className;

    @Column
    private String forCredit;

    @Column
    private String attendance;

    @Column
    private String textBookUsed;

    @Column
    private String wouldTakeAgain;

    @Column
    private String gradeReceived;

    @Column
    private String ratingText;

    @Column
    private Integer foundHelpful;
    private Integer foundUnhelpful;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOverallQuality() {
        return overallQuality;
    }

    public void setOverallQuality(Integer overallQuality) {
        this.overallQuality = overallQuality;
    }

    public Integer getLevelOfDifficulty() {
        return levelOfDifficulty;
    }

    public void setLevelOfDifficulty(Integer levelOfDifficulty) {
        this.levelOfDifficulty = levelOfDifficulty;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getForCredit() {
        return forCredit;
    }

    public void setForCredit(String forCredit) {
        this.forCredit = forCredit;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getTextBookUsed() {
        return textBookUsed;
    }

    public void setTextBookUsed(String textBookUsed) {
        this.textBookUsed = textBookUsed;
    }

    public String getWouldTakeAgain() {
        return wouldTakeAgain;
    }

    public void setWouldTakeAgain(String wouldTakeAgain) {
        this.wouldTakeAgain = wouldTakeAgain;
    }

    public String getGradeReceived() {
        return gradeReceived;
    }

    public void setGradeReceived(String gradeReceived) {
        this.gradeReceived = gradeReceived;
    }

    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public Integer getFoundHelpful() {
        return foundHelpful;
    }

    public void setFoundHelpful(Integer foundHelpful) {
        this.foundHelpful = foundHelpful;
    }

    public Integer getFoundUnhelpful() {
        return foundUnhelpful;
    }

    public void setFoundUnhelpful(Integer foundUnhelpful) {
        this.foundUnhelpful = foundUnhelpful;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }
}
