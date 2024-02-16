package org.example;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    MainClass mainClass = new MainClass();

    @Test
    public void testGetLocalNumber() {
        int expected = 14;
        int actual = mainClass.getLocalNumber();
        Assert.assertTrue("Метод getLocalNumber не возвращает число 14!", expected == actual);
    }

    @Test
    public void testGetClassNumber() {
        int expected = 45;
        int actual = mainClass.getClassNumber();
        Assert.assertTrue("Метод getClassNumber возвращает число меньше или равное 45!", actual > expected);
    }

    @Test
    public void testGetClassString() {
        String actual = mainClass.getClassString();
        Assert.assertTrue("Метод getClassString возвращает строку без подстрок 'hello' и 'Hello'!", actual.contains("hello") || actual.contains("Hello"));
    }
}
