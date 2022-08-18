package com.revature.nabnak.board;

import com.revature.nabnak.util.interfaces.Crudable;

import java.util.List;

public class BoardDao implements Crudable<Board> {
    @Override
    public Board create(Board newObject) {
        return null;
    }

    @Override
    public List<Board> findAll() {
        return null;
    }

    @Override
    public Board findById(String id) {
        return null;
    }

    @Override
    public boolean update(Board updatedObject) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
