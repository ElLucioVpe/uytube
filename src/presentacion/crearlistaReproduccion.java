/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.util.Iterator;
import java.util.List;
import logica.controladores.IControladorUsuario;

/**
 *
 * @author pagol
 */
public class crearlistaReproduccion extends javax.swing.JInternalFrame {

    /**
     * Creates new form listaReproduccionFrame
     */
    IControladorUsuario u;
    boolean esParticular;
    
    public crearlistaReproduccion(IControladorUsuario userC) {
        initComponents();
        u = userC;
        esParticular = false;
        this.setVisible(true);
        this.MostrarParticular(false);
        this.obtenerCategorias();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButtonCrear = new javax.swing.JButton();
        list1 = new java.awt.List();
        ParticularButton = new javax.swing.JToggleButton();
        PorDefectoButton = new javax.swing.JToggleButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxPrivacidad = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CategoriasBox = new javax.swing.JComboBox<>();

        jButton2.setText("Crear");

        setClosable(true);

        jLabel1.setText("Nombre");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButtonCrear.setText("Crear");
        jButtonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearActionPerformed(evt);
            }
        });

        list1.setVisible(false);

        ParticularButton.setText("Particular");
        ParticularButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ParticularButtonStateChanged(evt);
            }
        });
        ParticularButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ParticularButtonActionPerformed(evt);
            }
        });

        PorDefectoButton.setText("Por Defecto");
        PorDefectoButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                PorDefectoButtonStateChanged(evt);
            }
        });
        PorDefectoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PorDefectoButtonActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabel3.setText("Privacidad:");

        jComboBoxPrivacidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Privado", "Publico" }));
        jComboBoxPrivacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPrivacidadActionPerformed(evt);
            }
        });

        jLabel2.setText("Usuario");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Categoria (opcional):");

        CategoriasBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguna" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ParticularButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PorDefectoButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CategoriasBox, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxPrivacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButtonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCancelar)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ParticularButton)
                    .addComponent(PorDefectoButton))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxPrivacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CategoriasBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonCrear))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void ParticularButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ParticularButtonActionPerformed
        esParticular = true;
        MostrarParticular(true);
    }//GEN-LAST:event_ParticularButtonActionPerformed

    private void PorDefectoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PorDefectoButtonActionPerformed
        esParticular = false;
        MostrarParticular(false);
    }//GEN-LAST:event_PorDefectoButtonActionPerformed

    private void jComboBoxPrivacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPrivacidadActionPerformed

    }//GEN-LAST:event_jComboBoxPrivacidadActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        if(esParticular) {
            boolean privado = false;
            if(jComboBoxPrivacidad.getSelectedItem() == jComboBoxPrivacidad.getItemAt(0)) privado = true;
            String categoria = CategoriasBox.getSelectedItem().toString();
            
            u.AltaListaDeReproduccionParticular(
                    jTextField1.getText(), 
                    u.obtenerIdUsuario(jTextField2.getText()), privado,
                    categoria
            );
        }
        else u.AltaListaDeReproduccionPorDefecto(jTextField1.getText());
    }//GEN-LAST:event_jButtonCrearActionPerformed

    private void ParticularButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ParticularButtonStateChanged

    }//GEN-LAST:event_ParticularButtonStateChanged

    private void PorDefectoButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_PorDefectoButtonStateChanged

    }//GEN-LAST:event_PorDefectoButtonStateChanged
    
    //Auxiliares
    private void MostrarParticular(boolean x) {
        jLabel2.setVisible(x);
        jTextField2.setVisible(x);
        jLabel3.setVisible(x);
        jComboBoxPrivacidad.setVisible(x);
        jLabel4.setVisible(x);
        CategoriasBox.setVisible(x);
    }
    
    private void obtenerCategorias() {
        List cts = u.obtenerCategorias();
        Iterator it = cts.iterator();
        
        while(it.hasNext()){
            CategoriasBox.addItem((String)it.next());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CategoriasBox;
    private javax.swing.JToggleButton ParticularButton;
    private javax.swing.JToggleButton PorDefectoButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JComboBox<String> jComboBoxPrivacidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private java.awt.List list1;
    // End of variables declaration//GEN-END:variables
}
