package khaf.d4me.charly_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLite extends SQLiteOpenHelper {
    private static final String USERS_TABLE_CREATE = "CREATE TABLE Usuarios (Id_Usuario INTEGER PRIMARY KEY AUTOINCREMENT, Usuario TEXT, Contrasena TEXT, Tipo INTEGER, Estatus INTEGER);";
    private static final String USERSPERS_TABLE_CREATE = "CREATE TABLE Usuarios_Datos (Id_Usuario_Personales INTEGER PRIMARY KEY AUTOINCREMENT, Id_Usuario INTEGER, Nombre TEXT, " +
            "Apellido_P TEXT, Apellido_M TEXT, Telefono TEXT, Correo TEXT);";
    private static final String CATEGORIAS_TABLE_CREATE = "CREATE TABLE Categorias (Id_Usuario INTEGER PRIMARY KEY AUTOINCREMENT, Usuario TEXT, Contrasena TEXT, Tipo INTEGER, Estatus INTEGER);";
    private static final String DB_NAME = "Charly.sql";
    private static final int DB_VERSION = 1;
    private final SQLiteDatabase db;

    public SQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(USERS_TABLE_CREATE);
        sqLiteDatabase.execSQL(USERSPERS_TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    //Funciones para insertar
    public void Guardar_Usuario(String usuario, String contrasena, int tipo, int estatus){
        ContentValues cv = new ContentValues();
        cv.put("Usuario", usuario);
        cv.put("Contrasena", contrasena);
        cv.put("Tipo", tipo);
        cv.put("Estatus", estatus);
        db.insert("Usuarios", null, cv);
    }
    public void Guardar_Usuario_Datos(int Id_Usuario, String Nombre, String Apellido_P, String Apellido_M, String Telefono, String correo){
        ContentValues cv = new ContentValues();
        cv.put("Id_Usuario", Id_Usuario);
        cv.put("Nombre", Nombre);
        cv.put("Apellido_P", Apellido_P);
        cv.put("Apellido_M", Apellido_M);
        cv.put("Telefono", Telefono);
        cv.put("Correo", correo);
        db.insert("Usuarios_Datos", null, cv);
    }

    //Borrar a partir de su id
    public void borrarUsuario(int id){
        String[] args = new String[]{String.valueOf(id)};
        db.delete("Usuarios", "Id_Usuario=?", args);
    }
    //Borrar a partir de su id
    public void borrarDatosUsuario(int id){
        String[] args = new String[]{String.valueOf(id)};
        db.delete("Usuarios_Datos", "Id_Usuario=?", args);
    }

    //Obtener la lista de comentarios en la base de datos
    public ArrayList<Usuario> getUsuarios(){
        //Creamos el cursor
        ArrayList<Usuario>lista=new ArrayList<Usuario>();
        Cursor c = db.rawQuery("select Id_Usuario, Usuario, Contrasena, Tipo, Estatus from Usuarios", null);
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {
                //Asignamos el valor en nuestras variables para crear un nuevo objeto Comentario
                String Usuario = c.getString(1);
                int Tipo = c.getInt(3);
                int Estatus = c.getInt(4);
                int id=c.getInt(0);
                Usuario us = new Usuario(id, Usuario,"", Tipo,Estatus);
                //Añadimos el comentario a la lista
                lista.add(us);
            } while (c.moveToNext());
        }

        //Cerramos el cursor
        c.close();
        return lista;
    }
    //Obtener la lista de comentarios en la base de datos
    public ArrayList<Usuario_Datos> getUsuariosDatos(){
        //Creamos el cursor
        ArrayList<Usuario_Datos>lista=new ArrayList<Usuario_Datos>();
        Cursor c = db.rawQuery("select Id_Usuario, Nombre, Apellido_P, Apellido_M, Telefono, Correo from Usuarios_Datos", null);
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {
                //Asignamos el valor en nuestras variables para crear un nuevo objeto Comentario
                String Nombre = c.getString(1);
                String ApeP = c.getString(2);
                String ApeM = c.getString(3);
                String Tel = c.getString(4);
                String Corr = c.getString(5);
                int id=c.getInt(0);
                Usuario_Datos us = new Usuario_Datos(id, Nombre, ApeP, ApeM, Tel, Corr);
                //Añadimos el comentario a la lista
                lista.add(us);
            } while (c.moveToNext());
        }

        //Cerramos el cursor
        c.close();
        return lista;
    }
    public boolean login(String usu, String contra) {
        SQLiteDatabase db = this.getWritableDatabase(); //get the database that was created in this instance
        Cursor c = db.rawQuery("select * from Usuarios where Usuario =? And Contrasena = ?", new String[]{usu, contra});
        if (c.moveToLast()) {
            return true;

        }else {
            return false;
        }
    }

    public int Obtener_Usuario_Id(){
        Cursor cursor = db.rawQuery("SELECT MAX(Id_Usuario) FROM Usuarios", null);
        int id = 0;
        if(cursor.moveToLast()){
            //name = cursor.getString(column_index);//to get other values
            id = cursor.getInt(0);//to get id, 0 is the column index
        }
        return id;
    }
}