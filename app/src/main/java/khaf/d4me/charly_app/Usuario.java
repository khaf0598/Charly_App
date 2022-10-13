package khaf.d4me.charly_app;

public class Usuario {
    //Clase para representar un comentario
        //Campos correspondientes a la base de datos
    int Id_Usuario;
    String Usuario;
    String Contrasena;
    int Tipo;
    int Estatus;

    //Constructor
    public Usuario(int _Id_Usuario,String _Usuario,String _Contrasena, int _Tipo, int _Estatus){
        Id_Usuario=_Id_Usuario;
        Usuario=_Usuario;
        Contrasena=_Contrasena;
        Tipo=_Tipo;
        Estatus=_Estatus;
    }
    //Represetacion del objeto como cadena de texto
    @Override
    public String toString() {
        return Usuario;
    }
    //Metodos de acceso a cada atribito de la clase
    public int getId_Usuario(){
        return Id_Usuario;
    }

    public String getUsuario(){
        return Usuario;
    }
    public String getContrasena(){
        return Contrasena;
    }
    public int getTipo(){
        return Id_Usuario;
    }
    public int getEstatus(){
        return Id_Usuario;
    }
}

