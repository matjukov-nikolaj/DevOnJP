package com.javacourse2018.controller;

import com.javacourse2018.model.CommandLineArguments;
import com.javacourse2018.model.DisplayMode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
import java.util.List;

public class CommandLineArgumentsControllerImpl implements CommandLineArgumentsController {
    private static final Log LOG = LogFactory.getLog(CommandLineArgumentsControllerImpl.class);

    private static final Integer ARGS_LENGTH = 8;
    private static final String SEPARATOR = "--";
    private static final String FILE = SEPARATOR + "file";
    private static final String PATCH = SEPARATOR + "patch";
    private static final String OUT = SEPARATOR + "out";
    private static final String MODE = SEPARATOR + "mode";

    private List<String> args;

    private CommandLineArguments arguments;

    public CommandLineArgumentsControllerImpl(String[] args) {
        this.args = Arrays.asList(args);
        this.arguments = new CommandLineArguments();
    }

    public CommandLineArgumentsControllerImpl(List<String> args) {
        this.args = args;
        this.arguments = new CommandLineArguments();
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public void handle() {
        if (this.args.size() != ARGS_LENGTH) {
            throw new IllegalArgumentException("Invalid number of arguments.");
        }
        for (int i = 0; i < this.args.size(); i += 2) {
            String argument = this.args.get(i);
            switch (argument) {
                case FILE:
                    String file = this.args.get(i + 1);
                    this.arguments.setFile(file);
                    break;
                case PATCH:
                    String patch = this.args.get(i + 1);
                    this.arguments.setPatchFile(patch);
                    break;
                case OUT:
                    String out = this.args.get(i + 1);
                    this.arguments.setOutFile(out);
                    break;
                case MODE:
                    String modeStr = this.args.get(i + 1);
                    DisplayMode mode = DisplayMode.createFromString(modeStr.toLowerCase());
                    this.arguments.setDisplayMode(mode);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid command.");
            }
        }
    }

    public void showUsage() {
        LOG.info("java -jar gitVisualizer-1.0.jar <parameters>\r\n"
                + "---Parameters---\r\n"
                + "    --file <full path to file>\r\n"
                + "    --patch <full path to .patch file>\r\n"
                + "    --out <full path to .html file>\r\n"
                + "    --mode <split or unified\r\n");
    }

    public CommandLineArguments getArguments() {
        return arguments;
    }

    public void setArguments(CommandLineArguments arguments) {
        this.arguments = arguments;
    }
}
