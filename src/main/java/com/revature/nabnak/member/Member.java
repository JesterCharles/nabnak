package com.revature.nabnak.member;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="members")
public class Member {
    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name="full_name")
    private String fullName;

    @Column(name="experience_months")
    private int experienceMonths;

    @Column(name = "registration_date")
    private Date registrationDate;

    @JsonAlias(value = {"Password", "pass", "theThing"})
    private String password;

    public Member(){
        super();
    }

    public Member(String email, String fullName, int experienceMonths, Date registrationDate, String password) {
        this.email = email;
        this.fullName = fullName;
        this.experienceMonths = experienceMonths;
        this.registrationDate = registrationDate;
        this.password = password;
    }

    public Member(String id, String email, String fullName, int experienceMonths, Date registrationDate, String password) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.experienceMonths = experienceMonths;
        this.registrationDate = registrationDate;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setExperienceMonths(int experienceMonths) {
        this.experienceMonths = experienceMonths;
    }

    public int getExperienceMonths() {
        return experienceMonths;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", experienceMonths=" + experienceMonths +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
