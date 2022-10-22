package khaf.d4me.charly_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class RegistroTiposActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner spinCategorias, spinEstatusCat;
    Button btnGuardarCat;
    private SQLite dbLocal;
    EditText txtCategoria;
    SimpleCursorAdapter catSpinnerAdapter;
    TabLayout tbRegistros;
    LinearLayout llCategorias, llProductos;
    RecyclerView rvCategorias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tipos);
        getSupportActionBar().hide();
        dbLocal = new SQLite(getApplicationContext());
        btnGuardarCat = findViewById(R.id.btnGuardarCat);
        spinCategorias = findViewById(R.id.spCategoria);
        spinEstatusCat = findViewById(R.id.spEstatusCat);
        txtCategoria = findViewById(R.id.txtNomCat);
        tbRegistros = findViewById(R.id.tbRegistros);
        llCategorias = findViewById(R.id.llCategorias);
        llProductos = findViewById(R.id.llProductos);
        rvCategorias = findViewById(R.id.rvCategorias);

        //Creando Adaptador para GenreSpinner
        int[] id = { R.id.txtListElement };
        String[] DaysOfWeek = new String[] { "Categoria" };
        Cursor c = dbLocal.getAllCategories();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.list_template, c, DaysOfWeek, id, 0);
        spinCategorias.setAdapter(adapter);

        rvCategorias.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        AdaptadorCategorias adaptcat = new AdaptadorCategorias(dbLocal.getCategorias(),getApplicationContext());
        rvCategorias.setAdapter(adaptcat);

        tbRegistros.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().toString().equals("Registro Categorias")){
                    llCategorias.setVisibility(View.VISIBLE);
                    llProductos.setVisibility(View.GONE);
                }else{
                    llCategorias.setVisibility(View.GONE);
                    llProductos.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnGuardarCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtCategoria.getText().toString().equals("")){
                    int act = 0;
                    if(spinEstatusCat.getSelectedItem().toString().equals("Activo"))
                        act = 1;
                    dbLocal.Guardar_Categorias(txtCategoria.getText().toString(), act);
                    Toast.makeText(RegistroTiposActivity.this, "Categoria guardada", Toast.LENGTH_SHORT).show();
                    txtCategoria.setText("");
                    AdaptadorCategorias adaptcat = new AdaptadorCategorias(dbLocal.getCategorias(),getApplicationContext());
                    rvCategorias.setAdapter(adaptcat);
                }else{
                    Toast.makeText(RegistroTiposActivity.this, "Es necesario ingresar categoria", Toast.LENGTH_SHORT).show();
                    txtCategoria.requestFocus();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}