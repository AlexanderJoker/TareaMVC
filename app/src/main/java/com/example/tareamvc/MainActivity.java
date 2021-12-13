package com.example.tareamvc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer, View.OnClickListener{

    EmpleadosController empleadosController = new EmpleadosController();
    Empleados empleado = new Empleados();
    TextView txtNombre, txtApellidos, txtEdad, txtDireccion, txtPuesto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNombre = (TextView) findViewById(R.id.txtnombremain);
        txtApellidos = (TextView) findViewById(R.id.txtapellidomain);
        txtEdad = (TextView) findViewById(R.id.txtedadmain);
        txtDireccion = (TextView) findViewById(R.id.txtdireccionmain);
        txtPuesto = (TextView) findViewById(R.id.txtpuestomain);

        Button btnGuardar = (Button) findViewById(R.id.btnguardar);
        btnGuardar.setOnClickListener(this);

        Button btnListar = (Button) findViewById(R.id.btnver);
        btnListar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.btnguardar:
                if(!txtNombre.getText().toString().isEmpty() && !txtApellidos.getText().toString().isEmpty() && !txtDireccion.getText().toString().isEmpty() &&
                        !txtEdad.getText().toString().isEmpty() && !txtPuesto.getText().toString().isEmpty()) {

                    empleado = new Empleados(txtNombre.getText().toString(),
                            txtApellidos.getText().toString(),
                            Integer.valueOf(txtEdad.getText().toString()),
                            txtDireccion.getText().toString(),
                            txtPuesto.getText().toString());

                    empleadosController.openDataBase(getApplicationContext());
                    empleadosController.crearempleado(empleado);
                    Toast.makeText(getApplicationContext(), "Empleado Guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    mostrarDialogoVacios();
                }
                break;

            case R.id.btnver:
                Intent intent = new Intent(getApplicationContext(), lista.class);
                startActivity(intent);
                break;
        }
    }

    public void update(Observable observable, Object o) {

    }

    private void mostrarDialogoVacios() {
        new AlertDialog.Builder(this)
                .setTitle("Alerta de Vacíos")
                .setMessage("No puede dejar ningún campo vacío")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }

    private void limpiar(){
        txtNombre.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        txtDireccion.setText("");
        txtPuesto.setText("");
    }
}

