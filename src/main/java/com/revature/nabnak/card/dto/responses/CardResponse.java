package com.revature.nabnak.card.dto.responses;

import com.revature.nabnak.card.Card;

public class CardResponse {

    private int cardId;
    private String description;
    private int points;
    private String tech;
    private String status;
    private String memberEmail;

    public CardResponse(Card card){
        this.cardId = card.getCardId();
        this.description = card.getDescription();
        this.points = card.getPoints();
        this.status = card.getStatus().toString();
        this.tech = card.getTech().toString();
        this.memberEmail = card.getMemberId().getEmail();
    }

    public CardResponse() {
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
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

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    @Override
    public String toString() {
        return "CardResponse{" +
                "cardId=" + cardId +
                ", description='" + description + '\'' +
                ", points=" + points +
                ", tech='" + tech + '\'' +
                ", status='" + status + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                '}';
    }
}
