package com.springboot.controller;

import com.springboot.dao.impl.HibernateActorDaoImpl;
import com.springboot.persistence.Actor;
import com.springboot.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class ActorController {

    private Logger logger = Logger.getLogger(HibernateActorDaoImpl.class.getName());
    @Autowired
    public ActorService actorService;
    private Actor actor;


    /**
     * Get data Actor from DB
     *  04/12/2022
     * @return list of actors
     */
    @ResponseBody
    @GetMapping("/actor")
    public List<Actor> getActorData() {
        logger.log(Level.INFO, "Get DATA Actors in DB ");
        List<Actor> actors = actorService.getDataActors();
        if (actors.size() == 0) {
            logger.log(Level.WARNING, "Don't get data ");
        }
        return actors;
    }

    /**
     * Find actor by id
     *  04/12/2022
     * @return actor
     */
    @ResponseBody
    @GetMapping("/actor/{id}")
    public Actor getActor(@PathVariable int id) {
        logger.log(Level.INFO, "Find actor by id ");
        Optional<Actor> optional = Optional.ofNullable(actorService.getActorById(id));

        if (optional.isPresent()) {
            logger.log(Level.INFO, "Not find actor in DB ");
        }

        return optional.get();

    }


    @PostMapping("/actor")
    public Actor add(@RequestBody Actor actor){
        // JSON can have ID(non-null, non-zero) or null valid
        // Override id=0 to make sure with POST method we will ignore ID and always add
        // new customer
        actor.setActorId(0);
        actorService.save(actor);
        logger.log(Level.INFO, "Add a new actor into DB ");
        return actor;
    }


    @PutMapping("/actor")
    public Actor update(@RequestBody Actor actor){
        actorService.save(actor);
        logger.log(Level.INFO, "Update a  actor in DB ");
        return actor;
    }

    @DeleteMapping("/actor/{id}")
    public String delete(@PathVariable int id ){
        actorService.delete(id);
        logger.log(Level.INFO, "Delete a  actor in DB ");
        return "Delete " + id + " from DB";
    }
}
