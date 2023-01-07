package main;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class ThreadManager {
    private Set<String> targetFiles;

    public ThreadManager(int numberOfThreads, String hardcodedDirectoryPath) {
        try {
            targetFiles = listFiles(hardcodedDirectoryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SyncIterator syncIterator = new SyncIterator(targetFiles);
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(new Indexer(syncIterator, hardcodedDirectoryPath)).start();
        }
    }

    public Set<String> listFiles(String dir) throws IOException {
        Set<String> fileSet = new HashSet<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {
                    fileSet.add(path.getFileName().toString());
                }
            }
        }
        return fileSet;
    }

}
