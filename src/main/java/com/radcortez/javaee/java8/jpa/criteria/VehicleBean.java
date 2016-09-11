package com.radcortez.javaee.java8.jpa.criteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static javax.transaction.Transactional.TxType.SUPPORTS;

/**
 * @author Roberto Cortez
 */
@Transactional(SUPPORTS)
public class VehicleBean {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Vehicle> findVehicles(final Vehicle.Type type) {
        String jql = "SELECT v FROM Vehicle v WHERE 1 = 1";
        if (type != null) {
            jql = jql + " AND v.type = :vtype";
        }

        final TypedQuery<Vehicle> query = entityManager.createQuery(jql, Vehicle.class);
        if (type != null) {
            query.setParameter("vtype", type);
        }

        return query.getResultList();
    }

    public List<Vehicle> findVehiclesCriteria(final Vehicle.Type type) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Vehicle> query = builder.createQuery(Vehicle.class);
        final Root<Vehicle> root = query.from(Vehicle.class);

        final List<Predicate> predicates = new ArrayList<>();
        if (type != null) {
            predicates.add(builder.equal(root.get(Vehicle_.type), type));
        }

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(query).getResultList();
    }

    public List<Vehicle> findVehiclesEnhanced(final Optional<Vehicle.Type> type) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Vehicle> query = builder.createQuery(Vehicle.class);
        final Root<Vehicle> root = query.from(Vehicle.class);

        query.select(root).where(
                Stream.of(new SimpleEntry<>(Vehicle_.type, type))
                      .filter(p -> p.getValue().isPresent())
                      .map(p -> builder.equal(root.get(p.getKey()), p.getValue().get()))
                      .toArray(Predicate[]::new));

        return entityManager.createQuery(query).getResultList();
    }
}
