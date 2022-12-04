package com.springboot.dao;

import com.springboot.persistence.Actor;

import java.util.List;

public interface ActorDao {

    List<Actor> getDataActors();

    Actor getActorById(int id);

    void save(Actor actor);

    void delete(int id);
}
