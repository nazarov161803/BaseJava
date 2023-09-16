package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Test
    void insertElement() {
        storage.save(new Resume("uuid4"));
        Assertions.assertEquals(4, storage.getSize());
        Resume[] all = storage.getAll();
        Assertions.assertEquals("uuid1", all[0].getUuid());
        Assertions.assertEquals("uuid2", all[1].getUuid());
        Assertions.assertEquals("uuid3", all[2].getUuid());
        Assertions.assertEquals("uuid4", all[3].getUuid());

    }

    @Test
    void fillDeletedElement() {
        storage.delete("uuid2");
        Resume[] all = storage.getAll();
        Assertions.assertEquals("uuid1", all[0].getUuid());
        Assertions.assertEquals("uuid3", all[1].getUuid());
    }

    @Test
    void getIndex() {
        Resume uuid2 = storage.get("uuid2");
        Assertions.assertEquals("uuid2",uuid2);


    }
}