package khaf.d4me.charly_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    private SQLite dbLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btnRegistro, btnCancelar;
        EditText txtNombre, txtApellidoP, txtApellidoM, txtTelefono, txtCorreo, txtUsuario, txtContrasena, txtConfContra;
        TextView lblEstatus;
        dbLocal = new SQLite(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().hide();
        btnRegistro = (Button) findViewById(R.id.btnGuardar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellidoP = (EditText) findViewById(R.id.txtApellidoP);
        txtApellidoM = (EditText) findViewById(R.id.txtApellidoM);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);

        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtContrasena = (EditText) findViewById(R.id.txtContrasena);
        txtConfContra = (EditText) findViewById(R.id.txtConfcontra);

        lblEstatus = (TextView) findViewById(R.id.lblEstatuscontrasena);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().equals("")&&!txtApellidoP.getText().equals("")&&!txtApellidoM.getText().equals("")&&!txtTelefono.getText().equals("")){
                    if(!txtUsuario.getText().equals("")&&lblEstatus.getText().equals("Las contraseñas coinciden")){
                        dbLocal.Guardar_Usuario(txtUsuario.getText().toString(),txtContrasena.getText().toString(), 1, 1);
                        dbLocal.Guardar_Usuario_Datos(dbLocal.Obtener_Usuario_Id(),txtNombre.getText().toString(),txtApellidoP.getText().toString(), txtApellidoM.getText().toString(),
                                txtTelefono.getText().toString(), txtCorreo.getText().toString());
                        Toast.makeText(getApplicationContext(),"Registrado correctamente",Toast.LENGTH_LONG).show();
                        onBackPressed();
                    }else{
                        Toast.makeText(getApplicationContext(),"Es necesario ingresar todos los datos de usuario",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Es necesario ingresar todos los datos personales",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txtConfContra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(txtConfContra.getText().toString().equals(txtContrasena.getText().toString())){
                    lblEstatus.setText("Las contraseñas coinciden");
                    lblEstatus.setTextColor(Color.GREEN);
                }else {
                    lblEstatus.setText("Las contraseñas no coinciden");
                    lblEstatus.setTextColor(Color.RED);
                }
            }
        });
        txtContrasena.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(txtContrasena.getText().toString().equals(txtConfContra.getText().toString())){
                    lblEstatus.setText("Las contraseñas coinciden");
                    lblEstatus.setTextColor(Color.GREEN);
                }else {
                    lblEstatus.setText("Las contraseñas no coinciden");
                    lblEstatus.setTextColor(Color.RED);
                }
            }
        });
    }
}