package com.javacourse2018.controller;

import com.javacourse2018.model.CommandLineArguments;

import java.util.List;

public interface CommandLineArgumentsController {

    List<String> getArgs();

    void setArgs(List<String> args);

    void handle();

    void showUsage();

    CommandLineArguments getArguments();

    void setArguments(CommandLineArguments arguments);

}
