package BBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FuncionesEmpleados {
    public static void mostrarReservasTodas(Connection BD, String hotel){
        try {
            Statement statement = BD.createStatement();
            System.out.println("Reservas registradas para el hotel " + hotel +":");

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
                    if(resultSet.getString("Hotel").equals(hotel)) {
                        int id = resultSet.getInt("ID");
                        String FechaI = resultSet.getString("FechaInicio");
                        String FechaF = resultSet.getString("FechaFin");
                        String Hotel = resultSet.getString("Hotel");
                        int personas = resultSet.getInt("Personas");
                        System.out.println("ID: " + id + "  Fecha de inicio de reserva: " + FechaI + "  Fecha de fin de reserva: "+FechaF+" Hotel: " + Hotel+" Num de personas: "+personas);
                    }
                }
            }
            else {
                System.out.println("La tabla reservas esta vacia");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
