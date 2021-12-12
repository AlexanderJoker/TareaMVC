package com.example.tareamvc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.tareamvc.Transacciones.SQLiteConexion;
import com.example.tareamvc.Transacciones.Transacciones;

import java.util.ArrayList;

public class EmpleadosController {
    private SQLiteConexion db;
    private Context context;
    public void openDataBase(Context context){
        db = new SQLiteConexion(context, Transacciones.NameDataBase, null, 1);
    }
    public void createEmpleado(Empleados empleado){
        ContentValues cv = new ContentValues();
        cv.put(Transacciones.nombre, empleado.getNombre());
        cv.put(Transacciones.apellidos, empleado.getApellidos());
        cv.put(Transacciones.edad, empleado.getEdad());
        cv.put(Transacciones.direccion, empleado.getDireccion());
        cv.put(Transacciones.puesto, empleado.getPuesto());
        Log.e("inserted", "inserted");
        db.getWritableDatabase().insert(Transacciones.tablaEmpleados, Transacciones.id, cv);
    }
    public Empleados readEmpleado(int id){
        String [] params = {String.valueOf(id)};
        String [] fields = {
                Transacciones.id,
                Transacciones.nombre,
                Transacciones.apellidos,
                Transacciones.edad,
                Transacciones.direccion,
                Transacciones.puesto};
        String wherecond = Transacciones.id + "=?";
        Empleados empleado = new Empleados();
        try{
            Cursor cursor = db.getWritableDatabase().query(Transacciones.tablaEmpleados, fields, wherecond, params, null, null, null);
            cursor.moveToFirst();
            empleado = new Empleados(cursor.getInt(cursor.getColumnIndex(Transacciones.id)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.nombre)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.apellidos)),
                    cursor.getInt(cursor.getColumnIndex(Transacciones.edad)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.direccion)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.puesto)));
        }catch (Exception ex){
            Log.e("Exception", "Elemento no encontrado");
        }
        return empleado;
    }
    public void updateEmpleado(Empleados empleado){
        String [] params = {String.valueOf(empleado.getId())};
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombre, empleado.getNombre());
        valores.put(Transacciones.apellidos, empleado.getApellidos());
        valores.put(Transacciones.edad, empleado.getEdad());
        valores.put(Transacciones.direccion, empleado.getDireccion());
        valores.put(Transacciones.puesto, empleado.getPuesto());
        db.getWritableDatabase().update(Transacciones.tablaEmpleados, valores, Transacciones.id + "=?", params);
    }
    public void deleteEmpleado(int id){
        String [] params = {String.valueOf(id)}; //Parametro de Busqueda
        db.getWritableDatabase().delete(Transacciones.tablaEmpleados, Transacciones.id + "=?", params);
        Log.d("Exito", "Dato Eliminado");
    }
    public ArrayList<Empleados> listEmpleados(){
        ArrayList<Empleados> empleados = new ArrayList<>();
        Cursor cursor = db.getReadableDatabase().rawQuery("SELECT * FROM empleados",null);
        while (cursor.moveToNext()){
            Empleados empleado = new Empleados(cursor.getInt(cursor.getColumnIndex(Transacciones.id)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.nombre)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.apellidos)),
                    cursor.getInt(cursor.getColumnIndex(Transacciones.edad)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.direccion)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.puesto)));
            empleados.add(empleado);
        }
        return empleados;
    }
}