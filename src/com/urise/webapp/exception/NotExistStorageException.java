package com.urise.webapp.exception;

import java.text.MessageFormat;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super(MessageFormat.format("Resume with uuid - {0} doesn't exist", uuid), uuid);
    }
}
