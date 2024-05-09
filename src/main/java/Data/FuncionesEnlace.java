package Data;

import Funciones_BBDD.FuncionesHoteles;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;

import static Funciones_BBDD.FuncionesEmpleados.mostrarReservasTodas;
import static Funciones_BBDD.FuncionesReserva.*;
import static Conexion_BBDD.ConexionPrincipal.*;
import static Funciones_BBDD.FuncionesComprobacion.*;
import static Funciones_BBDD.FuncionesUsuario.*;
import static Funciones_BBDD.FuncionesGerente.*;
/**
 * @author Pablo
 */
public class FuncionesEnlace {

    public static void salirPrograma() {
        System.exit(0);
    }

    public static boolean saberSiUsuarioTieneReservas() {
        Connection BD = conectarBD();
        boolean compr = TieneReservaEnHotel(BD, Variables.usuario, Variables.telefono, Variables.password, Variables.hotel);
        desconexion(BD);
        return compr;
    }

    public static void creacionDeReservaDeUsuario(JFormattedTextField fechaI, JFormattedTextField fechaF, JSlider personas) {
        Connection BD = conectarBD();
        crearReservas(BD, Variables.usuario, Variables.telefono, Variables.password, Variables.hotel, fechaI.getText(), fechaF.getText(), personas.getValue());
        desconexion(BD);
    }

    public static ArrayList<String> conseguirHotelesDeBBDD() {
        ArrayList<String> Hoteles;
        Connection BD = conectarBD();
        Hoteles = FuncionesHoteles.conseguirNombreHoteles(BD);
        desconexion(BD);
        return Hoteles;
    }

    public static boolean comprobacionDisponibilidadDeCorreoUsuario(String correo) {
        Connection BD = conectarBD();
        boolean compr = comprobacionDisponibilidadCorreo(BD, correo);
        desconexion(BD);
        return compr;
    }

    public static boolean comprobacionDisponibilidadDeTelefonoUsuario(String telefono) {
        Connection BD = conectarBD();
        boolean compr = comprobacionDisponibilidadCorreo(BD, telefono);
        desconexion(BD);
        return compr;
    }

    public static void registrarUnUsuario(String nombre, String apellido, String correo, String telefono, String contra) {
        Connection BD = conectarBD();
        RegistrarUsuario(BD, nombre, apellido, correo, telefono, contra);
        desconexion(BD);
    }

    public static boolean buscarUnUsuario(String correo, String contra) {
        Connection BD = conectarBD();
        boolean compr = BuscarUsuario(BD, correo, contra);
        desconexion(BD);
        return compr;
    }

    public static int conseguirUnNivelUsuario(String correo, String contra) {
        Connection BD = conectarBD();
        int nivel = conseguirNivel(BD, correo, contra);
        desconexion(BD);
        return nivel;
    }

    public static String conseguirUnTelefonoUsuario(String correo, String contra) {
        Connection BD = conectarBD();
        String telefono = ConseguirTelefono(BD, correo, contra);
        desconexion(BD);
        return telefono;
    }

    public static ArrayList<ArrayList<String>> mostrarReservasDeUsuarioEnArraylist(String correo, String telef, String contra) {
        Connection BD = conectarBD();
        ArrayList<ArrayList<String>> reservas = MostrarReservasdeUsuarioArrayList(BD, correo, telef, contra);
        desconexion(BD);
        return reservas;
    }
    public static ArrayList<ArrayList<String>> mostrarReservasDeHotelEnArraylist(String correo, String telef, String contra){
        Connection BD = conectarBD();
        ArrayList<ArrayList<String>> reservas = mostrarReservasTodas(BD,conseguirLugarTrabajo(BD,conseguirID(BD, correo,telef,contra)));
        desconexion(BD);
        return reservas;
    }
    public static boolean UsuarioTieneReservaEnUnHotel(String correo, String telef, String contra, String hotel){
        Connection BD = conectarBD();
        String hotel2 = hotel.toUpperCase();
        boolean compr = TieneReservaEnHotel(BD,correo,telef,contra,hotel2);
        desconexion(BD);
        return compr;
    }
}
