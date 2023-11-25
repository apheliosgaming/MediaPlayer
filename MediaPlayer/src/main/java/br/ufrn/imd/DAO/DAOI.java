package br.ufrn.imd.DAO;

import java.util.List;

/**
 * Interface genérica para operações básicas de acesso a dados (CRUD) em um banco de dados.
 *
 * @param <T> Tipo de objeto que a interface manipula.
 */
public interface DAOI<T> {
    /**
     * Cria um novo objeto no banco de dados.
     *
     * @param obj O objeto a ser criado.
     * @return true se a criação for bem-sucedida, false caso contrário.
     */
    boolean create(T obj);

    /**
     * Obtém um objeto do banco de dados pelo seu ID.
     *
     * @param id O ID do objeto a ser recuperado.
     * @return O objeto encontrado ou null se não encontrado.
     */
    T getById(int id);

    /**
     * Obtém todos os objetos do banco de dados.
     *
     * @return Uma lista contendo todos os objetos.
     */
    List<T> getAll();

    /**
     * Atualiza um objeto no banco de dados.
     *
     * @param obj O objeto a ser atualizado.
     * @return true se a atualização for bem-sucedida, false caso contrário.
     */
    boolean update(T obj);

    /**
     * Exclui um objeto do banco de dados pelo seu ID.
     *
     * @param id O ID do objeto a ser excluído.
     * @return true se a exclusão for bem-sucedida, false caso contrário.
     */
    boolean delete(int id);

}
