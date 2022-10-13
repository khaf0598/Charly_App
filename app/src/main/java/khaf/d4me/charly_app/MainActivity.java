package khaf.d4me.charly_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SQLite dbLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btnIniciar, btnRegistrar;
        EditText txtUsuario, txtContrasena;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        dbLocal = new SQLite(getApplicationContext());
        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        txtUsuario = (EditText) findViewById(R.id.txtUsuarioIni);
        txtContrasena = (EditText) findViewById(R.id.txtPasswordIni);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbLocal.login(txtUsuario.getText().toString(),txtContrasena.getText().toString())) {
                    Intent openInicio = new Intent(MainActivity.this, InicioActivity.class);
                    txtUsuario.setText("");
                    txtContrasena.setText("");
                    startActivity(openInicio);
                }else{
                    txtUsuario.requestFocus();
                    Toast.makeText(getApplicationContext(),"El usuario no existe", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialogButtonClicked(view);
            }
        });

    }

    public void showAlertDialogButtonClicked(View view)
    {
        // Create an alert builder
        AlertDialog.Builder builder
                = new AlertDialog.Builder(this);
        builder.setTitle("Validar Administrador");
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
        builder.setView(customLayout);
        // add a button
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                // send data from the
                // AlertDialog to the Activity
                EditText editText = customLayout.findViewById(R.id.editText);
                sendDialogDataToActivity(editText.getText().toString());
            }
        });
        // create and show
        // the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Do something with the data
    // coming from the AlertDialog
    private void sendDialogDataToActivity(String data)
    {
        if(data.equals("123")){
            Intent openRegistro = new Intent(MainActivity.this, RegistroActivity.class);
            startActivity(openRegistro);
        }
    }
}