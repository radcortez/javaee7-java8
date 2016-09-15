package com.radcortez.javaee.java8.jpa.annotations;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Roberto Cortez
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findById", query = "SELECT e FROM EntityIdea e WHERE e.id = :id"),
        @NamedQuery(name = "findByName", query = "SELECT e FROM EntityIdea e WHERE e.id = :name")
})
@NamedQueryEnhanced(name = "findById", query = "SELECT e FROM EntityIdea e WHERE e.id = :id")
@NamedQueryEnhanced(name = "findByName", query = "SELECT e FROM EntityIdea e WHERE e.id = :name")
public class EntityIdea {
    @Id
    private Long id;
    private String name;
}
