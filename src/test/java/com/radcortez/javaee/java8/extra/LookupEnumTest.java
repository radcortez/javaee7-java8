package com.radcortez.javaee.java8.extra;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * @author Roberto Cortez
 */
@RunWith(JUnit4.class)
public class LookupEnumTest {
    @Test
    public void testLookupEnum() throws Exception {
        assertEquals(LookupEnum.DEFAULT, LookupEnum.of("default"));
        assertEquals(LookupEnum.VALUE_A, LookupEnum.of("a"));
        assertEquals(LookupEnum.VALUE_B, LookupEnum.of("b"));

        assertEquals(LookupEnum.DEFAULT, LookupEnum.of("something"));
    }
}
