package Conexion_BBDD;

import com.amadeus.exceptions.ResponseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static Conexion_BBDD.ConexionPrincipal.conectarBD;
import static Conexion_BBDD.ConexionPrincipal.desconexion;

public class LeerJSON_API {

    private static String nombreHotel;
    private static String idHotel;
    private static String ciudad;

    public static void leerjson() {
        try {

            JSONTokener parser = new JSONTokener(new FileInputStream("hotels.json"));
            JSONArray jsonList = new JSONArray(parser);
            for (int i = 0; i < jsonList.length(); i++) {
                JSONObject object = (JSONObject) jsonList.get(i);
                nombreHotel = object.getString("name");
                idHotel = object.getString("hotelId");
                ciudad = object.getString("iataCode");
                nombreHotel = nombreHotel.replace("AC BY MARRIOTT ", "");
                intsertBD(conectarBD());
                desconexion(conectarBD());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void intsertBD(Connection BD) {
        try {
            Statement statement = BD.createStatement();
            String sqlquery = "INSERT INTO hoteles (idHotel, Nombre, Ciudad) " +
                    "SELECT * FROM (SELECT '" + idHotel + "', '" + nombreHotel + "', '" + ciudad + "') AS tmp " +
                    "WHERE NOT EXISTS (" +
                    "    SELECT idHotel FROM hoteles WHERE idHotel = '" + idHotel + "'" +
                    ") LIMIT 1;";
            //String sqlquery="INSERT INTO hoteles(idHotel,Nombre,Ciudad)VALUES('"+idHotel+"','"+nombreHotel+"','"+ciudad+"')";
            statement.executeUpdate(sqlquery);
            System.out.println("Datos insertados");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws ResponseException {
        leerjson();
    }


}
