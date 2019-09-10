/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.controladores.IControladorVideo;
import logica.controladores.IControladorCategoria;

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
    IControladorCategoria cate = f.getIControladorCategoria();

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
        bajaSeguidorMenuItem = new javax.swing.JMenuItem();
        RegistroCategoria = new javax.swing.JMenuItem();
        CrearLista = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        listarCategorias = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        ModificarLista = new javax.swing.JMenuItem();
        ConsultoCategoriass = new javax.swing.JMenuItem();

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

        bajaSeguidorMenuItem.setText("Baja Seguidor");
        bajaSeguidorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajaSeguidorMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(bajaSeguidorMenuItem);

        RegistroCategoria.setText("Registrar Categoría");
        RegistroCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistroCategoriaActionPerformed(evt);
            }
        });
        jMenu2.add(RegistroCategoria);

        CrearLista.setText("Registrar Lista de Reproduccion");
        CrearLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearListaActionPerformed(evt);
            }
        });
        jMenu2.add(CrearLista);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Consultas");

        listarCategorias.setText("Lista de Categorías");
        listarCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarCategoriasActionPerformed(evt);
            }
        });
        jMenu3.add(listarCategorias);

        jMenuItem1.setText("Lista de Usuarios");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Lista de Videos");
        jMenu3.add(jMenuItem2);

        ModificarLista.setText("Listado de Listas de Reproduccion");
        ModificarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarListaActionPerformed(evt);
            }
        });
        jMenu3.add(ModificarLista);

        ConsultoCategoriass.setText("Consultar Categorías");
        ConsultoCategoriass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultoCategoriassActionPerformed(evt);
            }
        });
        jMenu3.add(ConsultoCategoriass);

        jMenuBar1.add(jMenu3);

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

    protected void AgregarInternalFrame(JInternalFrame f) {
        try{
            Escritorio.add(f);
            Escritorio.moveToFront(f);
            f.setSize(Escritorio.getWidth(),Escritorio.getHeight());
            f.setLocation(0,0);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

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
            JOptionPane.showMessageDialog(null,"Ya tiene una ventana de usuarios abierta");
        }
       /*
        Dimension desktopSize = Escritorio.getSize();
        Dimension FrameSize = registroUserWin.getSize();
        registroUserWin.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        registroUserWin.show();
        */

    }//GEN-LAST:event_RegistrarUsuarioActionPerformed


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
            JOptionPane.showMessageDialog(null,"Ya tiene una ventana de videos abierta");
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

    private void CrearListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearListaActionPerformed
        // TODO add your handling code here:
        try{

             crearlistaReproduccion listaRep = new crearlistaReproduccion(user);
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

    private void bajaSeguidorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaSeguidorMenuItemActionPerformed
        // TODO add your handling code here:
        dejarDeSeguirUsuario dejarU = new  dejarDeSeguirUsuario(user);
         Escritorio.add(dejarU);
         Escritorio.moveToFront(dejarU);
         dejarU.setSize(Escritorio.getWidth(),Escritorio.getHeight());
         dejarU.setLocation(0,0);
         dejarU.setVisible(true);
    }//GEN-LAST:event_bajaSeguidorMenuItemActionPerformed

    private void RegistroCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistroCategoriaActionPerformed
             registroCategorias registroC = new registroCategorias(cate);
             Escritorio.add(registroC);
             Escritorio.moveToFront(registroC);
             registroC.setSize(Escritorio.getWidth(),Escritorio.getHeight());
             registroC.setLocation(0,0);
             registroC.setVisible(true);
    }//GEN-LAST:event_RegistroCategoriaActionPerformed

    private void listarCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarCategoriasActionPerformed
        listarCategorias listaC = new listarCategorias(cate);
             Escritorio.add(listaC);
             Escritorio.moveToFront(listaC);
             listaC.setSize(Escritorio.getWidth(),Escritorio.getHeight());
             listaC.setLocation(0,0);
             listaC.setVisible(true);

    }//GEN-LAST:event_listarCategoriasActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
            listarUsuarios frmListarUsuarios = new listarUsuarios(user, this, Escritorio);
             Escritorio.add(frmListarUsuarios);
             Escritorio.moveToFront(frmListarUsuarios);
             frmListarUsuarios.setSize(Escritorio.getWidth(),Escritorio.getHeight());
             frmListarUsuarios.setLocation(0,0);
             frmListarUsuarios.setVisible(true);
             frmListarUsuarios.show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    private void ModificarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarListaActionPerformed
        try{

            listaDeReproduccionFrame listaRep = new listaDeReproduccionFrame(user, vid, this);
            Escritorio.add(listaRep);
            Escritorio.moveToFront(listaRep);
            listaRep.setSize(Escritorio.getWidth(),Escritorio.getHeight());
            listaRep.setLocation(0,0);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_ModificarListaActionPerformed


    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        listarVideos frmListarVideos = new listarVideos(user,vid,Escritorio);
        Escritorio.add(frmListarVideos);
        Escritorio.moveToFront(frmListarVideos);
        frmListarVideos.setSize(Escritorio.getWidth(),Escritorio.getHeight());
        frmListarVideos.setLocation(0,0);
        frmListarVideos.setVisible(true);
        frmListarVideos.show();
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    

    private void ConsultoCategoriassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultoCategoriassActionPerformed
       consultarCategorias consultaC = new consultarCategorias(cate,user);
             Escritorio.add(consultaC);
             Escritorio.moveToFront(consultaC);
             //consultaC.setSize(Escritorio.getWidth(),Escritorio.getHeight());
             consultaC.setLocation(0,0);
             consultaC.setVisible(true);
    }//GEN-LAST:event_ConsultoCategoriassActionPerformed

    private void ConsultoCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    
                                              


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
    private javax.swing.JMenuItem ConsultoCategoriass;
    private javax.swing.JMenuItem CrearLista;
    private javax.swing.JDesktopPane Escritorio;
    private javax.swing.JMenuItem ModificarLista;
    private javax.swing.JMenuItem RegistrarUsuario;
    private javax.swing.JMenuItem RegistrarVideo;
    private javax.swing.JMenuItem RegistroCategoria;
    private javax.swing.JMenuItem bajaSeguidorMenuItem;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem listarCategorias;
    private javax.swing.JMenuItem seguirUsuario;
    // End of variables declaration//GEN-END:variables
}
