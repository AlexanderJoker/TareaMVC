package com.example.tareamvc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class lista extends AppCompatActivity implements Observer, View.OnClickListener{

    EmpleadosController emplecontroller = new EmpleadosController();
    int employe_id;
    ListView lista;
    ArrayList<Empleados> empleados_lista;
    ArrayList<String> empleados_lista_ = new ArrayList<>();
    Button btneditar, btnatras;
    TextView txtBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        lista = (ListView) findViewById(R.id.resulempleados);
        txtBuscar = (TextView) findViewById(R.id.txtbuscar);

        employe_id = 0;
        emplecontroller.openDataBase(getApplicationContext());

        empleados_lista = emplecontroller.lista();
        for (int i = 0; i < empleados_lista.size(); i++) {
            empleados_lista_.add(empleados_lista.get(i).getNombre() + "-" + empleados_lista.get(i).getApellidos());
        }
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, empleados_lista_);
        lista.setAdapter(adaptador);

        txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {adaptador.getFilter().filter(s);}
            @Override
            public void afterTextChanged(Editable s) {}
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String nombre = "";
                for (int i = 0; i < empleados_lista.size(); i++){
                    if((empleados_lista.get(i).getNombre() + "-" + empleados_lista.get(i).getApellidos()).equalsIgnoreCase(lista.getItemAtPosition(position).toString())){
                        employe_id = empleados_lista.get(i).getId();
                        nombre = empleados_lista.get(i).getNombre() + "-" + empleados_lista.get(i).getApellidos();
                    }
                }
                Toast.makeText(lista.this, "Seleccionaste a: "+nombre, Toast.LENGTH_LONG).show();
            }
        });

        btneditar = (Button) findViewById(R.id.btneditar);
        btneditar.setOnClickListener(this);

        btnatras = (Button) findViewById(R.id.btnregreso);
        btnatras.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnregreso:
                Intent intent1 = new Intent(lista.this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.btneditar:
                if(employe_id == 0){
                    dialog_selecciona();
                }else{
                    Intent intent = new Intent(lista.this, MainActivity.class);
                    intent.putExtra("id", String.valueOf(employe_id));
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void update(Observable observable, Object o) {}

    private void dialog_selecciona() {
        new AlertDialog.Builder(lista.this)
                .setTitle("Selecciona un item!")
                .setMessage("No seleccionaste nada")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }
}