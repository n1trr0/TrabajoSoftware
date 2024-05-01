package org.ventanas;

import org.ventanas.Ventana;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

import static BASE_DE_DATOS.ConexionPrincipal.conectarBD;
import static BBDD.FuncionesEmpleados.mostrarReservasTodas;

public class PerfilTrabajdor extends javax.swing.JFrame {

    private static Ventana ventanaPrincipal;
    private javax.swing.JButton salirButton;
    private javax.swing.JButton volverButton;
    private javax.swing.JTable tabla;

    public PerfilTrabajdor(Ventana parent) {
        this.ventanaPrincipal = parent;
        initComponents();
        setLocationRelativeTo(parent);
    }

    public PerfilTrabajdor() {
        initComponents();
    }

    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        salirButton = new javax.swing.JButton();
        volverButton = new javax.swing.JButton();
        tabla = new javax.swing.JTable(); // Aquí inicializamos la variable de instancia tabla
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        salirButton.setFont(new java.awt.Font("Segoe UI", 1, 14));
        salirButton.setText("X");
        salirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirButtonActionPerformed(evt);
            }
        });

        volverButton.setFont(new java.awt.Font("Segoe UI", 1, 14));
        volverButton.setText("<");
        volverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverButtonActionPerformed(evt);
            }
        });

        ArrayList<ArrayList<String>> datos = mostrarReservasTodas(conectarBD(),"Ambassador");
        String[] columnas = {"id", "FechaInicio", "FechaFin", "Hotel", "Personas"};
        DefaultTableModel tableModel = new DefaultTableModel(devolverarray(datos), columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        tabla.setModel(tableModel); // Aquí asignamos el modelo de tabla a la tabla

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(volverButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 471, Short.MAX_VALUE)
                                .addComponent(salirButton))
                        .addComponent(tabla, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(salirButton)
                                        .addComponent(volverButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tabla, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PerfilTrabajdor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PerfilTrabajdor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PerfilTrabajdor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PerfilTrabajdor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ventanaPrincipal = new Ventana();
                new PerfilTrabajdor().setVisible(true);
            }
        });
    }
    private Object[][]devolverarray(ArrayList<ArrayList<String>>hoteles){
        Object[][] arrayBidimensional = new Object[hoteles.size() + 1][]; // Incrementa en 1 para incluir el nuevo dato

        Object[] nuevoDato = {"ID", "FECHA INICIO", "FECHA DE FIN", "HOTEL", "PERSONAS"};
        arrayBidimensional[0] = nuevoDato;

        for (int i = 0; i < hoteles.size(); i++) { // Itera solo hasta hoteles.size()
            ArrayList<String> lista = hoteles.get(i);
            arrayBidimensional[i + 1] = lista.toArray(new Object[0]); // Incrementa el índice en 1 para dejar espacio para el nuevo dato
        }
        return arrayBidimensional;
    }
    private void volverButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.updateVentana();
    }
}
