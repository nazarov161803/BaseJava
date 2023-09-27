package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractStorageTest {

    protected Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1 = new Resume(UUID_1, "John Snow");
    private static final Resume RESUME_2 = new Resume(UUID_2, "Bob Marley");
    private static final Resume RESUME_3 = new Resume(UUID_3, "Harry Potter");
    private static final Resume RESUME_4 = new Resume(UUID_4, "Dunno on the Moon");

    @BeforeEach
    public void beforeEach() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_4);

    }

    @Test
    public void clear() {
        assertEquals(4, storage.getSize());
        storage.clear();
        assertEquals(0, storage.getSize());
    }

    @Test
    public void save() {
        Resume newResume = new Resume("uuid5", "Dominic Toretto");
        storage.save(newResume);
        assertEquals(5, storage.getSize());
        assertEquals(newResume, storage.get("uuid5"));
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "Buzz Lighter");
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
        assertEquals(RESUME_2, storage.get(RESUME_2.getUuid()));
    }

    @Test
    public void delete() {
        storage.delete(UUID_3);
        assertEquals(3, storage.getSize());
    }

    @Test
    public void getAllSorted() {
        List<Resume> allSorted = storage.getAllSorted();
        assertEquals(4, allSorted.size());
        System.out.println();
        assertEquals(allSorted, Arrays.asList(RESUME_2, RESUME_4, RESUME_3, RESUME_1));
    }

    @Test
    public void size() {
        assertEquals(4, storage.getSize());
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
            storage.save(new Resume(UUID_2, "Bob Marley"));
        });
    }
}