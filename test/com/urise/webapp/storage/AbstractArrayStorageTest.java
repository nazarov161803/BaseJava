package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertEquals(3, storage.getSize());
        storage.clear();
        assertEquals(0, storage.getSize());
    }

    @Test
    public void save() {
        Resume newResume4 = new Resume("uuid4");
        storage.save(newResume4);
        assertEquals(4, storage.getSize());
        assertEquals(newResume4, storage.get("uuid4"));
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test()
    public void updateNotExistStorageException() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.update(new Resume("dummy"));
        });
    }

    @Test
    public void get() {
        Resume newResume = new Resume(UUID_2);
        assertEquals(newResume, storage.get(UUID_2));
    }

    @Test
    public void delete() {
        storage.delete(UUID_3);
        assertEquals(2, storage.getSize());
    }

    @Test
    public void getAll() {
        Resume[] array = storage.getAll();
        assertEquals(3, array.length);
        assertEquals(new Resume("uuid1"), array[0]);
        assertEquals(new Resume("uuid2"), array[1]);
        assertEquals(new Resume("uuid3"), array[2]);

    }

    @Test
    public void size() {
        assertEquals(3, storage.getSize());
    }

    @Test()
    public void notExistStorageException() {
        assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
        storage.delete("uuid2");
        assertThrows(NotExistStorageException.class, () -> {
            storage.get("uuid2");
        });
    }

    @Test()
    public void existStorageException() {
        assertThrows(ExistStorageException.class, () -> {
            storage.save(new Resume(UUID_2));
        });
    }
}