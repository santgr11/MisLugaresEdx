package com.example.mislugares;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static Lugares lugares = new LugaresVector();
    private RecyclerView recyclerView;
    public AdaptadorLugares adaptador;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adaptador = new AdaptadorLugares(this, lugares);
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adaptador.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VistaLugarActivity.class);
                i.putExtra("id", (long) recyclerView.getChildAdapterPosition(v));
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.menu_buscar) {
            lanzarVistaLugar(null);
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id. acercaDe) {
            lanzarAcercaDe(null);
            return true;
        }
        if  (id == R.id.action_settings) {
            lanzarPreferencias(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void lanzarAcercaDe(View view){
        Intent i = new Intent(this, AcercaDeActivity.class);
        startActivity(i);
    }

    public void lanzarPreferencias(View view) {
        Intent i = new Intent(this, PreferenciasActivity.class);
        startActivity(i);
    }

    public void mostrarPreferencias(View view){
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        String s = "notificaciones: "+ pref.getBoolean("notificaciones",true)
                +", máximo a listar: " + pref.getString("maximo","?");
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void lanzarVistaLugar(View view){
        final EditText entrada = new EditText(this);
        entrada.setText("0");
        new AlertDialog.Builder(this)
                .setTitle("Selección de lugar")
                .setMessage("indica su id:")
                .setView(entrada)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        long id = Long.parseLong(entrada.getText().toString());
                        Intent i = new Intent(MainActivity.this,
                                VistaLugarActivity.class);
                        i.putExtra("id", id);
                        startActivity(i);
                    }})
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
