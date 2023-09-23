package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void doSave(Resume resume, Object index) {
        storage.add(resume);
    }

    @Override
    public void doUpdate(Resume resume, Object index) {
        storage.set((Integer) index, resume);
    }

    @Override
    public Resume doGet(Object index) {
        return storage.get((int) index);
    }

    @Override
    public void doDelete(Object index) {
        storage.remove((int) index);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    protected boolean isExist(Object index) {
        if (index.getClass().equals(Integer.class)) {
            return (int) index >= 0;
        } else {
            throw new IllegalArgumentException("Index not Integer");
        }
    }
}
