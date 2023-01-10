package main;

import java.io.*;
import java.util.Map;

public class Indexer implements Runnable{
    private final SyncIterator syncIterator;
    private final String hardcodedDirectoryPath;
    private final SyncMapStorage syncMapStorage;

    public Indexer(SyncIterator syncIterator, SyncMapStorage syncMapStorage, String hardcodedDirectoryPath) {
        this.syncIterator = syncIterator;
        this.syncMapStorage = syncMapStorage;
        this.hardcodedDirectoryPath = hardcodedDirectoryPath;
    }

    @Override
    public void run() {
        String nextFile = syncIterator.next();
        while (nextFile != null) {
            System.out.println(Thread.currentThread().getName() +"\t"+ nextFile);
            BufferedReader reader;

            File currentFile = new File(hardcodedDirectoryPath + "\\" + nextFile);
            try {
                reader = new BufferedReader(new FileReader(currentFile));
                Tokenizer tokenizer = new Tokenizer();
                Map<String, TokenMetaData> newMap = tokenizer.streamTokenizer(reader, nextFile);
                syncMapStorage.addMap(newMap);
            } catch (Exception e) {
                e.printStackTrace();
            }

            nextFile = syncIterator.next();
        }
    }
}
