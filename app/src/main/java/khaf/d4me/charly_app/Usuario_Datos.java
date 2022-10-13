package khaf.d4me.charly_app;

public class Usuario_Datos {
    //Clase para representar un comentario
    //Campos correspondientes a la base de datos
    int Id_Usuario;
    String Nombre;
    String ApellidoP;
    String ApellidoM;
    String Telefono;
    String Correo;

    //Constructor
    public Usuario_Datos(int _Id_Usuario, String _Nombre , String _ApellidoP, String _ApellidoM, String _Telefono,String _Correo){
        Id_Usuario=_Id_Usuario;
        Nombre=_Nombre;
        ApellidoP=_ApellidoP;
        ApellidoM=_ApellidoM;
        Telefono=_Telefono;
        Correo=_Correo;
    }
    //Represetacion del objeto como cadena de texto
    @Override
    public String toString() {
        return Nombre;
    }
    //Metodos de acceso a cada atribito de la clase
    public int getId_Usuario(){
        return Id_Usuario;
    }

    public String getNombre(){
        return Nombre;
    }
    public String getApellidoP(){
        return ApellidoP;
    }
    public String getApellidoM(){
        return ApellidoM;
    }
    public String getTelefono(){
        return Telefono;
    }
    public String getCorreo(){
        return Correo;
    }
}
