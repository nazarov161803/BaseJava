package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    public void doSave(Resume resume, Object uuid) {
        storage.put(uuid.toString(), resume);

    }

    @Override
    public void doUpdate(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);

    }

    @Override
    protected Object getSearchKey(String uuid) {
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
    public Resume[] getAll() {
        return storage.values().stream().sorted(Resume::compareTo).toArray(Resume[]::new);
    }

    @Override
    public int getSize() {
        return storage.size();
    }
}
