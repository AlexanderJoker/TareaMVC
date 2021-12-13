package com.example.tareamvc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

public class editar extends AppCompatActivity implements Observer, View.OnClickListener{

        String idEmpleado;
        EmpleadosController empleadosController= new EmpleadosController();
        Empleados empleado;
        TextView txtNombre, txtApellidos, txtEdad, txtDireccion, txtPuesto;
        Button btnmod,btndel,btnexit;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        txtNombre = (TextView) findViewById(R.id.txtnom2);
        txtApellidos = (TextView) findViewById(R.id.txtape2);
        txtEdad = (TextView) findViewById(R.id.txtedad);
        txtDireccion = (TextView) findViewById(R.id.txtdir2);
        txtPuesto = (TextView) findViewById(R.id.txtcargo2);

        Intent intent = getIntent();
        idEmpleado = intent.getStringExtra("id");

        empleadosController.openDataBase(getApplicationContext());
        empleado = empleadosController.leerempleado(Integer.valueOf(idEmpleado));

        txtNombre.setText(empleado.getNombre());
        txtApellidos.setText(empleado.getApellidos());
        txtEdad.setText(String.valueOf(empleado.getEdad()));
        txtDireccion.setText(empleado.getDireccion());
        txtPuesto.setText(empleado.getPuesto());

        btnmod = (Button) findViewById(R.id.btmodi);
        btnmod.setOnClickListener(this);

        btndel = (Button) findViewById(R.id.btndell);
        btndel.setOnClickListener(this);

        btnexit = (Button) findViewById(R.id.btnsalir);
        btnexit.setOnClickListener(this);

        }

@Override
public void onClick(View v) {
        switch(v.getId()){

        case R.id.btmodi:
        if(!txtNombre.getText().toString().isEmpty() && !txtApellidos.getText().toString().isEmpty() && !txtDireccion.getText().toString().isEmpty() &&
        !txtEdad.getText().toString().isEmpty() && !txtPuesto.getText().toString().isEmpty()) {

        empleado = new Empleados(Integer.valueOf(idEmpleado),
        txtNombre.getText().toString(),
        txtApellidos.getText().toString(),
        Integer.valueOf(txtEdad.getText().toString()),
        txtDireccion.getText().toString(),
        txtPuesto.getText().toString());

        empleadosController.openDataBase(getApplicationContext());
        empleadosController.actualizaemploye(empleado);
        Toast.makeText(getApplicationContext(), "Empleado Modificado", Toast.LENGTH_LONG).show();
        } else {
        dialog_vacio();
        }
        break;

        case R.id.btndell:
        if(Integer.valueOf(idEmpleado) != 0) {
        empleadosController.openDataBase(getApplicationContext());
        empleadosController.delemploye(Integer.valueOf(idEmpleado));
        Toast.makeText(getApplicationContext(), "Empleado Eliminado!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), lista.class);
        startActivity(intent);
        } else {
        dialog_vacio();
        }
        break;

        case R.id.btnsalir:
        Intent intent = new Intent(getApplicationContext(),lista.class);
        startActivity(intent);
        break;
        }
        }

@Override
public void update(Observable observable, Object o) {}

private void dialog_vacio() {
        new AlertDialog.Builder(this)
        .setTitle("Campos Vacios")
        .setMessage("Complete todos los Campos!")
        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        }
        }).show();
        }

        }