package com.revature.nabnak.card;

import com.revature.nabnak.card.dto.requests.NewCardRequest;
import com.revature.nabnak.member.Member;

import javax.persistence.*;

@Entity
@Table(name="card")
public class Card {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int cardId;
    private String description;
    private int points;
    @Enumerated(EnumType.STRING)
    private Tech tech; // Java, java, JaVA, JavA

    @Enumerated(EnumType.STRING)
    private Status status; // Open, OPEN, open

    @ManyToOne
    @JoinColumn(name="member_id", nullable = false)
    private Member memberId;



    public Card(NewCardRequest cardRequest, Member member) {
        this.description = cardRequest.getDescription();
        this.points = cardRequest.getPoints();
        this.tech = Tech.valueOf(cardRequest.getTech().toUpperCase());
        this.status = Status.valueOf(cardRequest.getStatus().toUpperCase());
        this.memberId = member;
    }

    public Card() {
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

    public Tech getTech() {
        return tech;
    }

    public void setTech(Tech tech) {
        this.tech = tech;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Member getMemberId() {
        return memberId;
    }

    public void setMemberId(Member memberId) {
        this.memberId = memberId;
    }

    public enum Tech{
        JAVA, JAVASCRIPT, PYTHON, RUBY, GO, REACT, TYPESCRIPT, LUA, CARBON
    }

    public enum Status{
        OPEN, INPROGRESS, CLOSED
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", description='" + description + '\'' +
                ", points=" + points +
                ", tech='" + tech + '\'' +
                ", status='" + status + '\'' +
                ", memberEmail=" + memberId.getEmail() +
                '}';
    }
}
