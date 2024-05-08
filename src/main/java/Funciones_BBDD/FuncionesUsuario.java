package Funciones_BBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static Funciones_BBDD.FuncionesComprobacion.*;

public class FuncionesUsuario {
    public static boolean BuscarUsuario(Connection BD, String correo, String contra) {
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
                if (comprobacionFormatoCorreo(correo)) {
                    while (resultSet.next()) {
                        if (resultSet.getString("Correo").equals(correo) && resultSet.getString("Contraseña").equals(contra)) {
                            String nombre = resultSet.getString("Nombre");
                            System.out.println("Se ha encontrado un usuario con correo " + correo + " cuyo nombre es " + nombre);
                            compr = true;
                            break;
                        }
                    }
                    if (!compr) {
                        System.out.println("No se ha encontrado ningun usuario que contenga los datos introducidos.");
                    }
                }
            }
            return compr;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean BuscarUsuarioConId(Connection BD, int id) {
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
                    if (resultSet.getInt("ID") == id) {
                        String nombre = resultSet.getString("Nombre");
                        System.out.println("Se ha encontrado un usuario con id " + id + " cuyo nombre es " + nombre);
                        compr = true;
                        break;
                    }
                }
                if (!compr) {
                    System.out.println("No se ha encontrado ningun usuario que contenga los datos introducidos.");
                }
            }
            return compr;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void EliminarUsuario(Connection BD, String correo, String telef, String contra) {
        try {
            if (BuscarUsuario(BD, correo, contra)) {
                Statement statement = BD.createStatement();
                String nombre = ConseguirNombre(BD, correo, telef, contra);
                String elimBD = "DELETE FROM usuarios WHERE Correo='" + correo + "' AND Contraseña='" + contra + "'";//los strings se ponen entre comillas simples
                System.out.println("El usuario " + nombre + " y con correo " + correo + " ha sido eliminado");
                statement.executeUpdate(elimBD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void EliminarUsuarioConId(Connection BD, int id) {
        try {
            if (BuscarUsuarioConId(BD, id)) {
                Statement statement = BD.createStatement();
                String nombre = ConseguirNombreConId(BD, id);
                String elimBD = "DELETE FROM usuarios WHERE ID='" + id + "'";//los strings se ponen entre comillas simples
                System.out.println("El usuario " + nombre + " y con ID " + id + " ha sido eliminado");
                statement.executeUpdate(elimBD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void RegistrarUsuario(Connection BD, String nombre, String apellido, String correo, String telef, String contra) {
        try {
            if (comprobacionFormatoCorreo(correo) && comprobacionFormatoTelef(telef)) {
                if (comprobacionDisponibilidadCorreo(BD, correo) && comprobacionDisponibilidadTelefono(BD, telef)) {
                    Statement statement = BD.createStatement();
                    String IntroBD = "INSERT INTO usuarios (Nombre,Apellido,Correo,Telefono,Nivel,Contraseña)Values('" + nombre + "','" + apellido + "','" + correo + "','" + telef + "','0','" + contra + "')"; //Insert para meter un valor, primero seleciono las columnas, y luego le pongo los valores, entre ''
                    System.out.println("El usuario " + nombre + " con correo " + correo + " ha sido añadido");//Tambien puedo poner strings como Values('" + nombre +"',.....
                    statement.executeUpdate(IntroBD);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String ConseguirNombre(Connection BD, String correo, String telef, String contra) {
        String nombre = "";
        try {
            Statement statement = BD.createStatement();
            String sqlQuery = "SELECT * FROM usuarios";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getString("Correo").equals(correo) && resultSet.getString("Contraseña").equals(contra) && resultSet.getString("Telefono").equals(telef)) {
                    nombre = resultSet.getString("Nombre");
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nombre;
    }

    public static String ConseguirNombreConId(Connection BD, int id) {
        String nombre = "";
        try {
            Statement statement = BD.createStatement();
            String sqlQuery = "SELECT * FROM usuarios";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getInt("ID") == id) {
                    nombre = resultSet.getString("Nombre");
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nombre;
    }

    public static String ConseguirTelefono(Connection BD, String correo, String contra) {
        String telefono = "";
        try {
            Statement statement = BD.createStatement();
            String sqlQuery = "SELECT * FROM usuarios";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getString("Correo").equals(correo) && resultSet.getString("Contraseña").equals(contra)) {
                    telefono = resultSet.getString("Telefono");
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return telefono;
    }
}
