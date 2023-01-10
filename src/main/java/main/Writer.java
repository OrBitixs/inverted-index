package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class Writer {
    public Writer(String outputFileName, Map<String, TokenMetaData> map) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true));
        writer.write("");

        map.forEach((token, tokenMetaData) -> {
            final String[] currentFile = {""};
            try {
                writer.append("\n|"+token+"|\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            tokenMetaData.getFileLinePairLinkedList().forEach(fileLinePair -> {
                if (!Objects.equals(fileLinePair.fileName, currentFile[0])) {
                    currentFile[0] = fileLinePair.fileName;
                    try {
                        writer.append("\n["+currentFile[0]+"]\n");
                        writer.append(fileLinePair.lineNumber +" ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        writer.append(fileLinePair.lineNumber +" ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        });

        writer.flush();
        writer.close();
    }

}
