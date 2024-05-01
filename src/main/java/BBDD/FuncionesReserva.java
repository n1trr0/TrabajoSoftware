package BBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static BBDD.FuncionesComprobacion.comprobacionFormatoFecha;
import static BBDD.FuncionesGerente.conseguirID;
import static BBDD.FuncionesUsuario.BuscarUsuario;
import static BBDD.FuncionesUsuario.ConseguirNombre;
public class FuncionesReserva {
    public static boolean MostrarReservasdeUsuario(Connection BD, String correo, String telef, String contra){
        boolean compr= false;
        if(BuscarUsuario(BD,correo,contra)) {
            String nombre = ConseguirNombre(BD,correo,telef,contra);
            try {
                Statement statement = BD.createStatement();
                System.out.println("Reservas registradas para el usuario " + nombre +":");
                int id = conseguirID(BD,correo,telef,contra);
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
                        int idUsuario = resultSet.getInt("IDusuario");
                        if (id == idUsuario) {
                            String FechaI = resultSet.getString("FechaInico");
                            String FechaF = resultSet.getString("FechaFin");
                            String Hotel = resultSet.getString("Hotel");
                            int personas = resultSet.getInt("Personas");
                            System.out.println("Reserva el dia " + FechaI + " en el hotel "+Hotel+" hasta el dia "+FechaF+" para "+personas+" personas");
                            compr = true;
                        }
                    }
                }
                if (!compr) {
                    System.out.println("El usuario "+nombre+" no tiene ninguna reserva planificada");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return compr;
    }

    //Comprueba
    public static boolean BuscarReserva(Connection BD,String correo, String telef, String contra,String fechaI,String hotel){
        boolean compr2 = false;
        if(BuscarUsuario(BD,correo,contra) && comprobacionFormatoFecha(fechaI)) {
            try {
                Statement statement = BD.createStatement();

                int id = conseguirID(BD,correo,telef,contra);

                //comprobar si esta vacio
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM reservas");
                int count = 0;
                if (resultSet.next()) {
                    count = resultSet.getInt("count");
                }
                String nombre = ConseguirNombre(BD,correo,telef,contra);
                if (count != 0) {

                    //comprobamos las reservas
                    String sqlQuery = "SELECT * FROM reservas";
                    resultSet = statement.executeQuery(sqlQuery);
                    while (resultSet.next()) {
                        int idUsuario = resultSet.getInt("IDusuario");
                        if (idUsuario == id) {
                            if(resultSet.getString("FechaInicio").equals(fechaI) && resultSet.getString("Hotel").equals(hotel)) {
                                System.out.println("El usuario "+nombre+" tiene una reserva planificada en esta fecha y en este hotel");
                                compr2 = true;
                                break;
                            }
                        }
                    }
                    if (!compr2) {
                        System.out.println("El usuario "+nombre+" no tiene ninguna reserva planificada en esta fecha y en este hotel");
                    }
                }
                if (!compr2) {
                    System.out.println("El usuario "+nombre+" no tiene ninguna reserva planificada en esta fecha y en este hotel");
                }
                return compr2;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return compr2;
    }

    public static void crearReservas(Connection BD,String correo, String telef, String contra,String hotel,String fechaI,String fechaF,int personas){
        if(BuscarUsuario(BD,correo,contra)){
            try{
                int id;
                id = conseguirID(BD,correo,telef,contra);
                Statement statement = BD.createStatement();
                String sqlQuery = "INSERT INTO reservas (IDusuario,FechaInicio,FechaFin,Hotel,Personas) VALUES ('"+id+"','" + fechaI + "','"+fechaF+"','"+ hotel +"','"+personas+"')";
                statement.executeUpdate(sqlQuery);
                System.out.println("Reserva hecha desde "+fechaI+" hasta "+fechaF+" en el hotel "+hotel);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

    public static void eliminarReservas(Connection BD,String correo, String telef, String contra,String hotel,String fechaI){
        if(BuscarUsuario(BD,correo,contra)){
            int id;
            id = conseguirID(BD,correo,telef,contra);
            boolean compr;
            compr = MostrarReservasdeUsuario(BD,correo,telef,contra);
            if(compr){
                try {
                    boolean compr2=false;
                    Scanner scanner = new Scanner(System.in);
                    Statement statement = BD.createStatement();
                    String sqlQuery = "SELECT * FROM reservas";
                    ResultSet resultSet = statement.executeQuery(sqlQuery);
                    while(resultSet.next()){
                        if(resultSet.getInt("IDusuario")==id && resultSet.getString("FechaInicio").equals(fechaI) && resultSet.getString("Hotel").equals(hotel)){
                            compr2=true;
                            String elimBD = "DELETE FROM reservas WHERE IDusuario='"+id+"' AND FechaInicio='"+fechaI+"' AND Hotel='"+hotel+"'";
                            statement.executeUpdate(elimBD);
                            System.out.println("Reserva eliminada");
                            break;
                        }
                    }
                    if(!compr2){
                        System.out.println("No se puede eliminar una reserva que no existe.");
                    }
                }catch (SQLException e){
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void modificarReverva(Connection BD,String correo, String telef, String contra,String fechaI,String hotel,String fechaNI,String fechaNF,int personas){
        if(BuscarReserva(BD,correo,telef,contra,fechaI,hotel)){
            try {
                System.out.println();
                if(comprobacionFormatoFecha(fechaNI) && comprobacionFormatoFecha(fechaNF)) {
                    int id = conseguirID(BD,correo,telef,contra);
                    Statement statement = BD.createStatement();
                    String SQLQuery = "UPDATE reservas SET FechaInicio= '" + fechaNI + "',FechaFin='"+fechaNF+"',Personas='"+personas+"' WHERE IDusuario = '" + id + "' AND Hotel='"+hotel+"'";
                    statement.executeUpdate(SQLQuery);
                    System.out.println("Reserva del hotel "+hotel+" modificada con "+fechaNI+" y con "+fechaNF);
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

}
