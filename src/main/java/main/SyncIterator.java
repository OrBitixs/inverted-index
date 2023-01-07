package main;

import java.util.Iterator;
import java.util.Set;

public class SyncIterator {
    private Iterator<String> fileIter;

    public SyncIterator(Set<String> fileSet) {
        fileIter = fileSet.iterator();
    }

    public synchronized String next() {
        if (fileIter.hasNext()) {
            return fileIter.next();
        } else {
            return null;
        }
    }
}
