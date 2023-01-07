package main;

import java.io.File;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;


public class CommandLineValues {
    @Option(name = "-t", aliases = { "--threads" }, required = true, usage = "number of threads used")
    private int threads;

    @Option(name = "-i", aliases = { "--index-dir" }, required = true, usage = "directory to be indexed")
    private File indexedDirectory;

    private boolean errorFree = false;

    public CommandLineValues(String... args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);

            if (!getIndexedDirectory().isFile()) {
                throw new CmdLineException(parser,
                        "--index-dir is no valid input file.");
            }

            errorFree = true;
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
    }

    public boolean isErrorFree() {
        return errorFree;
    }

    public File getIndexedDirectory() {
        return indexedDirectory;
    }


}