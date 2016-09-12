package com.radcortez.javaee.java8.jpa.find;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Roberto Cortez
 */
@RunWith(Arquillian.class)
public class FindTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                         .addPackage("com.radcortez.javaee.java8.jpa.find")
                         .addAsWebInfResource("beans.xml")
                         .addAsResource("META-INF/persistence.xml")
                         .addAsResource("META-INF/load-movies.sql", "META-INF/load.sql");
    }

    @Inject
    private MovieBean movieBean;

    @Test
    public void testFindMovie() throws Exception {
        final Movie movie = movieBean.findMovie(1L);
        assertNotNull(movie);
        assertEquals("Star Wars: A New Hope", movie.getName());

        final Movie anotherMovie = movieBean.findMovie(5L);
        if (anotherMovie != null) {
            System.out.println("The name of the Movie is " + anotherMovie.getName());
        } else {
            System.out.println("Movie not found!");
        }
        assertNull(anotherMovie);

        final Optional<Movie> movieOptional = movieBean.findMovieEnhanced(5L);
        assertNotNull(movieOptional);
        System.out.println(movieOptional.map(Movie::getName)
                                        .orElse("Movie not found!"));

    }

    @Test
    public void testFindMovieWithQuery() throws Exception {
        final Movie movie = movieBean.findMovieWithQuery(1L);
        assertNotNull(movie);
        assertEquals("Star Wars: A New Hope", movie.getName());

        try {
            movieBean.findMovieWithQuery(5L);
            assertTrue(false);
        } catch (final NoResultException e) {
            assertTrue(true);
        }

        final Optional<Movie> movieOptional = movieBean.findMovieWithQueryEnhanced(5L);
        assertNotNull(movieOptional);
        System.out.println(movieOptional.map(Movie::getName)
                                        .orElse("Movie not found!"));
    }
}
