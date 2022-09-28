/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume resume) {
        if (get(resume.uuid) == null) {
            storage[size] = resume;
            size++;
        } else {
            System.out.printf("Resume with uuid %s already exist.", resume.uuid);
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equalsIgnoreCase(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equalsIgnoreCase(uuid)) {
                storage[i] = storage[i + 1];
                size--;
                if (size - i >= 0) System.arraycopy(storage, i + 1, storage, i, size - i);
                storage[size] = null;
            }
        }
    }

    void update(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equalsIgnoreCase(resume.uuid)) {
                storage[i] = resume;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[size];
        System.arraycopy(storage, 0, result, 0, size);
        return result;
    }

    int size() {
        return size;
    }
}
