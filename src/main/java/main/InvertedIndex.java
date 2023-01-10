package main;

class InvertedIndex {
    public static void main(String[] args) {
        CommandLineValues commandLineValues = new CommandLineValues(args);

        int numberOfThreads;
        numberOfThreads = commandLineValues.getThreads();

        String toBeIndexedDirectoryPath = commandLineValues.getIndexedDirectory().toString();
        String outputFilePath = commandLineValues.getOutputFile().toString();


        try {
            new ThreadManager(numberOfThreads, toBeIndexedDirectoryPath, outputFilePath);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
