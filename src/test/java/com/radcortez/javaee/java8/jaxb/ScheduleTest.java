package com.radcortez.javaee.java8.jaxb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Description.
 *
 * @author Roberto Cortez
 */
@RunWith(JUnit4.class)
public class ScheduleTest {
    @Test
    public void testScheduleUnmarshall() throws Exception {
        final JAXBContext jaxbContext = JAXBContext.newInstance(Schedule.class);
        final Schedule schedule = ((Schedule) jaxbContext.createUnmarshaller().unmarshal(
                this.getClass().getClassLoader().getResource("jaxb/schedule.xml")));
        assertNotNull(schedule);
        assertNotNull(schedule.getShiftStartDate());
        assertNotNull(schedule.getShiftStartTime());
        assertNotNull(schedule.getShiftEndDate());
        assertNotNull(schedule.getShiftEndTime());

        assertEquals(LocalDate.of(2010, 10, 10), schedule.getShiftStartDate());
        assertEquals(LocalDateTime.of(2010, 10, 10, 10, 0, 0), schedule.getShiftStartTime());
        assertEquals(LocalDate.of(2010, 10, 11), schedule.getShiftEndDate());
        assertEquals(LocalDateTime.of(2010, 10, 11, 10, 0, 0), schedule.getShiftEndTime());

        System.out.println(schedule);
    }

    @Test
    public void testScheduleMarshall() throws Exception {
        final JAXBContext jaxbContext = JAXBContext.newInstance(Schedule.class);
        final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        final Schedule schedule = new Schedule();
        schedule.setShiftStartDate(LocalDate.of(2010, 10, 10));
        schedule.setShiftStartTime(LocalDateTime.of(2010, 10, 10, 10, 0, 0));
        schedule.setShiftEndDate(LocalDate.of(2010, 10, 11));
        schedule.setShiftEndTime(LocalDateTime.of(2010, 10, 11, 10, 0, 0));

        jaxbMarshaller.marshal(schedule, System.out);
    }
}