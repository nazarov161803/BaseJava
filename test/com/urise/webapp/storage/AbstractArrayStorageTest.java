package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class AbstractArrayStorageTest {

    protected Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @BeforeEach
    public void beforeEach() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));

    }

    @Test
    public void clear() {
        Assertions.assertEquals(3, storage.getSize());
        storage.clear();
        Assertions.assertEquals(0, storage.getSize());
    }

    @Test
    public void save() {
        storage.save(new Resume("uuid4"));
        Resume[] all = storage.getAll();
        System.out.println();
        Assertions.assertEquals(4, storage.getSize());
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        Assertions.assertEquals(newResume, storage.get(UUID_1));
    }

    @Test
    public void get() {
        Resume newResume = new Resume(UUID_2);
        Assertions.assertEquals(newResume, storage.get(UUID_2));
    }

    @Test
    public void delete() {
        storage.delete(UUID_3);
        Assertions.assertEquals(2, storage.getSize());
    }

    @Test
    public void getAll() {
        Resume[] array = storage.getAll();
        Assertions.assertEquals(3, array.length);
        Assertions.assertEquals(new Resume("uuid1"), array[0]);
        Assertions.assertEquals(new Resume("uuid2"), array[1]);
        Assertions.assertEquals(new Resume("uuid3"), array[2]);

    }

    @Test
    public void size() {
        Assertions.assertEquals(3, storage.getSize());
    }

    @Test()
    public void notExistStorageException() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }

    @Test()
    public void existStorageException() {
        Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(new Resume(UUID_2));
        });
    }
}