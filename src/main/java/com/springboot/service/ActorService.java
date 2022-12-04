package com.springboot.service;

import com.springboot.persistence.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> getDataActors();

    Actor getActorById(int id);

    void save(Actor actor);

    void delete(int id);
}
