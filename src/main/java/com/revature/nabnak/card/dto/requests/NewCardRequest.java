package com.revature.nabnak.card.dto.requests;

public class NewCardRequest {

    private String description;
    private int points;
    private String tech;
    private String status;

    public NewCardRequest() {
    }

    public NewCardRequest(String description, int points, String tech, String status) {
        this.description = description;
        this.points = points;
        this.tech = tech;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "NewCardRequest{" +
                "description='" + description + '\'' +
                ", points=" + points +
                ", tech='" + tech + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
