package Funciones_BBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FuncionesEmpleados {
    public static ArrayList<ArrayList<String>> mostrarReservasTodas(Connection BD, String hotel) {
        ArrayList<ArrayList<String>> tabla = new ArrayList<>();
        try {
            Statement statement = BD.createStatement();
            System.out.println("Reservas registradas para el hotel " + hotel + ":");

            //comprobar si esta vacio
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM reservas");
            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
            if (count != 0) {
                //comprobamos las reservas
                String sqlQuery = "SELECT * FROM reservas";
                resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    if (resultSet.getString("Hotel").equals(hotel)) {
                        ArrayList<String> fila = new ArrayList<>();
                        int idR = resultSet.getInt("IDreserva");
                        String IDS = Integer.toString(idR);
                        int idU = resultSet.getInt("IDusuario");
                        String IDUS = Integer.toString(idU);
                        String FechaI = resultSet.getString("FechaInicio");
                        String FechaF = resultSet.getString("FechaFin");
                        String Hotel = resultSet.getString("Hotel");
                        int personas = resultSet.getInt("Personas");
                        String personasS = Integer.toString(personas);
                        fila.add(IDS);
                        fila.add(IDUS);
                        fila.add(FechaI);
                        fila.add(FechaF);
                        fila.add(Hotel);
                        fila.add(personasS);
                        tabla.add(fila);
                        System.out.println("ID: " + idU + "  Fecha de inicio de reserva: " + FechaI + "  Fecha de fin de reserva: " + FechaF + " Hotel: " + Hotel + " Num de personas: " + personas);
                    }
                }
            } else {
                System.out.println("La tabla de reservas de este hotel esta vacia");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tabla;
    }
    public static ArrayList<ArrayList<String>> mostrarReservasTodasGerente(Connection BD, String hotel) {
        ArrayList<ArrayList<String>> tabla = new ArrayList<>();
        try {
            Statement statement = BD.createStatement();
            System.out.println("Reservas registradas para el hotel " + hotel + ":");

            //comprobar si esta vacio
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM reservas");
            int count = 0;
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
            if (count != 0) {
                //comprobamos las reservas
                String sqlQuery = "SELECT * FROM reservas";
                resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()) {
                    if (resultSet.getString("Hotel").equals(hotel)) {
                        ArrayList<String> fila = new ArrayList<>();
                        int idU = resultSet.getInt("IDusuario");
                        String IDUS = Integer.toString(idU);
                        String FechaI = resultSet.getString("FechaInicio");
                        String FechaF = resultSet.getString("FechaFin");
                        int personas = resultSet.getInt("Personas");
                        String personasS = Integer.toString(personas);
                        fila.add(IDUS);
                        fila.add(FechaI);
                        fila.add(FechaF);
                        fila.add(personasS);
                        tabla.add(fila);
                        System.out.println("ID: " + idU + "  Fecha de inicio de reserva: " + FechaI + "  Fecha de fin de reserva: " + FechaF + " Num de personas: " + personas);
                    }
                }
            } else {
                System.out.println("La tabla de reservas de este hotel esta vacia");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tabla;
    }

}