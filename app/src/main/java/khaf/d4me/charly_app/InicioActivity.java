package khaf.d4me.charly_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;

public class InicioActivity extends AppCompatActivity {
    private SQLite dbLocal;
    private ViewPager viewPager;
    private TabLayout mTabLayout;
    private ImageButton btnRegistros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbLocal = new SQLite(getApplicationContext());
        setContentView(R.layout.activity_inicio);
        getSupportActionBar().hide();
        btnRegistros = findViewById(R.id.imgbtnRegistros);
        initViews();

        btnRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InicioActivity.this, RegistroTiposActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    private void initViews() {
        // initialise the layout
        viewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tabs);

        // setOffscreenPageLimit means number
        // of tabs to be shown in one page
        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // setCurrentItem as the tab position
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setDynamicFragmentToTabLayout();
    }
    // show all the tab using DynamicFragmnetAdapter
    private void setDynamicFragmentToTabLayout() {
        // here we have given 10 as the tab number
        // you can give any number here
        mTabLayout.addTab(mTabLayout.newTab().setText("Mas vendidos"));

        for (int i = 0; i < dbLocal.getCategorias().size(); i++) {
            // set the tab name as "Page: " + i
            mTabLayout.addTab(mTabLayout.newTab().setText(dbLocal.getCategorias().get(i).getCategoria()));
        }
        DynamicFragmentAdapter mDynamicFragmentAdapter = new DynamicFragmentAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());

        // set the adapter
        viewPager.setAdapter(mDynamicFragmentAdapter);

        // set the current item as 0 (when app opens for first time)
        viewPager.setCurrentItem(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            mTabLayout.removeAllTabs();
            // here we have given 10 as the tab number
            // you can give any number here
            mTabLayout.addTab(mTabLayout.newTab().setText("Mas vendidos"));

            for (int i = 0; i < dbLocal.getCategorias().size(); i++) {
                // set the tab name as "Page: " + i
                mTabLayout.addTab(mTabLayout.newTab().setText(dbLocal.getCategorias().get(i).getCategoria()));
            }
            DynamicFragmentAdapter mDynamicFragmentAdapter = new DynamicFragmentAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());

            // set the adapter
            viewPager.setAdapter(mDynamicFragmentAdapter);

            // set the current item as 0 (when app opens for first time)
            viewPager.setCurrentItem(0);
        }
    }
}