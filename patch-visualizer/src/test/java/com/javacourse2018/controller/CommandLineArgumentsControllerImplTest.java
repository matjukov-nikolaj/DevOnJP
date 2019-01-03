package com.javacourse2018.controller;

import com.javacourse2018.model.CommandLineArguments;
import com.javacourse2018.model.DisplayMode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CommandLineArgumentsControllerImplTest {

    @Test
    public void can_set_args_through_constructor() {
        String[] args = { "1", "2", "3"};
        List<String> expectedArgs = new ArrayList<>(Arrays.asList(args));
        CommandLineArgumentsController commandLineArgumentsController = new CommandLineArgumentsControllerImpl(args);
        Assert.assertEquals(expectedArgs, commandLineArgumentsController.getArgs());
        commandLineArgumentsController.setArgs(expectedArgs);
        Assert.assertTrue(!commandLineArgumentsController.getArgs().isEmpty());
        commandLineArgumentsController = new CommandLineArgumentsControllerImpl(expectedArgs);
        Assert.assertTrue(!commandLineArgumentsController.getArgs().isEmpty());
    }

    @Test
    public void can_watch_usage() {
        String[] args = { "1", "2", "3"};
        CommandLineArgumentsController commandLineArgumentsController = new CommandLineArgumentsControllerImpl(args);
        commandLineArgumentsController.showUsage();
    }

    @Test(expected= IllegalArgumentException.class)
    public void incorrect_numbers_of_parameters() {
        String[] args = { "1", "2", "3"};
        CommandLineArgumentsController commandLineArgumentsController = new CommandLineArgumentsControllerImpl(args);
        commandLineArgumentsController.handle();
    }

    @Test(expected= IllegalArgumentException.class)
    public void incorrect_numbers_of_parameters_2() {
        String[] args = { "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        CommandLineArgumentsController commandLineArgumentsController = new CommandLineArgumentsControllerImpl(args);
        commandLineArgumentsController.handle();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_for_invalid_command_1() {
        String[] args = { "--fil", "1", "--patch", "2", "--out", "3", "--mode", "lol"};
        CommandLineArgumentsController commandLineArgumentsController = new CommandLineArgumentsControllerImpl(args);
        commandLineArgumentsController.handle();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_for_invalid_command_2() {
        String[] args = { "--file", "1", "--pat", "2", "--out", "3", "--mode", "lol"};
        CommandLineArgumentsController commandLineArgumentsController = new CommandLineArgumentsControllerImpl(args);
        commandLineArgumentsController.handle();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_for_invalid_command_3() {
        String[] args = { "--file", "1", "--pat", "2", "--outs", "3", "--mode", "lol"};
        CommandLineArgumentsController commandLineArgumentsController = new CommandLineArgumentsControllerImpl(args);
        commandLineArgumentsController.handle();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_for_invalid_command_4() {
        String[] args = { "--file", "1", "--patch", "2", "--out", "3", "--me", "lol"};
        CommandLineArgumentsController commandLineArgumentsController = new CommandLineArgumentsControllerImpl(args);
        commandLineArgumentsController.handle();
    }

    @Test
    public void can_get_arguments_model() {
        String[] args = { "--file", "1", "--patch", "2", "--out", "3", "--mode", "split"};
        CommandLineArgumentsController commandLineArgumentsController = new CommandLineArgumentsControllerImpl(args);
        commandLineArgumentsController.handle();
        CommandLineArguments arguments = commandLineArgumentsController.getArguments();
        Assert.assertEquals(DisplayMode.SPLIT, arguments.getDisplayMode());
        Assert.assertEquals("1", arguments.getFile());
        Assert.assertEquals("2", arguments.getPatchFile());
        Assert.assertEquals("3", arguments.getOutFile());
    }

    @Test
    public void can_set_arguments_model() {
        String[] args = { "--file", "5", "--patch", "6", "--out", "4", "--mode", "split"};
        CommandLineArgumentsController commandLineArgumentsController = new CommandLineArgumentsControllerImpl(args);
        commandLineArgumentsController.handle();
        CommandLineArguments arguments = new CommandLineArguments();
        arguments.setFile("1");
        arguments.setPatchFile("2");
        arguments.setOutFile("3");
        arguments.setDisplayMode(DisplayMode.UNIFIED);
        commandLineArgumentsController.setArguments(arguments);
        Assert.assertTrue(!arguments.getFile().equals("5"));
        Assert.assertTrue(!arguments.getPatchFile().equals("6"));
        Assert.assertTrue(!arguments.getOutFile().equals("4"));
        Assert.assertNotSame(DisplayMode.SPLIT, arguments.getDisplayMode());
        Assert.assertEquals("unified", arguments.getDisplayMode().toString());
    }

}