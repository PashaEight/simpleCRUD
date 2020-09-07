package ru.eight.App;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

    @Test
    public void sum() {
        Assert.assertSame(Utils.sum(3, 3), 8);
    }
}