package com.springboot.service.impl;

import com.springboot.dao.ActorDao;
import com.springboot.dao.impl.HibernateActorDaoImpl;
import com.springboot.persistence.Actor;
import com.springboot.service.ActorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActorServiceImpl implements ActorService {

    @Autowired
    public ActorDao actorDao;


    @Override
    public List<Actor> getDataActors() {
        return actorDao.getDataActors();
    }

    @Override
    public Actor getActorById(int id) {
        return actorDao.getActorById(id);
    }

    @Override
    @Transactional
    public void save(Actor actor) {
        actorDao.save(actor);
    }

    @Override
    @Transactional
    public void delete(int id) {
        actorDao.delete(id);
    }
}
