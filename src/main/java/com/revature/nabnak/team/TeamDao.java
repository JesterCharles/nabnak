package com.revature.nabnak.team;

import com.revature.nabnak.util.interfaces.Crudable;

import java.util.List;

public class TeamDao implements Crudable<Team> {
    @Override
    public Team create(Team newObject) {
        return null;
    }

    @Override
    public List<Team> findAll() {
        return null;
    }

    @Override
    public Team findById(String id) {
        return null;
    }

    @Override
    public boolean update(Team updatedObject) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
