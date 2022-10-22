package khaf.d4me.charly_app;

public class Categorias {
    int Id_Categoria;
    String Categoria;
    int Estatus;

    //Constructor
    public Categorias(int _Id_Categoria,String _Categoria, int _Estatus){
        Id_Categoria=_Id_Categoria;
        Categoria=_Categoria;
        Estatus=_Estatus;
    }
    //Represetacion del objeto como cadena de texto
    @Override
    public String toString() {
        return Categoria;
    }
    //Metodos de acceso a cada atribito de la clase
    public int getId_Categoria(){
        return Id_Categoria;
    }

    public String getCategoria(){
        return Categoria;
    }
    public int getEstatus(){
        return Estatus;
    }
}
