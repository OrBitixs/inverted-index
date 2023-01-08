package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ThreadManager {
    private Set<String> targetFiles;

    public ThreadManager(int numberOfThreads, String hardcodedDirectoryPath, String outputFile) throws InterruptedException {
        try {
            targetFiles = listFiles(hardcodedDirectoryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SyncIterator syncIterator = new SyncIterator(targetFiles);
        SyncMapStorage syncMapStorage = new SyncMapStorage();

        List<Thread> threads = new ArrayList<>(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            Thread newThread = new Thread(new Indexer(syncIterator, syncMapStorage, hardcodedDirectoryPath));
            newThread.start();
            threads.add(newThread);
        }

        for (int i = 0; i < numberOfThreads; i++) {
            threads.get(i).join();
        }

        System.out.println(syncMapStorage.getTokenToMetaDataMapList().size());
        for (int i = 1; i < syncMapStorage.getTokenToMetaDataMapList().size(); i++) {
            syncMapStorage.getTokenToMetaDataMapList().get(i).forEach((key, value) -> {
                if (syncMapStorage.getTokenToMetaDataMapList().get(0).containsKey(key)){
                    syncMapStorage.getTokenToMetaDataMapList().get(0).get(key).addTokenMeta(value);
                } else {
                    syncMapStorage.getTokenToMetaDataMapList().get(0).put(key,value);
                }
            });
        }

        try {
            new FileOutputStream(outputFile, false).close();
            new Writer(outputFile, syncMapStorage.getTokenToMetaDataMapList().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println("----------end here----------");
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
