package Funciones_BBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Funciones_BBDD.FuncionesGerente.conseguirID;
import static Funciones_BBDD.FuncionesUsuario.BuscarUsuario;


public class FuncionesComprobacion {
    public static boolean comprobacionFormatoCorreo(String correo) {
        boolean compr1 = false;
        boolean compr2 = false;
        int iAnt = 0;
        if (!correo.isEmpty()) {
            for (int i = 0; i < correo.length(); i++) {
                if (correo.charAt(i) == '@' && i + 1 < correo.length()) {
                    if (!correo.startsWith("@") && correo.charAt(i - 1) != ' ') {
                        compr1 = true;
                        iAnt = i;
                    }
                    break;
                }
            }
            if (compr1) {
                if (correo.charAt(iAnt + 1) != '.') {
                    for (int i = iAnt; i < correo.length(); i++) {
                        if (correo.charAt(i) == '.' && i + 1 < correo.length()) {
                            if (correo.charAt(i + 1) != ' ') {
                                compr2 = true;
                            }
                            break;
                        }
                    }
                }
            }
            if (!compr2) {
                System.out.println("El correo no tiene un formato correcto");
            }
        }
        return compr2;
    }

    public static boolean comprobacionFormatoTelef(String telef) {
        if (telef.length() >= 9 && telef.length() <= 15) {
            return true;
        } else {
            System.out.println("El telefono no tiene un formato correcto");
            return false;
        }
    }

    public static boolean comprobacionFormatoFecha(String fecha) {
        if (fecha.length() > 10) {
            return false; // La fecha no tiene el formato esperado
        }

        String fechaC = "";
        char L;
        for (int i = 0; i < 4; i++) {
            L = fecha.charAt(6 + i);
            fechaC = fechaC + L;
        }
        int fechaCC = Integer.parseInt(fechaC);
        return fechaCC >= 2024;
    }

    public static boolean comprobacionDisponibilidadCorreo(Connection BD, String correo) {
        boolean compr = true;
        try {
            Statement statement = BD.createStatement();
            String sqlQuery = "SELECT Correo FROM usuarios";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getString("Correo").equals(correo)) {
                    System.out.println("El correo " + correo + " no esta disponible");
                    compr = false;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return compr;
    }

    public static boolean comprobacionDisponibilidadTelefono(Connection BD, String telef) {
        boolean compr = true;
        try {
            Statement statement = BD.createStatement();
            String sqlQuery = "SELECT Telefono FROM usuarios";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getString("Telefono").equals(telef)) {
                    System.out.println("El telefono " + telef + " no esta disponible");
                    compr = false;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return compr;
    }


    public static boolean comprobacionValidezFechas(String fechaI, String fechaF) {

        // Crear formateador para el formato "DD/MM/AAAA"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Convertir las cadenas de fecha en objetos LocalDate
        LocalDate dateI = LocalDate.parse(fechaI, formatter);
        LocalDate dateF = LocalDate.parse(fechaF, formatter);

        if (dateI.compareTo(dateF) < 0) {
            return false;
        } else {
            return true;
        }
    }
}
