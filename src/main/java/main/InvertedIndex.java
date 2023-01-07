package main;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

class InvertedIndex {
    public static void main(String[] args) {
//        System.out.println(args[0]);
        int numberOfThreads = 1;
        try {
            numberOfThreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println(e.toString() + "\nAssigned number of Threads 1");
        }

        String hardcodedDirectoryPath = System.getProperty("user.dir") + "\\books";
        System.out.println(hardcodedDirectoryPath);

//        String hardcodedDirectoryPath = "C:\\maks\\ParallelComputing_labs\\inverted-index\\books";

        new ThreadManager(numberOfThreads, hardcodedDirectoryPath);
    }
    }
