package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOImpl implements AppDAO {

    // define field for entity manager
    EntityManager em;

    @Autowired
    public AppDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        em.persist(instructor);
    }

    @Override
    public Instructor findById(int id) {
        return em.find(Instructor.class, id);
    }
}
