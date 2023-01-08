package main;

import java.util.LinkedList;
import java.util.List;

public class TokenMetaData {
    private List<FileLinePair> fileLinePairLinkedList = new LinkedList<>();

    public void print() {
        for (FileLinePair fileLinePair : fileLinePairLinkedList) {
            System.out.println(fileLinePair.fileName + "__" + fileLinePair.lineNumber);
        }
    }

    public TokenMetaData(String fileName, int lineNumber) {
        fileLinePairLinkedList.add(new FileLinePair(fileName, lineNumber));
    }

    public List<FileLinePair> getFileLinePairLinkedList() {
        return fileLinePairLinkedList;
    }

    public void addTokenMeta(String fileName, int lineNumber) {
        fileLinePairLinkedList.add(new FileLinePair(fileName, lineNumber));
    }

    public void addTokenMeta(TokenMetaData tokenMetaData) {
        fileLinePairLinkedList.addAll(tokenMetaData.fileLinePairLinkedList);
//        System.out.println("new tokenMeta");
    }

    protected class FileLinePair implements Comparable{
        protected String fileName;
        protected int lineNumber;

        public FileLinePair(String fileName, int lineNumber) {
            this.fileName = fileName;
            this.lineNumber = lineNumber;
        }

        public String getFileName() {
            return fileName;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        @Override
        public int compareTo(Object o) {
            FileLinePair another = (FileLinePair) o;
            if (another == null) {
                throw new NullPointerException();
            }

            return Integer.compare(this.lineNumber, another.getLineNumber());
        }
    }
}
