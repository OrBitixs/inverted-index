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

    @Option(name = "-o", aliases = { "--output-dir" }, required = true, usage = "directory where index will be stored")
    private File outputFile;

    private boolean errorFree = false;

    public CommandLineValues(String... args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);

            if (!indexedDirectory.isDirectory()) {
                throw new CmdLineException(parser, "-i [--index-dir] is no valid directory.");
            }


            if (threads < 1 || threads > 16) {
                threads = 1;
                throw new CmdLineException(parser, "-t [--threads] must be integer 1 through 16");
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

    public File getOutputFile() {
        return outputFile;
    }

    public int getThreads() {
        return threads;
    }
}