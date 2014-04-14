package com.trial.swagger.dao;

import com.trial.swagger.model.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by varunverma on 11/04/2014.
 */
@Repository
public class BookDao {

    @PersistenceContext
    EntityManager entityManager;

    public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

    public final EntityManager entityManager() {
        EntityManager em = entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public Book findBook(Long id) {
        if (id == null) return null;
        return entityManager().find(Book.class, id);
    }
}
