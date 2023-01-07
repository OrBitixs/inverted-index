package main;

import java.io.File;

public class Indexer implements Runnable{
    private final SyncIterator syncIterator;
    private final String hardcodedDirectoryPath;

    public Indexer(SyncIterator syncIterator, String hardcodedDirectoryPath) {
        this.syncIterator = syncIterator;
        this.hardcodedDirectoryPath = hardcodedDirectoryPath;
    }

    @Override
    public void run() {
        String nextFile = syncIterator.next();
        while (nextFile != null) {
            System.out.println(Thread.currentThread().getName() +"\t"+ nextFile);

            Tokenizer tokenizer = new Tokenizer();
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            nextFile = syncIterator.next();
        }
    }
}
