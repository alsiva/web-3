package edu.ifmo.web.lab3;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@ApplicationScoped
public class DbManager implements Serializable {
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("hits");
    private final EntityManager entityManager = factory.createEntityManager();

    synchronized public boolean addHits(List<HitResult> hitResults) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            for (HitResult hitResult : hitResults) {
                entityManager.persist(hitResult);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.err.println("failed to add hits to database: " + e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    synchronized public List<HitResult> getHits() {
        try {
            return entityManager
                .createQuery("SELECT h FROM HitResult h", HitResult.class)
                .getResultList();
        } catch (Exception e) {
            System.err.println("failed to get hits from database: " + e.getMessage());

            return Collections.emptyList();
        }
    }
}
