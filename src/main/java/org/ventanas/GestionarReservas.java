package org.ventanas;
import Data.Variables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;

import static BASE_DE_DATOS.ConexionPrincipal.conectarBD;
import static BASE_DE_DATOS.ConexionPrincipal.desconexion;
import static BBDD.FuncionesEmpleados.mostrarReservasTodas;
import static BBDD.FuncionesGerente.conseguirID;
import static BBDD.FuncionesGerente.conseguirLugarTrabajo;
public class GestionarReservas extends javax.swing.JFrame {

    private javax.swing.JButton salirButton;
    private javax.swing.JButton volverButton;

    private static PerfilUser ventanaperfiluser;
    public GestionarReservas(PerfilUser parent) {
        this.ventanaperfiluser=parent;
        initComponents();
        setLocationRelativeTo(parent);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
    private void volverButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        ventanaperfiluser.setVisible(true);
    }
    private void initComponents() {

        salirButton = new javax.swing.JButton();
        volverButton = new javax.swing.JButton();

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(volverButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 471, Short.MAX_VALUE)
                                .addComponent(salirButton))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(salirButton)
                                        .addComponent(volverButton))
                                .addGap(0, 398, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReservarFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReservarFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReservarFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservarFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionarReservas(ventanaperfiluser).setVisible(true);
            }
        });
    }

}
