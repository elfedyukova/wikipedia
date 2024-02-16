package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainClassTest {
    @Test
    public void testGetLocalNumber() {
        MainClass mainClass = new MainClass();
        int expected = 14;
        int actual = mainClass.getLocalNumber();
        assertEquals(expected, actual);
    }
}
