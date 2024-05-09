package org.ventanas;

import Data.Variables;

import static Data.FuncionesEnlace.*;

public class PerfilUser extends javax.swing.JFrame {
    private Ventana ventanaPrincipal;

    /**
     * Creates new form Perfil
     */
    public PerfilUser(Ventana parent) {
        this.ventanaPrincipal = parent;
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
        cerrarSesionButton = new javax.swing.JButton();
        gestionarButton = new javax.swing.JButton();

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

        cerrarSesionButton.setText("Cerrar sesión");
        cerrarSesionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarSesionButtonActionPerformed(evt);
            }
        });

        gestionarButton.setText("Gestionar Reservas");
        gestionarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(volverButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(salirButton))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(187, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cerrarSesionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(gestionarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(209, 209, 209))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(salirButton)
                                        .addComponent(volverButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                                .addComponent(gestionarButton)
                                .addGap(52, 52, 52)
                                .addComponent(cerrarSesionButton)
                                .addGap(152, 152, 152))
        );

        pack();
    }// </editor-fold>

    private void cerrarSesionButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Variables.logged = false;
        volverButtonActionPerformed(evt);
    }

    private void volverButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.updateVentana();
    }

    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {
        salirPrograma();
    }

    private void gestionarButtonActionPerformed(java.awt.event.ActionEvent evt) {
       /* GestionarReservas gestionarReservas = new GestionarReservas(this);
        gestionarReservas.setVisible(true);
        this.setVisible(false);*/
        // TODO add your handling code here:
    }


    // Variables declaration - do not modify
    private javax.swing.JButton cerrarSesionButton;
    private javax.swing.JButton gestionarButton;
    private javax.swing.JButton salirButton;
    private javax.swing.JButton volverButton;
    // End of variables declaration
}
