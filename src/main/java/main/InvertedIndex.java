package main;

class InvertedIndex {
    public static void main(String[] args) {
        CommandLineValues commandLineValues = new CommandLineValues(args);

//        System.out.println(args[0]);
        int numberOfThreads;
        numberOfThreads = commandLineValues.getThreads();

        String toBeIndexedDirectoryPath = commandLineValues.getIndexedDirectory().toString();
        String outputFilePath = commandLineValues.getOutputFile().toString();
//        System.out.println(toBeIndexedDirectoryPath);

//        String toBeIndexedDirectoryPath = "C:\\maks\\ParallelComputing_labs\\inverted-index\\books";

        try {
            new ThreadManager(numberOfThreads, toBeIndexedDirectoryPath, outputFilePath);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
