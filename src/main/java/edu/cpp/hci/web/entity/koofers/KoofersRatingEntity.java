package edu.cpp.hci.web.entity.koofers;

import edu.cpp.hci.web.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "koofers_rating")
public class KoofersRatingEntity extends BaseEntity {

    @Column
    private String courseNumber;

    @Column
    private String courseName;

    @Column
    private String period;

    @Column
    private double overallRating;

    @Column
    private double overallGpa;

    @Column
    private String reviewText;

    @Column
    private Integer professorId;

    public KoofersRatingEntity(String courseNumber, String courseName, String period, double overallRating, double overallGpa, String reviewText, Integer professorId) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.period = period;
        this.overallRating = overallRating;
        this.overallGpa = overallGpa;
        this.reviewText = reviewText;
        this.professorId = professorId;
    }

    public KoofersRatingEntity() {

    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
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

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }
}
