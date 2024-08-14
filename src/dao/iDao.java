package dao;

import model.odontologo;

import java.util.List;

public interface iDao<T> {
    T save(T t);
    void update(T t, int id);
    void delete(int id);
    T get(int id);
    List<T> getAll();

    void guardarOdontologo(odontologo odontologo) throws Exception;

    List<odontologo> listarOdontologos() throws Exception;
}
