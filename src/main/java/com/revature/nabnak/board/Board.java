package com.revature.nabnak.board;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="boards")
public class Board {
    @Id
    private int board_id;
}
