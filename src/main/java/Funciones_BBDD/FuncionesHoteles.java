package Funciones_BBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FuncionesHoteles {
    public static ArrayList<String> conseguirNombreHoteles(Connection BD) {
        ArrayList<String> Hoteles = new ArrayList<>();
        try {
            //creo un statement
            Statement statement = BD.createStatement();
            // Seleciono una tabla de la base de datos
            String sqlQuery = "SELECT * FROM hoteles";//puedo poner columnas individuales tambien
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (!resultSet.next()) {
                System.out.println("La tabla hoteles esta vacía");
            } else {
                resultSet.close();
                resultSet = statement.executeQuery(sqlQuery);
                // Procesar los resultados de la consulta
                System.out.println("Datos de la tabla hoteles:");
                while (resultSet.next()) {//este comando pasa de fila hasta que no haya mas filasna
                    String nombre = resultSet.getString("Nombre");
                    Hoteles.add(nombre);
                    System.out.println("  Nombre: " + nombre + " sacado de la base de datos");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Hoteles;
    }
}
