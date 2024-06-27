package gift.model.repository;

import java.util.List;

public interface RepositoryInterface<T, ID> {
    void save(final T entity);
    T find(final ID id);
    void delete(final T entity);
    List<T> findAll();
}
