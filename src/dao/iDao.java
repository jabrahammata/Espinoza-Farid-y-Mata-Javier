package dao;

import java.util.List;

public interface iDao<T> {
    T save(T t);
    void update(T t, int id);
    void delete(int id);
    T get(int id);
    List<T> getAll();
}
