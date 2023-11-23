package br.ufrn.imd.DAO;

import java.util.List;

public interface DAOI<T> {
    T getById(int id);
    List<T> listar(T obj);
    void deletar(T obj);
    void inserir(T obj);
}
