package edu.cpp.hci.web.entity.rmp;

import edu.cpp.hci.web.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rmp_professor")
public class RMPProfessorEntity extends BaseEntity {

    @Column
    private double overallQuality;

    @Column
    private double levelOfDifficulty;

    @Column
    private Double wouldTakeAgain;

    @Column
    private String name;

    @Column
    private String school;

    public RMPProfessorEntity() {
    }

    public RMPProfessorEntity(double overallQuality, double levelOfDifficulty, Double wouldTakeAgain, String name, String school) {
        this.overallQuality = overallQuality;
        this.levelOfDifficulty = levelOfDifficulty;
        this.wouldTakeAgain = wouldTakeAgain;
        this.name = name;
        this.school = school;
    }

    public double getOverallQuality() {
        return overallQuality;
    }

    public void setOverallQuality(double overallQuality) {
        this.overallQuality = overallQuality;
    }

    public double getLevelOfDifficulty() {
        return levelOfDifficulty;
    }

    public void setLevelOfDifficulty(double levelOfDifficulty) {
        this.levelOfDifficulty = levelOfDifficulty;
    }

    public Double getWouldTakeAgain() {
        return wouldTakeAgain;
    }

    public void setWouldTakeAgain(Double wouldTakeAgain) {
        this.wouldTakeAgain = wouldTakeAgain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
