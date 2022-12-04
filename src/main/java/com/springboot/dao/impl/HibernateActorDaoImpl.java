package com.springboot.dao.impl;

import com.springboot.dao.ActorDao;
import com.springboot.persistence.Actor;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Repository
public class HibernateActorDaoImpl implements ActorDao {

    // EntityManager: Object from JPA --> Auto Configure from Data Source
    // EntityManager: using Hibernate's Session --> entityManager.unwrap(Session.class);
    private EntityManager entityManager;

    private Logger logger = Logger.getLogger(HibernateActorDaoImpl.class.getName());

    @Autowired
    public HibernateActorDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public List<Actor> getDataActors() {
        Session session = entityManager.unwrap(Session.class);
        logger.log(Level.INFO, "Session is created !!!");
        return session.createNativeQuery("SELECT * FROM  actor", Actor.class).getResultList();
    }

    @Override
    public Actor getActorById(int id) {
        Session session = entityManager.unwrap(Session.class);
        logger.log(Level.INFO, "Session is created !!!");
        return session.get(Actor.class, id);
    }

    @Deprecated(since = "5.0")
    @Override
    public void save(Actor actor) {
        Session session = entityManager.unwrap(Session.class);
        logger.log(Level.INFO, "Session is created !!!");
        session.saveOrUpdate(actor);
    }

    @Override
    public void delete(int id) {
        Session session = entityManager.unwrap(Session.class);
        logger.log(Level.INFO, "Session is created !!!");

        session.createNativeQuery("DELETE  FROM actor WHERE actor_id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }


}
