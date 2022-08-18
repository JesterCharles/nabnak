package com.revature.nabnak.member.dto.requests;

import com.revature.nabnak.util.web.DTO.EditResourceRequests;

public class EditMemberRequest extends EditResourceRequests {

    private String fullName;
    private String email; //pulling the id from the parent class
    private String password;

    public EditMemberRequest() {
        super();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EditMemberRequest{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
