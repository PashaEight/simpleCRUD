package ru.eight.App;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {
    @Test
    public void commandWithParam() {
        String commandWithParam = "insert -x -y";
        Assert.assertEquals("insert", Utils.getCommand(commandWithParam));
    }

    @Test
    public void rawCommand() {
        String command = "read";
        Assert.assertEquals("read", Utils.getCommand(command));
    }
}