package br.ufrn.imd.models;

/**
 * Uma classe que representa um usuário.
 * Cada usuário possui um ID, um nome de usuário, um endereço de e-mail e uma senha.
 */
public class Usuario {
    private int id;
    private String username;
    private String email;
    private String senha;

    /**
     * Constrói um novo objeto Usuario com os parâmetros especificados.
     *
     * @param id O identificador único do usuário.
     * @param username O nome de usuário escolhido pelo usuário.
     * @param email O endereço de e-mail associado ao usuário.
     * @param senha A senha do usuário.
     */
    public Usuario(int id, String username, String email, String senha){
        this.id = id;
        this.username = username;
        this.email = email;
        this.senha = senha;
    }

    /**
     * Obtém o identificador único do usuário.
     *
     * @return O ID do usuário.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador único para o usuário.
     *
     * @param id O novo ID para o usuário.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome de usuário do usuário.
     *
     * @return O nome de usuário do usuário.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Define o nome de usuário para o usuário.
     *
     * @param username O novo nome de usuário para o usuário.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtém o endereço de e-mail do usuário.
     *
     * @return O endereço de e-mail do usuário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o endereço de e-mail para o usuário.
     *
     * @param email O novo endereço de e-mail para o usuário.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém a senha do usuário.
     *
     * @return A senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha para o usuário.
     *
     * @param senha A nova senha para o usuário.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

}
