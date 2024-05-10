package Data;

import Funciones_BBDD.FuncionesHoteles;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;

import static Funciones_BBDD.FuncionesEmpleados.mostrarReservasTodas;
import static Funciones_BBDD.FuncionesEmpleados.mostrarReservasTodasGerente;
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
    public static ArrayList<ArrayList<String>> mostrarReservasDeHotelEnArraylistGerente(String correo, String telef, String contra){
        Connection BD = conectarBD();
        ArrayList<ArrayList<String>> reservas = mostrarReservasTodasGerente(BD,conseguirLugarTrabajo(BD,conseguirID(BD, correo,telef,contra)));
        desconexion(BD);
        return reservas;
    }
    public static ArrayList<ArrayList<String>> mostrarEmpleadosDeHotelEnArraylistGerente(String hotel){
        Connection BD = conectarBD();
        ArrayList<ArrayList<String>> reservas = SacarEmpleados(BD,hotel);
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
    public static boolean HayNumerosEnString(String frase){
        return frase.matches("[1-8]");
    }
    public static boolean HayNumerosEnStringEmpl(String frase){
        return frase.matches("[1-9]+");
    }
    public static boolean TamanoString(String frase){
        return frase.length()==1;
    }
    public static void ModificarReservaDeUsuario(String correo,String telef,String contra,String hotel,String fechaI,String fechaF,int personas){
        Connection BD = conectarBD();
        String hotel2 = hotel.toUpperCase();
        String fechaIA = sacarFechaInicio(BD,correo,telef,contra,hotel2);
        modificarReverva(BD,correo,telef,contra,fechaIA,hotel2,fechaI,fechaF,personas);
        desconexion(BD);
    }
    public static void EliminarReservaDeUsuario(String correo,String telef,String contra,String hotel,String fechaI){
        Connection BD = conectarBD();
        String hotel2 = hotel.toUpperCase();
        eliminarReservas(BD,correo,telef,contra,hotel2,fechaI);
        desconexion(BD);
    }

    public static void EliminarReservaDeUsuarioDesdeGerente(int id){
        Connection BD = conectarBD();
        ArrayList<String> datos = conseguirDatosConID(BD,id);
        String correo,telef,contra;
        correo = datos.get(0);
        telef = datos.get(1);
        contra = datos.get(2);
        String hotel2 = conseguirLugarTrabajoEmpleado(conseguirIDExtra(Variables.usuario,Variables.telefono,Variables.password)).toUpperCase();
        String fechaI = sacarFechaInicio(BD,correo,telef,contra,hotel2);
        eliminarReservas(BD,correo,telef,contra,hotel2,fechaI);
        desconexion(BD);
    }
    public static void ModificarReservaDeUsuarioDesdeGerente(int id,String fechaI,String fechaF,int personas){
        Connection BD = conectarBD();
        ArrayList<String> datos = conseguirDatosConID(BD,id);
        String correo,telef,contra;
        correo = datos.get(0);
        telef = datos.get(1);
        contra = datos.get(2);
        String hotel2 = conseguirLugarTrabajoEmpleado(conseguirIDExtra(Variables.usuario,Variables.telefono,Variables.password)).toUpperCase();
        String fechaIA = sacarFechaInicio(BD,correo,telef,contra,hotel2);
        modificarReverva(BD,correo,telef,contra,fechaIA,hotel2,fechaI,fechaF,personas);
        desconexion(BD);
    }
    public static ArrayList<String> conseguirDatosConIDGerente(int id){
        Connection BD = conectarBD();
        ArrayList<String> datos = conseguirDatosConID(BD,id);
        desconexion(BD);
        return datos;
    }
    public static String conseguirLugarTrabajoEmpleado(int id){
        Connection BD = conectarBD();
        String hotel = conseguirLugarTrabajo(BD,id);
        desconexion(BD);
        return hotel;
    }
    public static int conseguirIDExtra(String correo,String telef,String contra){
        Connection BD = conectarBD();
        int id = conseguirID(BD,correo,telef,contra);
        desconexion(BD);
        return  id;
    }
    public static void annadirEmpleadoGerente(String nombre, String apellido, String correo, String telef, String contra, String hotel){
        Connection BD = conectarBD();
        introducirEmpleadoDirectamente(BD,nombre, apellido, correo, telef, contra, hotel);
        desconexion(BD);
    }
    public static void eliminarEmpleadoGerente(int id, String hotel){
        Connection BD = conectarBD();
        eliminarEmpleadoYusuario(BD,id,hotel);
        desconexion(BD);
    }
}
