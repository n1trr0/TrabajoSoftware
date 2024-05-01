package org.ventanas;

import BBDD.FuncionesHoteles;
import Data.Variables;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;

import static BASE_DE_DATOS.ConexionPrincipal.conectarBD;
import static BASE_DE_DATOS.ConexionPrincipal.desconexion;

/**
 *
 * @author Raul
 */
public class Reservar extends javax.swing.JFrame {
    private String selectedHotel;
    private ArrayList<JRadioButton> buttons;
    private ArrayList<String> listaHoteles;
    private JFrame ventanaPrincipal;
    /**
     * Creates new form Reservar
     */
    public Reservar(JFrame parent) {
        this.ventanaPrincipal = parent;
        initComponents();
        setLocationRelativeTo(parent);
        updateHotelButtons();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        hotelSelection = new javax.swing.ButtonGroup();
        hotel1 = new javax.swing.JRadioButton();
        hotel2 = new javax.swing.JRadioButton();
        hotel3 = new javax.swing.JRadioButton();
        hotel4 = new javax.swing.JRadioButton();
        hotel5 = new javax.swing.JRadioButton();
        hotel6 = new javax.swing.JRadioButton();
        hotel7 = new javax.swing.JRadioButton();
        hotel8 = new javax.swing.JRadioButton();
        hotel9 = new javax.swing.JRadioButton();
        hotel10 = new javax.swing.JRadioButton();
        salirButton = new javax.swing.JButton();
        volverButton = new javax.swing.JButton();
        continueButton = new javax.swing.JButton();
        errorText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        hotelSelection.add(hotel1);

        hotelSelection.add(hotel2);

        hotelSelection.add(hotel3);

        hotelSelection.add(hotel4);

        hotelSelection.add(hotel5);

        hotelSelection.add(hotel6);

        hotelSelection.add(hotel7);

        hotelSelection.add(hotel8);

        hotelSelection.add(hotel9);

        hotelSelection.add(hotel10);

        listaHoteles = new ArrayList<>();
        buttons = new ArrayList<>();
        buttons.add(hotel1);
        buttons.add(hotel2);
        buttons.add(hotel3);
        buttons.add(hotel4);
        buttons.add(hotel5);
        buttons.add(hotel6);
        buttons.add(hotel7);
        buttons.add(hotel8);
        buttons.add(hotel9);
        buttons.add(hotel10);

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

        continueButton.setText("Continuar");
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButtonActionPerformed(evt);
            }
        });

        errorText.setForeground(new java.awt.Color(255, 0, 0));
        errorText.setText("Seleciona un hotel");
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
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(continueButton)
                                .addGap(209, 209, 209))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(105, 105, 105)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(hotel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(hotel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(hotel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(hotel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(hotel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(hotel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(hotel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(hotel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(hotel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(hotel10, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(199, 199, 199)
                                                .addComponent(errorText)))
                                .addContainerGap(195, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(salirButton)
                                        .addComponent(volverButton))
                                .addGap(24, 24, 24)
                                .addComponent(errorText)
                                .addGap(18, 18, 18)
                                .addComponent(hotel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hotel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hotel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hotel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hotel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hotel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hotel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hotel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hotel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hotel10)
                                .addGap(18, 18, 18)
                                .addComponent(continueButton)
                                .addContainerGap(45, Short.MAX_VALUE))
        );


        pack();
    }// </editor-fold>                        

    private void volverButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        ventanaPrincipal.setVisible(true);
    }

    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void continueButtonActionPerformed(java.awt.event.ActionEvent evt) {
        selectedHotel = getSelectedButtonText(hotelSelection);

        if(selectedHotel == null){
            errorText.setVisible(true);
            return;
        }

        errorText.setVisible(false);
        Variables.hotel = selectedHotel;
        ReservarFechas reservarFechasVentana = new ReservarFechas(this);
        reservarFechasVentana.setVisible(true);
        this.setVisible(false);
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    public void updateHotelButtons(){
        Connection BD = (Connection) conectarBD();
        listaHoteles = FuncionesHoteles.conseguirNombreHoteles(BD);
        desconexion((java.sql.Connection) BD);

        for(int i = 0; i< buttons.size(); i++){
            buttons.get(i).setText(listaHoteles.get(i));
        }
    }
    // Variables declaration - do not modify                     
    private javax.swing.JRadioButton hotel1;
    private javax.swing.JRadioButton hotel10;
    private javax.swing.JRadioButton hotel2;
    private javax.swing.JRadioButton hotel3;
    private javax.swing.JRadioButton hotel4;
    private javax.swing.JRadioButton hotel5;
    private javax.swing.JRadioButton hotel6;
    private javax.swing.JRadioButton hotel7;
    private javax.swing.JRadioButton hotel8;
    private javax.swing.JRadioButton hotel9;
    private javax.swing.ButtonGroup hotelSelection;
    private javax.swing.JButton salirButton;
    private javax.swing.JButton volverButton;
    private javax.swing.JButton continueButton;
    private javax.swing.JLabel errorText;
    // End of variables declaration                   
}