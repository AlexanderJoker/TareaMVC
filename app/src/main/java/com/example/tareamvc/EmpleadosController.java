package com.example.tareamvc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.tareamvc.Empleados;
import com.example.tareamvc.transacciones.SQLiteConexion;
import com.example.tareamvc.transacciones.Transacciones;

import java.util.ArrayList;
import java.util.Observable;

public class EmpleadosController extends Observable {

    private SQLiteConexion db_emple;
    private Context context;

    public void openDataBase(Context context){
        db_emple = new SQLiteConexion(context, Transacciones.nameDB, null, 1);
    }

    public void crearempleado(Empleados empleado){
        ContentValues contenido = new ContentValues();

        contenido.put(Transacciones.nombre, empleado.getNombre());
        contenido.put(Transacciones.apellido, empleado.getApellidos());
        contenido.put(Transacciones.edad, empleado.getEdad());
        contenido.put(Transacciones.direccion, empleado.getDireccion());
        contenido.put(Transacciones.puesto, empleado.getPuesto());

        db_emple.getWritableDatabase().insert(Transacciones.tableEmploye, Transacciones.id, contenido);
    }

    public Empleados leerempleado(int id){
        String [] parametros = {String.valueOf(id)};
        String [] datos = {
                Transacciones.id,
                Transacciones.nombre,
                Transacciones.apellido,
                Transacciones.edad,
                Transacciones.direccion,
                Transacciones.puesto};

        String parm2 = Transacciones.id + "=?";

        Empleados employe = new Empleados();
        try{
            Cursor cursor = db_emple.getWritableDatabase().query(Transacciones.tableEmploye, datos, parm2, parametros, null, null, null);
            cursor.moveToFirst();

            employe = new Empleados(cursor.getInt(cursor.getColumnIndex(Transacciones.id)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.nombre)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.apellido)),
                    cursor.getInt(cursor.getColumnIndex(Transacciones.edad)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.direccion)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.puesto)));
        }catch (Exception ex){
            ex.printStackTrace();}
        return employe;
    }

    public void actualizaemploye(Empleados empleado){
        String [] paremtros = {String.valueOf(empleado.getId())};

        ContentValues datos = new ContentValues();
        datos.put(Transacciones.nombre, empleado.getNombre());
        datos.put(Transacciones.apellido, empleado.getApellidos());
        datos.put(Transacciones.edad, empleado.getEdad());
        datos.put(Transacciones.direccion, empleado.getDireccion());
        datos.put(Transacciones.puesto, empleado.getPuesto());

        db_emple.getWritableDatabase().update(Transacciones.tableEmploye, datos, Transacciones.id + "=?", paremtros);
    }

    public void delemploye(int id){
        String [] parametros = {String.valueOf(id)};
        db_emple.getWritableDatabase().delete(Transacciones.tableEmploye, Transacciones.id + "=?", parametros);
    }

    public ArrayList<Empleados> lista(){
        ArrayList<Empleados> emple = new ArrayList<>();
        Cursor cursor = db_emple.getReadableDatabase().rawQuery("select * from tbl_empleados",null);

        while (cursor.moveToNext()){
            Empleados empleado = new Empleados(cursor.getInt(cursor.getColumnIndex(Transacciones.id)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.nombre)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.apellido)),
                    cursor.getInt(cursor.getColumnIndex(Transacciones.edad)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.direccion)),
                    cursor.getString(cursor.getColumnIndex(Transacciones.puesto)));
            emple.add(empleado);
        }
        return emple;
    }

}
