package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SyncMapStorage {
    private List<Map<String, TokenMetaData>> tokenToMetaDataMapList = new ArrayList<>();

    public synchronized void addMap(Map<String, TokenMetaData> newMap) {
        tokenToMetaDataMapList.add(newMap);
    }

    public List<Map<String, TokenMetaData>> getTokenToMetaDataMapList() {
        return tokenToMetaDataMapList;
    }
}
