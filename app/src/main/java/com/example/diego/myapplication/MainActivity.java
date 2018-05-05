package com.example.diego.myapplication;
//..
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//..
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;
    private Button btnAceptar;
    Button btnGuardar;
    Button btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context contexto = this;
        SharedPreferences sharprefs = getSharedPreferences("ArchivoSP", MODE_PRIVATE);

        //Obtenemos una referencia a los controles de la interfaz
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        btnAceptar = (Button)findViewById(R.id.btnAceptar);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnMostrar = (Button)findViewById(R.id.btnMostrar);

        btnGuardar.setOnClickListener( new View.OnClickListener(){
            public void onClick( View v ){

                SharedPreferences sharpref = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sharpref.edit();
                editor.putString("MiDato", txtNombre.getText().toString());

                editor.commit();    // guardo como preferencia compartida...
            }
        });

        btnMostrar.setOnClickListener( new View.OnClickListener(){
            public void onClick( View v ){

                SharedPreferences sharpref = getPreferences(MODE_PRIVATE);
                String valor = sharpref.getString("MiDato", "No Hay dato");
                Toast.makeText(getApplicationContext(), "Dato Guardado: "+valor, Toast.LENGTH_SHORT).show();

            }
        });

        //Implementamos el evento click del botón
        btnAceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent = new Intent(MainActivity.this, SaludoActivity.class);

                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();
                b.putString("NOMBRE", txtNombre.getText().toString());

                //Añadimos la información al intent
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);
            }





        });
    }
}
