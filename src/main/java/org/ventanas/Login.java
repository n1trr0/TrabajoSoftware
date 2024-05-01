package org.ventanas;

import BBDD.FuncionesGerente;
import BBDD.FuncionesUsuario;
import Data.Variables;

import javax.swing.*;
import java.sql.Connection;

import static BASE_DE_DATOS.ConexionPrincipal.conectarBD;
import static BASE_DE_DATOS.ConexionPrincipal.desconexion;
import static BBDD.FuncionesUsuario.*;
import static BBDD.FuncionesComprobacion.*;

public class Login extends javax.swing.JFrame {
    private Ventana ventanaPrincipal;

    /**
     * Creates new form LogIn
     */
    public Login(Ventana parent) {
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

        salirButton = new javax.swing.JToggleButton();
        passwordUsuario = new javax.swing.JPasswordField();
        correo = new javax.swing.JTextField();
        correoText = new javax.swing.JLabel();
        passwordText = new javax.swing.JLabel();
        loginButton = new javax.swing.JToggleButton();
        volverButton = new javax.swing.JToggleButton();
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

        correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correoActionPerformed(evt);
            }
        });

        correoText.setText("Correo:");

        passwordText.setText("Contraseña:");

        loginButton.setText("Iniciar sesión");

        volverButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        volverButton.setText("<");
        volverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverButtonActionPerformed(evt);
            }
        });

        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        errorText.setForeground(new java.awt.Color(255, 0, 0));
        errorText.setText("xd");
        errorText.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(volverButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(salirButton))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(134, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(errorText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(passwordText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(correoText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(27, 27, 27)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(passwordUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                                        .addComponent(correo))))
                                .addGap(107, 107, 107))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(salirButton)
                                        .addComponent(volverButton))
                                .addGap(100, 100, 100)
                                .addComponent(errorText)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(correoText))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordText))
                                .addGap(62, 62, 62)
                                .addComponent(loginButton)
                                .addGap(0, 105, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void correoActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String correoS = correo.getText();
        char[] contraAux = passwordUsuario.getPassword();
        String contraS = new String(contraAux);
        java.util.Arrays.fill(contraAux, ' ');
        if (correoS.isEmpty()) {
            errorText.setText("Rellena el campo del correo para continuar");
            errorText.setVisible(true);

            return;
        }

        if (contraS.isEmpty()) {
            errorText.setText("Rellena el campo de la contraseña para continuar");
            errorText.setVisible(true);

            return;
        }

        if (!comprobacionFormatoCorreo(correoS)) {
            errorText.setText("El correo no tiene un formato valido");
            errorText.setVisible(true);

            return;
        }

        Connection BD = conectarBD();

        if (BuscarUsuario(BD, correoS, contraS)) {
            errorText.setVisible(false);
            JOptionPane.showMessageDialog(null, "Los datos introducidos son correctos", "Información", JOptionPane.INFORMATION_MESSAGE);
            Variables.logged = true;
            Variables.usuario = correoS;
            Variables.password = contraS;
            Variables.nivel = FuncionesGerente.conseguirNivel(BD, correoS, contraS);
            Variables.telefono = FuncionesUsuario.ConseguirTelefono(BD, correoS, contraS);

        } else {
            errorText.setText("El correo o contraseña no son correctos");
            errorText.setVisible(true);
        }

        desconexion(BD);
    }

    private void volverButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.updateVentana();
    }


    // Variables declaration - do not modify
    private javax.swing.JTextField correo;
    private javax.swing.JLabel correoText;
    private javax.swing.JLabel errorText;
    private javax.swing.JToggleButton loginButton;
    private javax.swing.JLabel passwordText;
    private javax.swing.JPasswordField passwordUsuario;
    private javax.swing.JToggleButton salirButton;
    private javax.swing.JToggleButton volverButton;
    // End of variables declaration
}
