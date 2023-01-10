package main;

import com.sun.security.jgss.GSSUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.*;

public class Tokenizer {
    private static final int QUOTE_CHARACTER = '\'';
    private static final int DOUBLE_QUOTE_CHARACTER = '"';
    private static final int DOT_CHARACTER = '.';
    private static final int DOUBLE_DOT_CHARACTER = ':';
    private static final int SEMICOLON_CHARACTER = ';';
    private static final int QUESTION_MARK_CHARACTER = '?';
    private static final int EXCLAMATION_MARK_CHARACTER = '!';
    private static final int UNDERSCORE_CHARACTER = '_';


    public Map<String, TokenMetaData> streamTokenizer(BufferedReader reader, String fileName) throws IOException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(reader);
        Map<String, TokenMetaData> tokens = new HashMap<>();

        streamTokenizer.quoteChar(QUOTE_CHARACTER);
        streamTokenizer.quoteChar(DOUBLE_QUOTE_CHARACTER);

//        streamTokenizer.whitespaceChars(0, 32);
//        streamTokenizer.whitespaceChars(128, 256);

        streamTokenizer.ordinaryChar(DOT_CHARACTER);
        streamTokenizer.ordinaryChar(DOUBLE_DOT_CHARACTER);
        streamTokenizer.ordinaryChar(SEMICOLON_CHARACTER);
        streamTokenizer.ordinaryChar(QUESTION_MARK_CHARACTER);
        streamTokenizer.ordinaryChar(EXCLAMATION_MARK_CHARACTER);

        streamTokenizer.wordChars(UNDERSCORE_CHARACTER, UNDERSCORE_CHARACTER);

        int currentToken = streamTokenizer.nextToken();
        while (currentToken != StreamTokenizer.TT_EOF) {

            if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER
                    || streamTokenizer.ttype == StreamTokenizer.TT_WORD) {
                String stringToken = streamTokenizer.sval;
//                System.out.println("|"+stringToken+"|");
                if (!(stringToken == null)) {
                    stringToken.toLowerCase(Locale.ROOT);
                } else {
                    break;
//                    continue;
                }
                if (tokens.containsKey(stringToken)) {
                    tokens.get(stringToken).addTokenMeta(fileName, streamTokenizer.lineno());
                } else {
                    tokens.put(stringToken, new TokenMetaData(fileName, streamTokenizer.lineno()));
                }
            }

            currentToken = streamTokenizer.nextToken();
        }

        return tokens;
    }
}
