package org.ventanas;

import BBDD.FuncionesComprobacion;
import BBDD.FuncionesReserva;
import Data.Variables;

import javax.swing.*;
import java.sql.Connection;

import static BASE_DE_DATOS.ConexionPrincipal.conectarBD;
import static BASE_DE_DATOS.ConexionPrincipal.desconexion;

/**
 *
 * @author Raul
 */
public class ReservarFechas extends javax.swing.JFrame {
    private JFrame ventanaReserva;
    /**
     * Creates new form ReservarFechas
     */
    public ReservarFechas(JFrame parent) {
        this.ventanaReserva = parent;
        initComponents();
        setLocationRelativeTo(parent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        salirButton = new javax.swing.JButton();
        volverButton = new javax.swing.JButton();
        fechaInicioText = new javax.swing.JLabel();
        numPersonasText = new javax.swing.JLabel();
        fechaFinText = new javax.swing.JLabel();
        numPersonas = new javax.swing.JSlider();
        formatoFechaText = new javax.swing.JLabel();
        fechaInicio = new javax.swing.JFormattedTextField();
        fechaFin = new javax.swing.JFormattedTextField();
        reservar = new javax.swing.JButton();
        errorText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        salirButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        salirButton.setText("X");
        salirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirButtonActionPerformed(evt);
            }
        });

        volverButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        volverButton.setText("<");
        volverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverButtonActionPerformed(evt);
            }
        });

        fechaInicioText.setText("Fecha Inicio");

        numPersonasText.setText("Personas");

        fechaFinText.setText("Fecha Fin");

        numPersonas.setMajorTickSpacing(1);
        numPersonas.setMaximum(8);
        numPersonas.setMinimum(1);
        numPersonas.setPaintLabels(true);

        formatoFechaText.setText("Formato fecha DD/MM/YYYY");

        fechaInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        fechaFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        reservar.setText("Reservar");
        reservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservarActionPerformed(evt);
            }
        });

        errorText.setForeground(new java.awt.Color(255, 0, 0));
        errorText.setVisible(false);
        errorText.setText("Raul");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(volverButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(salirButton))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(errorText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(fechaInicioText, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                        .addComponent(numPersonasText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(fechaFinText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(reservar)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(fechaInicio)
                                                                .addComponent(numPersonas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                        .addComponent(formatoFechaText)
                                                                        .addGap(18, 18, 18))
                                                                .addComponent(fechaFin)))))
                                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(salirButton)
                                        .addComponent(volverButton))
                                .addGap(25, 25, 25)
                                .addComponent(errorText)
                                .addGap(34, 34, 34)
                                .addComponent(formatoFechaText)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(fechaInicioText)
                                        .addComponent(fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(fechaFinText)
                                        .addComponent(fechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(numPersonasText)
                                        .addComponent(numPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(66, 66, 66)
                                .addComponent(reservar)
                                .addGap(0, 74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void volverButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        ventanaReserva.setVisible(true);
    }

    private void reservarActionPerformed(java.awt.event.ActionEvent evt) {
        if(!Variables.logged){
            errorText.setText("Necesitar tener un inicio de sesion activo");
            errorText.setVisible(true);
            return;
        }
        if(!FuncionesComprobacion.comprobacionFormatoFecha(fechaInicio.getText())){
            errorText.setText("Fecha de inicio debe de ser >2024");
            errorText.setVisible(true);
            return;
        }
        if(!FuncionesComprobacion.comprobacionFormatoFecha(fechaFin.getText())){
            errorText.setText("Fecha de fin debe de ser >2024");
            errorText.setVisible(true);
            return;
        }
        if(!FuncionesComprobacion.comprobacionValidezFechas(fechaInicio.getText(), fechaFin.getText())){
            errorText.setText("La fecha final no puede ser anterior a la inicial");
            errorText.setVisible(true);
            return;
        }
        Connection BD = (Connection) conectarBD();
        FuncionesReserva.crearReservas(BD, Variables.usuario, Variables.telefono, Variables.password, Variables.hotel, fechaInicio.getText(), fechaFin.getText(), numPersonas.getValue());
        desconexion((java.sql.Connection) BD);
    }
    // Variables declaration - do not modify
    private javax.swing.JFormattedTextField fechaFin;
    private javax.swing.JLabel fechaFinText;
    private javax.swing.JFormattedTextField fechaInicio;
    private javax.swing.JLabel fechaInicioText;
    private javax.swing.JLabel formatoFechaText;
    private javax.swing.JSlider numPersonas;
    private javax.swing.JLabel numPersonasText;
    private javax.swing.JButton salirButton;
    private javax.swing.JButton volverButton;
    private javax.swing.JButton reservar;
    private javax.swing.JLabel errorText;
    // End of variables declaration
}
