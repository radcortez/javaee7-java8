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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Roberto Cortez
 */
@RunWith(Arquillian.class)
public class DateConverterTest {
    @Deployment
    public static WebArchive createDeployment() {
        final WebArchive war = ShrinkWrap.create(WebArchive.class)
                                         .addPackage("com.radcortez.javaee.java8.jpa.converter")
                                         .addAsWebInfResource("beans.xml")
                                         .addAsResource("META-INF/persistence.xml");
        System.out.println(war.toString(true));
        return war;
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
    }
}
