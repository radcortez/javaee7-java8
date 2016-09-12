package com.radcortez.javaee.java8.jaxb;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * Description.
 *
 * @author Roberto Cortez
 */
@XmlRootElement
@XmlAccessorType(FIELD)
public class Schedule {
    @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
    private LocalDate shiftStartDate;
    @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
    private LocalDate shiftEndDate;
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime shiftStartTime;
    @XmlJavaTypeAdapter(LocalDateTimeXmlAdapter.class)
    private LocalDateTime shiftEndTime;

    public Schedule() {
    }

    public Schedule(final LocalDate shiftStartDate, final LocalDate shiftEndDate, final LocalDateTime shiftStartTime,
                    final LocalDateTime shiftEndTime) {
        this.shiftStartDate = shiftStartDate;
        this.shiftEndDate = shiftEndDate;
        this.shiftStartTime = shiftStartTime;
        this.shiftEndTime = shiftEndTime;
    }

    public LocalDate getShiftStartDate() {
        return shiftStartDate;
    }

    public void setShiftStartDate(final LocalDate shiftStartDate) {
        this.shiftStartDate = shiftStartDate;
    }

    public LocalDate getShiftEndDate() {
        return shiftEndDate;
    }

    public void setShiftEndDate(final LocalDate shiftEndDate) {
        this.shiftEndDate = shiftEndDate;
    }

    public LocalDateTime getShiftStartTime() {
        return shiftStartTime;
    }

    public void setShiftStartTime(final LocalDateTime shiftStartTime) {
        this.shiftStartTime = shiftStartTime;
    }

    public LocalDateTime getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(final LocalDateTime shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
    }

    @Override
    public String toString() {
        return "Schedule{" +
               "\n shiftStartDate = " + shiftStartDate +
               ",\n shiftEndDate = " + shiftEndDate +
               ",\n shiftStartTime = " + shiftStartTime +
               ",\n shiftEndTime = " + shiftEndTime +
               "\n}";
    }
}
