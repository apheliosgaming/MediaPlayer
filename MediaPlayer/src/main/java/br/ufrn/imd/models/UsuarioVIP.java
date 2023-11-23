package br.ufrn.imd.models;

public class UsuarioVIP extends Usuario{
    private double codigo;
    
    public UsuarioVIP(double codigo){
        super();
        this.codigo = codigo;
    }
    
    public double getCodigo() {
    return codigo;
  }

  public void setCodigo(double codigo) {
    this.codigo = codigo;
  }
    
}
