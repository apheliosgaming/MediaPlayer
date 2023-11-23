package br.ufrn.imd.models;

import br.ufrn.imd.models.Playlist;

public class UsuarioVIP extends Usuario{
    private double codigo;
    private Playlist playlist;
    
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
    public Playlist getPlaylist(){
        
}
    public void setPlaylist(Playlist playlist) {
        
  }
    
