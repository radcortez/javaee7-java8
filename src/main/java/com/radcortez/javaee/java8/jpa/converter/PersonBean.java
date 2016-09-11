package com.radcortez.javaee.java8.jpa.converter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

/**
 * @author Roberto Cortez
 */
@Transactional(SUPPORTS)
public class PersonBean {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(REQUIRED)
    public Person createPerson(final Person person) {
        entityManager.persist(person);
        return person;
    }

    public Person findPerson(final Long id) {
        return entityManager.find(Person.class, id);
    }
}
