package com.revature.nabnak.member.dto.response;

import com.revature.nabnak.member.Member;

public class MemberResponse {

    private String memberId;
    private String fullName;
    private String email;
    private int experienceMonths;

    public MemberResponse() {
    }

    public MemberResponse(Member member){
        this.memberId = member.getId();
        this.email = member.getEmail();
        this.fullName = member.getFullName();
        this.experienceMonths = member.getExperienceMonths();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExperienceMonths() {
        return experienceMonths;
    }

    public void setExperienceMonths(int experienceMonths) {
        this.experienceMonths = experienceMonths;
    }

    @Override
    public String toString() {
        return "MemberResponse{" +
                "memberId='" + memberId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", experienceMonths=" + experienceMonths +
                '}';
    }
}
