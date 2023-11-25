package br.ufrn.imd.DAO;

import br.ufrn.imd.models.Diretorio;

import java.util.List;

public interface DAOI<T> {
    boolean create(T obj);
    T getById(int id);
    List<T> getAll();
    boolean update(T obj);
    boolean delete(int id);

}
