package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.text.MessageFormat;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume resume, int index) {
        storage [size] = resume;
    }

    @Override
    protected void fillDeletedElement(int index)  {
        storage [index] = storage [size - 1];
    }

    @Override
    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
