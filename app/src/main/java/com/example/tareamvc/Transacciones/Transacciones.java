package com.example.tareamvc.Transacciones;

public class Transacciones {
    public static final String tablaEmpleados = "empleados";
    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String direccion = "direccion";
    public static final String puesto = "puesto";
    public static final String CreateTableEmpleados =
            "CREATE TABLE empleados(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR(100), apellidos VARCHAR(100)," +
                    "edad INT, direccion TEXT, puesto VARCHAR(50))";
    public static final String DropeTableEmpleados =
            "DROPE TABLE IF EXISTS empleados";
    /* Base de Datos */
    public static final String NameDataBase = "DBCurso";
}
