package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Test
    void insertElement() {
        storage.save(new Resume("uuid5"));
        storage.save(new Resume("uuid4"));
        Resume[] all = storage.getAll();
        Assertions.assertEquals("uuid4", all[3].getUuid());
        Assertions.assertEquals("uuid5", all[4].getUuid());

    }

    @Test
    void fillDeletedElement() {
        storage.save(new Resume("uuid4"));
        storage.save(new Resume("uuid5"));
        storage.delete("uuid3");
        Resume[] all = storage.getAll();
        Assertions.assertEquals("uuid4", all[2].getUuid());
        Assertions.assertEquals("uuid5", all[3].getUuid());


    }

    @Test
    void getIndex() {
    }
}