package com.javacourse2018.controller;

import com.javacourse2018.model.CommandLineArguments;
import com.javacourse2018.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;

public class ApplicationController {
    private static final Log LOG = LogFactory.getLog(ApplicationController.class);

    private ApplicationController() {

    }

    public static void run(String[] args) {
        CommandLineArgumentsController commandLineArgumentsController = new CommandLineArgumentsControllerImpl(Arrays.asList(args));
        CommandLineArguments arguments = null;
        try {
            commandLineArgumentsController.handle();
            arguments = commandLineArgumentsController.getArguments();
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            commandLineArgumentsController.showUsage();
            return;
        }

        PatchFileParser parser = null;
        try {
            parser = new PatchFileParserImpl(arguments.getPatchFile());
            parser.parse();
        } catch (Exception e) {
            LOG.error("Incorrect input data.");
            return;
        }

        MainFileReader mainFileReader = new MainFileReader(arguments.getFile());
        mainFileReader.read();

        try {
            CommitLineGenerator generator = new CommitLineGenerator(mainFileReader.getLines(), parser.getBlocks());
            generator.generate();

            HtmlGenerator htmlGenerator = new HtmlGenerator(arguments.getDisplayMode(), generator.getLines(), arguments.getOutFile(), parser.getCommitInfo());
            htmlGenerator.generate();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

}
