/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//A aca se llega asi, ListarUsuarios --> ConsultarUsuario --> ModificarUsuario
package presentacion;

import com.toedter.calendar.JTextFieldDateEditor;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import logica.controladores.IControladorUsuario;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Esteban
 */
public class modificarUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form modificarUsuario
     */
    IControladorUsuario u;
    String u_nick;
    int u_id;
    JFileChooser fc;
    
    public modificarUsuario(IControladorUsuario user, String user_nick) {
        setVisible(true);
        initComponents();
        setTitle("Modificar Usuario");
        setClosable(true);
        u=user;
        u_nick = user_nick;
        u_id = u.obtenerIdUsuario(user_nick);
        fc = new JFileChooser();
        fc.setVisible(false);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) jDateChooser1.getDateEditor();
        editor.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombreField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        apellField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonSubir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        imagenField = new javax.swing.JTextField();
        nombreCanalField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descCanalField = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jComboBoxPrivacidad = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setClosable(true);

        nombreField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreFieldActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre");

        apellField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Apellido");

        jLabel3.setText("Fecha de Nacimiento");

        jButtonSubir.setText("Subir");
        jButtonSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubirActionPerformed(evt);
            }
        });

        jLabel4.setText("Imagen");

        imagenField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imagenFieldActionPerformed(evt);
            }
        });

        nombreCanalField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreCanalFieldActionPerformed(evt);
            }
        });

        jLabel5.setText("Nombre del Canal");

        jLabel6.setText("Descripción del Canal");

        descCanalField.setColumns(20);
        descCanalField.setRows(5);
        jScrollPane1.setViewportView(descCanalField);

        jButton1.setText("Modificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");

        jComboBoxPrivacidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Privado", "Publico" }));
        jComboBoxPrivacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPrivacidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCancelar)
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(imagenField, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSubir))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(apellField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nombreCanalField, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxPrivacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSubir)
                    .addComponent(imagenField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreCanalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxPrivacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCancelar)
                            .addComponent(jButton1)))
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombreFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreFieldActionPerformed

    private void apellFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellFieldActionPerformed

    private void jButtonSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubirActionPerformed
        fc.setVisible(true);
        fc.showOpenDialog(null);
        File f = fc.getSelectedFile();
        String fpath = f.getAbsolutePath();
        imagenField.setText(fpath);
    }//GEN-LAST:event_jButtonSubirActionPerformed

    private void imagenFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imagenFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_imagenFieldActionPerformed

    private void nombreCanalFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreCanalFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreCanalFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // Me aseguro de que la imagen es correcta y la guardo
            File f = fc.getSelectedFile();
            String fpath = "";
            String fnewpath = "";
            if(f != null){
                fpath = f.getPath();
                String extension = "";
                int i = fpath.lastIndexOf('.');
                if (i > 0) extension = fpath.substring(i+1);

                if (!extension.equals("jpg") && !extension.equals("png"))
                    throw new Exception(extension+" Imagen con extension invalida, por favor suba una imagen .jpg o .png");

                String imagen = u_nick + "." + extension;
                fnewpath = "data/imagenes/" + imagen;
            }
            
            //Modifico el usuario
            boolean privado = false;
            if(jComboBoxPrivacidad.getSelectedItem() == jComboBoxPrivacidad.getItemAt(0)) privado = true;
            
            u.ModificarUsuario(
                    u_id, 
                    nombreField.getText(),
                    apellField.getText(),
                    jDateChooser1.getDate(),  
                    nombreCanalField.getText(),
                    descCanalField.getText(),
                    privado
            );
            
            //Copio el archivo al directorio de imagenes, lo hago luego por si el nick ya existe
            if(!fpath.equals(fnewpath) && !fnewpath.isEmpty()) FileUtils.copyFile(f, new File(fnewpath));
            
            //Creacion de listas por defecto (favoritos, ver mas tarde, etc)
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxPrivacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPrivacidadActionPerformed

    }//GEN-LAST:event_jComboBoxPrivacidadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellField;
    private javax.swing.JTextArea descCanalField;
    private javax.swing.JTextField imagenField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSubir;
    private javax.swing.JComboBox<String> jComboBoxPrivacidad;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombreCanalField;
    private javax.swing.JTextField nombreField;
    // End of variables declaration//GEN-END:variables
}