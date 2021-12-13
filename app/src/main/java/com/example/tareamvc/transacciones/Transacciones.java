package com.example.tareamvc.transacciones;

public class Transacciones {
    public static final String tableEmploye = "tbl_empleados";
    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String apellido = "apellido";
    public static final String edad = "edad";
    public static final String direccion = "direccion";
    public static final String puesto = "puesto";

    public static final String creartabla =
            "create table "+tableEmploye+"("+id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+nombre+" VARCHAR(100), "+apellido+" VARCHAR(100)," +
                    edad+" INT, "+direccion+" TEXT, "+puesto+" VARCHAR(50))";
    public static final String EliminaTabla = "DROP TABLE IF EXISTS "+tableEmploye;

    public static final String nameDB = "DB_MVC_PM1";
}
