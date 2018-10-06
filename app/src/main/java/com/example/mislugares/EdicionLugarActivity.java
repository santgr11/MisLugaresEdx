package com.example.mislugares;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class EdicionLugarActivity extends AppCompatActivity {

    private long id;
    private Lugar lugar;
    private EditText nombre;
    private Spinner tipo;
    private EditText direccion;
    private EditText telefono;
    private EditText url;
    private EditText comentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edicion_lugar);
        Bundle extras = getIntent().getExtras();

        id = extras.getLong("id", -1);
        lugar= MainActivity.adaptador.lugarPosicion((int) id);

        tipo = (Spinner) findViewById(R.id.tipo);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, TipoLugar.getNombres());
        adaptador.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);
        tipo.setAdapter(adaptador);
        tipo.setSelection(lugar.getTipo().ordinal());

        nombre = (EditText) findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());

        findViewById(R.id.direccion).setVisibility(View.VISIBLE);
        direccion = (EditText) findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());

        findViewById(R.id.telefono).setVisibility(View.VISIBLE);
        telefono = (EditText) findViewById(R.id.telefono);
        telefono.setText(Integer.toString(lugar.getTelefono()));

        findViewById(R.id.url).setVisibility(View.VISIBLE);
        url = (EditText) findViewById(R.id.url);
        url.setText(lugar.getUrl());

        findViewById(R.id.comentario).setVisibility(View.VISIBLE);
        comentario = (EditText) findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edicion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idItem = item.getItemId();

        if( idItem == R.id.edicion_aceptar) {
            lugar.setNombre(nombre.getText().toString());
            lugar.setTipo(TipoLugar.values()[tipo.getSelectedItemPosition()]);
            lugar.setDireccion(direccion.getText().toString());
            lugar.setTelefono(Integer.parseInt(telefono.getText().toString()));
            lugar.setUrl(url.getText().toString());
            lugar.setComentario(comentario.getText().toString());
            MainActivity.lugares.actualiza((int) id,lugar);
            finish();
        } if (idItem == R.id.edicion_cancelar) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
