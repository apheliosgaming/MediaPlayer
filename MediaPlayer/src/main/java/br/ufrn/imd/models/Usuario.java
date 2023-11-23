package br.ufrn.imd.models;

public class Usuario {
  private String email;
  private int senha;
    
    public UsuarioVIP(double codigo){
        super();
    }
    
    public String getEmail() {
        return email;
  }

    public void setEmail(String email) {
        this.email = email;
  }
    public int getSenha() {
        return senha;
  }

    public void setSenha(int senha) {
        this.senha = senha;
  }
}
