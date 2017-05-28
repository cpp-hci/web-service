package edu.cpp.hci.web.entity.koofers;

import edu.cpp.hci.web.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "koofers_professor")
public class KoofersProfessorEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String period;

    @Column
    private String school;

    @Column
    private String department;

    @Column
    private double overallRating;

    @Column
    private double overallGpa;

    public KoofersProfessorEntity() {

    }

    public KoofersProfessorEntity(String name, String period, String school, String department, double overallRating, double overallGpa) {

        this.name = name;
        this.period = period;
        this.school = school;
        this.department = department;
        this.overallRating = overallRating;
        this.overallGpa = overallGpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    public double getOverallGpa() {
        return overallGpa;
    }

    public void setOverallGpa(double overallGpa) {
        this.overallGpa = overallGpa;
    }
}
