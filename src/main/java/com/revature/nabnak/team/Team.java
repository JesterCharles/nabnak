package com.revature.nabnak.team;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="teams")
public class Team {
    @Id
    private String teamName;
    private int teamSize;

}
