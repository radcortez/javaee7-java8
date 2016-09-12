package com.radcortez.javaee.java8.jpa.find;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

import static javax.transaction.Transactional.TxType.SUPPORTS;

/**
 * @author Roberto Cortez
 */
@Transactional(SUPPORTS)
public class MovieBean {
    @PersistenceContext
    private EntityManager entityManager;

    public Movie findMovie(final Long id) {
        return entityManager.find(Movie.class, id);
    }

    public Optional<Movie> findMovieEnhanced(final Long id) {
        return Optional.ofNullable(entityManager.find(Movie.class, id));
    }

    public Movie findMovieWithQuery(final Long id) {
        return entityManager.createQuery("SELECT m FROM Movie m WHERE m.id = :id", Movie.class)
                            .setParameter("id", id)
                            .getSingleResult();
    }

    public Optional<Movie> findMovieWithQueryEnhanced(final Long id) {
        try {
            return Optional.of(entityManager.createQuery("SELECT m FROM Movie m WHERE m.id = :id", Movie.class)
                                .setParameter("id", id)
                                .getSingleResult());
        } catch (final NoResultException e) {
            return Optional.empty();
        }
    }
}
