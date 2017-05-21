package edu.cpp.hci.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rmp_tag")
public class RMPTagEntity extends BaseEntity {

    @Column
    private Integer ratingId;

    @Column
    private String text;

    public RMPTagEntity() {
    }

    public RMPTagEntity(Integer ratingId, String text) {

        this.ratingId = ratingId;
        this.text = text;
    }

    public Integer getRatingId() {

        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
