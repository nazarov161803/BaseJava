package com.urise.webapp.exception;

import java.text.MessageFormat;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super(MessageFormat.format("Resume with uuid - {0} already exist", uuid), uuid);
    }
}
