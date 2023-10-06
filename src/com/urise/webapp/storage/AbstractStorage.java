package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void save(Resume resume) {
        LOG.info("Saving Resume " + resume.getUuid());
        SK searchKey = getSearchKey(resume.getUuid());
        if (isExist(searchKey)) {
            LOG.info(MessageFormat.format("Resume with uuid - {0} already exist", resume.getUuid()));
            throw new ExistStorageException(resume.getUuid());
        } else {
            doSave(resume, searchKey);
        }
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Updating Resume " + resume.getUuid());
        SK searchKey = getSearchKey(resume.getUuid());
        if (isExist(searchKey)) {
            doUpdate(resume, searchKey);
        } else {
            LOG.info(MessageFormat.format("Resume with uuid - {0} doesn\'t exist", resume.getUuid()));
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Deleting Resume " + uuid);
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            doDelete(searchKey);
        } else {
            LOG.info(MessageFormat.format("Resume with uuid - {0} doesn\'t exist", uuid));
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            return doGet(searchKey);
        } else {
            LOG.info(MessageFormat.format("Resume with uuid - {0} doesn\'t exist", uuid));
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doGetAll();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> doGetAll();

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract SK getSearchKey(String uuid);

}
