package com.radcortez.javaee.java8.jpa.converter;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import static java.time.temporal.ChronoUnit.YEARS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Roberto Cortez
 */
@RunWith(Arquillian.class)
public class DateConverterTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                         .addPackage("com.radcortez.javaee.java8.jpa.converter")
                         .addAsWebInfResource("beans.xml")
                         .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private PersonBean personBean;

    @Test
    public void testDateConverters() throws Exception {
        final LocalDateTime now = LocalDateTime.now();
        personBean.createPerson(new Person(1L, "Naruto", LocalDate.of(1990, 5, 10), now));

        final Person person = personBean.findPerson(1L);
        assertNotNull(person);
        assertEquals(LocalDate.of(1990, 5, 10), person.getBirthDate());
        assertEquals(now, person.getCreateDate());

        long years = person.getBirthDate().until(now, YEARS);
        System.out.println(person.getName() + " is " + years + " old.");

        // The old not so good way
        final Calendar birthDate = Calendar.getInstance();
        birthDate.set(1990, 4, 10, 0, 0, 0); // Months start at 0!
        System.out.println("milis = " + birthDate.get(Calendar.MILLISECOND)); // milis are still there!
        birthDate.set(Calendar.MILLISECOND, 0);
        final Calendar current = Calendar.getInstance();
        int diff = current.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        if (birthDate.get(Calendar.MONTH) > current.get(Calendar.MONTH) ||
            (birthDate.get(Calendar.MONTH) == current.get(Calendar.MONTH) &&
             birthDate.get(Calendar.DAY_OF_MONTH) > current.get(Calendar.DAY_OF_MONTH))) {
            diff--;
        }
        System.out.println(person.getName() + " is " + diff + " old.");
    }
}
