package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {

    final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected boolean isExist(Object uuid) {
        return storage.containsKey(uuid.toString());
    }

    @Override
    public void doSave(Resume resume, Object uuid) {
        storage.put(uuid.toString(), resume);

    }

    @Override
    public void doUpdate(Resume resume, Object searchKey) {
        storage.put(searchKey.toString(), resume);

    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    public Resume doGet(Object uuid) {
        return storage.get(uuid.toString());
    }

    @Override
    public void doDelete(Object uuid) {
        storage.remove(uuid.toString());
    }

    @Override
    public List<Resume> doGetAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int getSize() {
        return storage.size();
    }
}
