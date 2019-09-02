/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.Dimension;
import java.beans.PropertyVetoException;
import javax.swing.JOptionPane;
import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.controladores.IControladorVideo;

/**
 *
 * @author Esteban
 */
public class UyTubeFrame extends javax.swing.JFrame {

    /**
     * Creates new form FramePrincipal
     */
    Fabrica f = Fabrica.getInstance();
    IControladorUsuario user = f.getIControladorUsuario();
    IControladorVideo vid = f.getIControladorVideo();

    public UyTubeFrame() {
        initComponents();
    }
    private registroVideo registroVidWin;
    private registroUser registroUserWin;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        Escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        RegistrarUsuario = new javax.swing.JMenuItem();
        RegistrarVideo = new javax.swing.JMenuItem();
        seguirUsuario = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        CrearLista = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );

        jMenu1.setText("Inicio");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Registro");

        RegistrarUsuario.setText("Registrar Usuario");
        RegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarUsuarioActionPerformed(evt);
            }
        });
        jMenu2.add(RegistrarUsuario);

        RegistrarVideo.setText("Registrar Video");
        RegistrarVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarVideoActionPerformed(evt);
            }
        });
        jMenu2.add(RegistrarVideo);

        seguirUsuario.setText("Registro Seguidor");
        seguirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seguirUsuarioActionPerformed(evt);
            }
        });
        jMenu2.add(seguirUsuario);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Consultas");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Listas");
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });

        CrearLista.setText("Crear Lista de Reproduccion");
        CrearLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearListaActionPerformed(evt);
            }
        });
        jMenu4.add(CrearLista);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Categoria");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarUsuarioActionPerformed
        // TODO add your handling code here:
        //Agregar Frame para esta funcion
        String x = registroUser.x;

        if(x==null){
        try{

             registroUserWin = new registroUser(user, this);
             Escritorio.add(registroUserWin);
             Escritorio.moveToFront(registroUserWin);
             registroUserWin.setSize(Escritorio.getWidth(),Escritorio.getHeight());
             registroUserWin.setLocation(0,0);


        }catch(Exception ex){
            ex.printStackTrace();
        }
        }else{
            JOptionPane.showMessageDialog(null,"Ya tiene una venta de usuarios abierta");
        }
       /*
        Dimension desktopSize = Escritorio.getSize();
        Dimension FrameSize = registroUserWin.getSize();
        registroUserWin.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        registroUserWin.show();
        */

    }//GEN-LAST:event_RegistrarUsuarioActionPerformed

    protected void RegistrarCanal(String n) {
        try{

            registroCanal canalWin = new registroCanal(user, n);
            Escritorio.add(canalWin);
            Escritorio.moveToFront(canalWin);
            canalWin.setSize(Escritorio.getWidth(),Escritorio.getHeight());
            canalWin.setLocation(0,0);
            canalWin.setVisible(true);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void RegistrarVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarVideoActionPerformed
        // TODO add your handling code here:
          String registroVideoX = registroVideo.VideoRegister;

        if(registroVideoX==null){
        try{

             registroVidWin = new registroVideo(vid, user);
             Escritorio.add(registroVidWin);
             Escritorio.moveToFront(registroVidWin);
             registroVidWin.setSize(Escritorio.getWidth(),Escritorio.getHeight());
             registroVidWin.setLocation(0,0);


        }catch(Exception ex){
            ex.printStackTrace();
        }
        }else{
            JOptionPane.showMessageDialog(null,"Ya tiene una venta de usuarios abierta");
        }
    }//GEN-LAST:event_RegistrarVideoActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:

        String registroVideoX = registroVideo.VideoRegister;
        String x = registroUser.x;



        try{
             welcome welcomeUser = new welcome();
             Escritorio.add(welcomeUser);
             Escritorio.moveToFront(welcomeUser);
             registroVidWin.setSize(Escritorio.getWidth(),Escritorio.getHeight());
             registroVidWin.setLocation(0,0);


        }catch(Exception ex){
            ex.printStackTrace();
        }

          /*
        if(registroVideoX!=null){
          try {
                registroVidWin.setClosed(true);
              } catch (PropertyVetoException ex) {
                  System.err.println("Closing Exception");
              }
        }

        if(x!=null){
            try {
                registroUserWin.setClosed(true);
              } catch (PropertyVetoException ex) {
                  System.err.println("Closing Exception");
              }
        }

        */
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed


    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed

        try{

             categoriaRegistro categoriaRegistro = new categoriaRegistro();
             Escritorio.add(categoriaRegistro);
             Escritorio.moveToFront(categoriaRegistro);
             categoriaRegistro.setSize(Escritorio.getWidth(),Escritorio.getHeight());
             categoriaRegistro.setLocation(0,0);
            categoriaRegistro.setVisible(true);
        }catch(Exception ex){
        ex.printStackTrace();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5ActionPerformed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5MouseClicked

    private void CrearListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearListaActionPerformed
        // TODO add your handling code here:
        try{

             listaReproduccion listaRep = new listaReproduccion(user);
             Escritorio.add(listaRep);
             Escritorio.moveToFront(listaRep);
             listaRep.setSize(Escritorio.getWidth(),Escritorio.getHeight());
             listaRep.setLocation(0,0);

        
        }catch(Exception ex){
        ex.printStackTrace();
        }
    }//GEN-LAST:event_CrearListaActionPerformed

    
        private void seguirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seguirUsuarioActionPerformed
        // TODO add your handling code here:
           seguirUsuario seguirU = new seguirUsuario(user);
             Escritorio.add(seguirU);
             Escritorio.moveToFront(seguirU);
             seguirU.setSize(Escritorio.getWidth(),Escritorio.getHeight());
             seguirU.setLocation(0,0);
             seguirU.setVisible(true);

    }//GEN-LAST:event_seguirUsuarioActionPerformed
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(UyTubeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UyTubeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UyTubeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UyTubeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UyTubeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CrearLista;
    private javax.swing.JDesktopPane Escritorio;
    private javax.swing.JMenuItem RegistrarUsuario;
    private javax.swing.JMenuItem RegistrarVideo;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem seguirUsuario;
    // End of variables declaration//GEN-END:variables
}
