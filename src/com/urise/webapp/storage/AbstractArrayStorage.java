package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.text.MessageFormat;
import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > 0) {
            throw new ExistStorageException(MessageFormat.format("Resume with uuid - {0} already exist", resume.getUuid()));
        } else if (size == storage.length) {
            throw new StorageException("Stack Over flow", resume.getUuid());
        } else {
            insertElement(resume, index);
            size++;
        }
    }

    protected abstract void insertElement(Resume resume, int index);

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            throw new NotExistStorageException(MessageFormat.format("Resume with uuid - {0} doesn't exist", resume.getUuid()));
        } else {
            storage[index] = resume;
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(MessageFormat.format("Resume with uuid - {0} doesn't exist", uuid));
        } else {
            return storage[index];
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > 0) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    fillDeletedElement(index);
                    storage[size - 1] = null;
                    size--;
                }
            }
        } else {
            throw new NotExistStorageException(MessageFormat.format("Resume with uuid - {0} doesn't exist", uuid));
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        Resume[] allResumes = new Resume[size];
        System.arraycopy(storage, 0, allResumes, 0, size);
        return allResumes;
    }

    @Override
    public int getSize() {
        return size;
    }


    public abstract int getIndex(String uuid);

    protected abstract void fillDeletedElement(int index);

}


