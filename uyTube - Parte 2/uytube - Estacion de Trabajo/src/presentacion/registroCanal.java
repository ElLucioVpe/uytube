/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import javax.swing.JOptionPane;
import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;

/**
 *
 * @author Esteban
 */
public class registroCanal extends javax.swing.JInternalFrame {

    /**
     * Creates new form registroCanal
     */
    Fabrica fab = Fabrica.getInstance();
    IControladorUsuario u;
    String user_nick;
    
    public registroCanal() {
        u = null;
        user_nick = "";
    }
    
    public registroCanal(String nick) {
        setVisible(true);
        initComponents();
        setTitle("Registrar Canal");
        setClosable(true);
        u = fab.getIControladorUsuario();
        user_nick = nick;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxPrivacidad = new javax.swing.JComboBox<>();
        jTextFieldNombre = new javax.swing.JTextField();
        jButtonFinalizar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescripcion = new javax.swing.JTextArea();
        CategoriasBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Nombre de Canal(opcional):");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 22));

        jLabel3.setText("Categoria (opcional):");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        jComboBoxPrivacidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Privado", "Publico" }));
        jComboBoxPrivacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPrivacidadActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxPrivacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 81, -1));
        getContentPane().add(jTextFieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 91, -1));

        jButtonFinalizar.setText("Finalizar");
        jButtonFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, -1, -1));

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 235, -1, -1));

        jLabel4.setText("Descripción:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, 22));

        jTextAreaDescripcion.setColumns(20);
        jTextAreaDescripcion.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDescripcion);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, -1, -1));

        CategoriasBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguna" }));
        CategoriasBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CategoriasBoxActionPerformed(evt);
            }
        });
        getContentPane().add(CategoriasBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 112, -1));

        jLabel5.setText("Privacidad:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxPrivacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPrivacidadActionPerformed

    }//GEN-LAST:event_jComboBoxPrivacidadActionPerformed

    private void jButtonFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarActionPerformed
        boolean privado = false;
        if(jComboBoxPrivacidad.getSelectedItem() == jComboBoxPrivacidad.getItemAt(0)) privado = true;
        int user_id = u.obtenerIdUsuario(user_nick);
        u.AltaCanal(jTextFieldNombre.getText(), privado, CategoriasBox.getSelectedItem().toString(), user_id, jTextAreaDescripcion.getText());
        //JOptionPane.showMessageDialog(null,"El usuario se registro con exito");
        this.dispose();
    }//GEN-LAST:event_jButtonFinalizarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed

        //Eliminar usuario
        u.EliminarUsuario(u.obtenerIdUsuario(user_nick));
        JOptionPane.showMessageDialog(null,"El registro ha sido cancelado");
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        //Eliminar usuario
        u.EliminarUsuario(u.obtenerIdUsuario(user_nick));
        JOptionPane.showMessageDialog(null,"El registro ha sido cancelado");
        this.dispose();
    }//GEN-LAST:event_formInternalFrameClosing

    private void CategoriasBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CategoriasBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CategoriasBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CategoriasBox;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonFinalizar;
    private javax.swing.JComboBox<String> jComboBoxPrivacidad;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaDescripcion;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
