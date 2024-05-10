package Funciones_BBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Funciones_BBDD.FuncionesComprobacion.comprobacionFormatoCorreo;
import static Funciones_BBDD.FuncionesUsuario.*;

public class FuncionesGerente {
    public static void ConsultarUsuarios(Connection BD) {
        // Crear una declaración SQL,tiene que estar dentro de un try catch
        try {
            //creo un statement
            Statement statement = BD.createStatement();
            // Seleciono una tabla de la base de datos
            String sqlQuery = "SELECT * FROM usuarios";//puedo poner columnas individuales tambien
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (!resultSet.next()) {
                System.out.println("La tabla usuarios esta vacía");
            } else {
                resultSet.close();
                resultSet = statement.executeQuery(sqlQuery);
                // Procesar los resultados de la consulta
                System.out.println("Datos de la tabla usuarios:");
                while (resultSet.next()) {//este comando pasa de fila hasta que no haya mas filas
                    int id = resultSet.getInt("ID");//se pone el nombre de la columna
                    String nombre = resultSet.getString("Nombre");
                    String apellido = resultSet.getString("Apellido");
                    String telef = resultSet.getString("Telefono");
                    String correo = resultSet.getString("Correo");
                    System.out.println("ID: " + id + "  Nombre: " + nombre + "  Apellido: " + apellido + "  Telefono: " + telef + "  Correo: " + correo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cambiarNivel(Connection BD, String correo, String telef, String contra, int nivel) {
        try {
            int id = conseguirID(BD, correo, telef, contra);
            Statement statement = BD.createStatement();
            String SQLQuery = "UPDATE Usuarios SET Nivel = '" + nivel + "' WHERE ID = '" + id + "';";
            statement.executeUpdate(SQLQuery);
            System.out.println("Nivel cambiado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cambiarNivelConId(Connection BD, int id, int nivel) {
        try {
            Statement statement = BD.createStatement();
            String SQLQuery = "UPDATE Usuarios SET Nivel = '" + nivel + "' WHERE ID = '" + id + "';";
            statement.executeUpdate(SQLQuery);
            System.out.println("Nivel cambiado");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int conseguirNivel(Connection BD, String correo, String contra) {
        int nivel = 0;
        try {
            Statement statement = BD.createStatement();
            String sqlQuery = "SELECT * FROM usuarios";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getString("Correo").equals(correo) && resultSet.getString("Contraseña").equals(contra)) {
                    nivel = resultSet.getInt("Nivel");
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nivel;
    }

    public static int conseguirID(Connection BD, String correo, String telef, String contra) {
        int id = 0;
        try {
            Statement statement = BD.createStatement();
            String sqlQuery = "SELECT * FROM usuarios";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getString("Correo").equals(correo) && resultSet.getString("Contraseña").equals(contra) && resultSet.getString("Telefono").equals(telef)) {
                    id = resultSet.getInt("ID");
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public static String conseguirLugarTrabajo(Connection BD, int id) {
        String hotel = "";
        try {
            Statement statement = BD.createStatement();
            String sqlQuery = "SELECT * FROM empleados";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getInt("ID") == id) {
                    hotel = resultSet.getString("LugarTrabajo");
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotel;
    }

    public static void introducirEmpleadodeUsuarios(Connection BD, String correo, String telef, String contra, String hotel) {
        if (BuscarUsuario(BD, correo, contra)) {
            int id = conseguirID(BD, correo, telef, contra);
            if (!buscarEmpleado(BD, id, hotel)) {
                try {
                    Statement statement = BD.createStatement();
                    String SQLQuery = "INSERT INTO empleados (ID,LugarTrabajo,EsGerente) Values('" + id + "','" + hotel + "','0')";
                    statement.executeUpdate(SQLQuery);
                    System.out.println("Empleado añadido en el hotel " + hotel);
                    cambiarNivel(BD, correo, telef, contra, 1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void introducirEmpleadoDirectamente(Connection BD, String nombre, String apellido, String correo, String telef, String contra, String hotel) {
        RegistrarUsuario(BD, nombre, apellido, correo, telef, contra);
        introducirEmpleadodeUsuarios(BD, correo, telef, contra, hotel);
    }

    public static boolean buscarEmpleado(Connection BD, int id, String hotel) {
        try {
            boolean compr = false;
            Statement statement = BD.createStatement();
            String sqlQuery = "SELECT * FROM empleados";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (!resultSet.next()) {
                System.out.println("La tabla empleados esta vacía");
            } else {
                resultSet.close();
                resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    if (resultSet.getInt("ID") == id && resultSet.getString("LugarTrabajo").equals(hotel)) {
                        String nombre = ConseguirNombreConId(BD, id);
                        System.out.println("Se ha encontrado un empleado que trabaja en el hotel " + hotel + " cuyo nombre es " + nombre);
                        compr = true;
                        break;
                    }
                }
                if (!compr) {
                    System.out.println("No se ha encontrado ningun empleado que contenga los datos introducidos.");
                }
            }
            return compr;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void consultarEmpleados(Connection BD, String hotel) {
        try {
            Statement statement = BD.createStatement();
            String sqlQuery = "SELECT * FROM empleados";//puedo poner columnas individuales tambien
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (!resultSet.next()) {
                System.out.println("La tabla empleados esta vacía");
            } else {
                resultSet.close();
                resultSet = statement.executeQuery(sqlQuery);
                System.out.println("Datos de la tabla empleados:");
                while (resultSet.next()) {//este comando pasa de fila hasta que no haya mas filas
                    if (resultSet.getString("LugarTrabajo").equals(hotel)) {
                        int id = resultSet.getInt("ID");//se pone el nombre de la columna
                        String hotel2 = resultSet.getString("LugarTrabajo");
                        String gerente;
                        Boolean gerenteA = resultSet.getBoolean("EsGerente");
                        if (gerenteA) {
                            gerente = "Si";
                        } else {
                            gerente = "No";
                        }
                        System.out.println("ID: " + id + "  Hotel donde trabaja: " + hotel2 + "  Es Gerente de hotel: " + gerente);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<ArrayList<String>> SacarEmpleados(Connection BD, String hotel){
        ArrayList<ArrayList<String>> tabla = new ArrayList<>();
        try {
            Statement statement = BD.createStatement();
            System.out.println("Empleados del hotel " + hotel + ":");

            //comprobar si esta vacio
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM empleados");
            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
            if (count != 0) {
                //comprobamos las reservas
                String sqlQuery = "SELECT * FROM empleados";
                resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    if (resultSet.getString("LugarTrabajo").equals(hotel)) {
                        ArrayList<String> fila = new ArrayList<>();
                        int idU = resultSet.getInt("ID");
                        String IDUS = Integer.toString(idU);
                        String Hotel = resultSet.getString("LugarTrabajo");
                        fila.add(IDUS);
                        fila.add(Hotel);
                        tabla.add(fila);
                    }
                }
            } else {
                System.out.println("La tabla de empleados de este hotel esta vacia");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tabla;
    }

    public static void eliminarEmpleado(Connection BD, int id, String hotel) {
        if (buscarEmpleado(BD, id, hotel)) {
            String nombre = ConseguirNombreConId(BD, id);
            try {
                Statement statement = BD.createStatement();
                cambiarNivelConId(BD, id, 0);
                String elimBD = "DELETE FROM empleados WHERE ID='" + id + "' AND LugarTrabajo='" + hotel + "'";//los strings se ponen entre comillas simples
                System.out.println("El empleado " + nombre + " ha sido despedido del hotel " + hotel);
                statement.executeUpdate(elimBD);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static void eliminarEmpleadoYusuario(Connection BD, int id, String hotel) {
        eliminarEmpleado(BD, id, hotel);
        EliminarUsuarioConId(BD, id);
    }

    public static ArrayList<String> conseguirDatosConID(Connection BD, int id){
        ArrayList<String> datos = new ArrayList<>();
        try {
            boolean compr = false;
            Statement statement = BD.createStatement();
            String sqlQuery = "SELECT * FROM usuarios";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (!resultSet.next()) {
                System.out.println("La tabla usuarios esta vacía");
            } else {
                resultSet.close();
                resultSet = statement.executeQuery(sqlQuery);
                    while (resultSet.next()) {
                        if (resultSet.getInt("ID")==id) {
                            String correo = resultSet.getString("Correo");
                            String telef = resultSet.getString("Telefono");
                            String contra = resultSet.getString("Contraseña");
                            datos.add(correo);
                            datos.add(telef);
                            datos.add(contra);
                            compr = true;
                            break;
                        }
                    }
                    if (!compr) {
                        System.out.println("No se ha encontrado ningun usuario que contenga los datos introducidos.");
                    }
            }
            return datos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
