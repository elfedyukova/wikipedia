package org.example;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    @Test
    public void testGetLocalNumber() {
        MainClass mainClass = new MainClass();
        int expected = 14;
        int actual = mainClass.getLocalNumber();
        Assert.assertTrue("Метод getLocalNumber не возвращает число 14!", expected == actual);
    }

    @Test
    public void testGetClassNumber() {
        MainClass mainClass = new MainClass();
        int expected = 45;
        int actual = mainClass.getClassNumber();
        Assert.assertTrue("Метод getClassNumber возвращает число меньше или равное 45!", actual > expected);
    }
}
