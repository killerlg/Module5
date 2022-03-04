package com.addq.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table
public class Promotion implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeEnd;
    private Long value;
    private String description;

    public Promotion() {
    }

    public Promotion(String title, Date timeStart, Date timeEnd, Long value, String description) {
        this.title = title;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.value = value;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Promotion promotion = (Promotion) target;
        String title = promotion.getTitle();
        ValidationUtils.rejectIfEmpty(errors, "title", "title.empty");
        Date timeStart = promotion.getTimeStart();
        ValidationUtils.rejectIfEmpty(errors, "timeStart", "timeStart.empty");
        Date timeEnd = promotion.getTimeEnd();
        ValidationUtils.rejectIfEmpty(errors, "timeEnd", "timeEnd.empty");
        Long value = promotion.getValue();
        ValidationUtils.rejectIfEmpty(errors, "value", "value.empty");
        String description = promotion.getDescription();
        ValidationUtils.rejectIfEmpty(errors, "description", "description.empty");
        if (value<=10000){
            errors.rejectValue("value", "value.value");
        }
        if (timeEnd.before(timeStart) || timeEnd.equals(timeStart)) {
            errors.rejectValue("timeEnd", "timeEnd.before");
        }
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        if (timeStart.before(date)) {
            errors.rejectValue("timeStart", "timeStart.before");
        }
    }
}
