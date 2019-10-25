/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentacion;

import java.util.Iterator;
import java.util.List;
import logica.controladores.IControladorVideo;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import logica.controladores.IControladorCategoria;
import logica.dt.CategoriaDt;

/**
 *
 * @author pagol
 */
public class modificarVideo extends javax.swing.JInternalFrame {
    
    IControladorVideo v;
    IControladorCategoria c;
    int video_id;

    /** Creates new form modificarVideo */
    public modificarVideo(IControladorVideo video, IControladorCategoria cat, int idd) {
        setVisible(true);
        setResizable(true);
        initComponents();
        
        //Spinner model aca
        SpinnerNumberModel modeloSpinner = new SpinnerNumberModel();
        modeloSpinner.setMinimum(0);
        jSpinner1.setModel(modeloSpinner);
        
        SpinnerNumberModel modeloSpinner2 = new SpinnerNumberModel();
        modeloSpinner2.setMaximum(59);
        modeloSpinner2.setMinimum(0);
        jSpinner2.setModel(modeloSpinner2);
        
        
        setTitle("Modificar Video");
        setClosable(true);
        v=video;
        c=cat;
        video_id=idd;
        obtenerCategorias();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label5 = new java.awt.Label();
        label4 = new java.awt.Label();
        desField4 = new java.awt.TextField();
        urlField3 = new java.awt.TextField();
        label3 = new java.awt.Label();
        label2 = new java.awt.Label();
        nomField1 = new java.awt.TextField();
        label1 = new java.awt.Label();
        jButton1 = new javax.swing.JButton();
        jComboBoxPrivacidad = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        label6 = new java.awt.Label();
        label7 = new java.awt.Label();
        CategoriasBox = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        label8 = new java.awt.Label();
        jSpinner2 = new javax.swing.JSpinner();

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label5.setText("Fecha Publicacion");
        getContentPane().add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 144, -1, -1));

        label4.setText("Descripcion");
        getContentPane().add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 114, -1, -1));
        getContentPane().add(desField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 114, 81, -1));
        getContentPane().add(urlField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 84, 81, -1));

        label3.setText("UrlAsociada");
        getContentPane().add(label3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 84, -1, -1));

        label2.setText("Duracion");
        getContentPane().add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 54, -1, -1));
        getContentPane().add(nomField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 24, 77, -1));

        label1.setText("Nombre");
        getContentPane().add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 24, -1, -1));

        jButton1.setText("Modificar");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 258, -1, -1));

        jComboBoxPrivacidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Privado", "Publico" }));
        jComboBoxPrivacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPrivacidadActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxPrivacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 175, 81, -1));
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 144, -1, -1));

        jButton2.setText("Cancelar");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 258, -1, -1));

        label6.setText("Privacidad");
        getContentPane().add(label6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 175, -1, -1));

        label7.setText("Categoria");
        getContentPane().add(label7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 205, -1, -1));

        CategoriasBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguna" }));
        getContentPane().add(CategoriasBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 205, 77, -1));
        getContentPane().add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 54, -1, -1));

        label8.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label8.setText(":");
        getContentPane().add(label8, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 54, -1, -1));
        getContentPane().add(jSpinner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 54, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       
        String durat;
            
        try { 
            
            if(Integer.parseInt(jSpinner2.getValue().toString())<10){
            durat = jSpinner1.getValue().toString()+".0"+jSpinner2.getValue().toString();
            }else{
            durat = jSpinner1.getValue().toString()+"."+jSpinner2.getValue().toString();
            }
            
            
             boolean privado = false;
             if(jComboBoxPrivacidad.getSelectedItem() == jComboBoxPrivacidad.getItemAt(0)) privado = true;
             String categoria = CategoriasBox.getSelectedItem().toString();
             
           v.ModificarVideo(
                    video_id,
                    nomField1.getText(),
                    durat,
                    urlField3.getText(), 
                    desField4.getText(),
                    jDateChooser1.getDate(),
                    privado,
                    categoria
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error Modificando Video: "+e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxPrivacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPrivacidadActionPerformed

    }//GEN-LAST:event_jComboBoxPrivacidadActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    private void obtenerCategorias() {
        List cts = c.ListarCategorias();
        Iterator it = cts.iterator();
        
        while(it.hasNext()){
            CategoriaDt ct = (CategoriaDt) it.next();
            CategoriasBox.addItem(ct.getNombre());
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CategoriasBox;
    private java.awt.TextField desField4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxPrivacidad;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private java.awt.TextField nomField1;
    private java.awt.TextField urlField3;
    // End of variables declaration//GEN-END:variables

}