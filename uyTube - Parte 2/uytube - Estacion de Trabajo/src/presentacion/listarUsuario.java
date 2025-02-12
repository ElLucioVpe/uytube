/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import logica.Canal;
import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.controladores.IControladorVideo;
import logica.dt.UsuarioDt;
import logica.dt.VideoDt;

/**
 *
 * @author Luciano
 */
public class listarUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form listarUsuario
     */
    Fabrica fab = Fabrica.getInstance();
    String usuario;
    UyTubeFrame p;
    IControladorUsuario u;
    IControladorVideo v;
    UsuarioDt dt;
    List<String> listas;
    List<String> seguidores;
    List<String> siguiendo;
    List<VideoDt> videos;
    Canal canal;

    public listarUsuario(UyTubeFrame padre, String _usuario) {
        initComponents();
        this.setVisible(true);
        ConsultarL.setEnabled(false);
        ConsultarV.setEnabled(false);
        u = fab.getIControladorUsuario();
        v = fab.getIControladorVideo();
        p = padre;
        usuario = _usuario;
        int id = u.obtenerIdUsuario(usuario);
        dt = u.ConsultarUsuario(id);
        canal = dt.getCanal();
        listas = u.obtenerListasUsuario(id);
        videos = u.listarVideosDeUsuario(_usuario);
        siguiendo = u.ListarSiguiendo(id);
        seguidores = u.ListarSeguidores(id);
        
        lblNickname.setText(dt.getNickname()); 
        lblNombre.setText(dt.getNombre()); 
        lblApellido.setText(dt.getApellido());
        lblFechaNac.setText(dt.getFechanac().toString()); 
        lblMail.setText(dt.getMail());
        lblDescripcion.setText(canal.getDescripcion());
        lblDescripcion.setEditable(false);
        
        if(dt.getImagen() == null) ImagenLabel.setText("<html>Este usuario<br/>no tiene imagen</html>");
        else {
            try {
                BufferedImage img = ImageIO.read(new File("../data/imagenes/" + dt.getImagen()));
                ImageIcon icon = new ImageIcon(resize(img, 80, 100));
                ImagenLabel.setIcon(icon);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        lblNombreCanal.setText(canal.getNombre());
        String privacidad = "Publico";
        if(canal.getPrivacidad()) privacidad = "Privado";
        lblPrivacidadCanal.setText(privacidad);
        String categoria = "Ninguna";
        if(canal.getCategoria() != null) categoria = canal.getCategoria().getNombre();
        lblCategoriaCanal.setText(categoria);
        
        cargarListas();
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel1 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblFechaNac = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblMail = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblNickname = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblNombreCanal = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblPrivacidadCanal = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listListas = new javax.swing.JList<>();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listVideos = new javax.swing.JList<>();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listSeguidores = new javax.swing.JList<>();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listSiguiendo = new javax.swing.JList<>();
        ImagenLabel = new javax.swing.JLabel();
        ConsultarV = new javax.swing.JButton();
        ConsultarL = new javax.swing.JButton();
        lblNombreCanal1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lblDescripcion = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        lblCategoriaCanal = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuario");

        jLabel1.setText("Nombre:");

        lblNombre.setText("lblNombre");
        lblNombre.setToolTipText("");

        jLabel3.setText("Apellido:");

        lblApellido.setText("lblApellido");

        jLabel5.setText("Fecha nac.:");

        lblFechaNac.setText("lblFechaNac");

        jLabel7.setText("Correo electrónico:");

        lblMail.setText("lblMail");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Listas de reproduccion");
        jLabel9.setToolTipText("");

        lblNickname.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblNickname.setText("lblNickname");
        lblNickname.setToolTipText("");

        jLabel11.setText("Nombre:");

        jLabel12.setText("jLabel2");

        jLabel13.setText("Apellido:");

        jLabel14.setText("jLabel4");

        jLabel15.setText("Fecha nac.");

        jLabel16.setText("jLabel2");

        jLabel17.setText("Mail");

        jLabel18.setText("jLabel4");

        jLabel19.setText("Canal");
        jLabel19.setToolTipText("");

        jLabel20.setText("Nickname");
        jLabel20.setToolTipText("");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel21.setText("Nombre:");

        lblNombreCanal.setText("lblNombreCanal");

        jLabel23.setText("Privacidad:");

        lblPrivacidadCanal.setText("lblPrivacidadCanal");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setText("Canal");
        jLabel25.setToolTipText("");

        listListas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listListasValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listListas);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("Videos");
        jLabel26.setToolTipText("");

        listVideos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listVideosValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listVideos);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("Seguidores");
        jLabel27.setToolTipText("");

        jScrollPane3.setViewportView(listSeguidores);

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("Siguiendo");
        jLabel28.setToolTipText("");

        jScrollPane4.setViewportView(listSiguiendo);

        ImagenLabel.setPreferredSize(new java.awt.Dimension(100, 80));

        ConsultarV.setText("Consultar Video");
        ConsultarV.setEnabled(false);
        ConsultarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarVActionPerformed(evt);
            }
        });

        ConsultarL.setText("Consultar Lista");
        ConsultarL.setEnabled(false);
        ConsultarL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarLActionPerformed(evt);
            }
        });

        lblNombreCanal1.setText("Descripción:");

        lblDescripcion.setColumns(20);
        lblDescripcion.setRows(5);
        jScrollPane5.setViewportView(lblDescripcion);

        jLabel24.setText("Categoria:");

        lblCategoriaCanal.setText("lblCategoriaCanal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNickname, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ImagenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(6, 6, 6)
                                        .addComponent(lblNombre))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(6, 6, 6)
                                        .addComponent(lblApellido))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(6, 6, 6)
                                        .addComponent(lblFechaNac)))
                                .addGap(86, 86, 86)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(6, 6, 6)
                                        .addComponent(lblNombreCanal))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addGap(6, 6, 6)
                                        .addComponent(lblPrivacidadCanal))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addGap(6, 6, 6)
                                        .addComponent(lblCategoriaCanal))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(35, 35, 35)
                        .addComponent(lblNombreCanal1)
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ConsultarL, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(190, 190, 190)
                                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ConsultarV, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNickname, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ImagenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombreCanal1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(lblNombre))
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(lblApellido)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21)
                                            .addComponent(lblNombreCanal))
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23)
                                            .addComponent(lblPrivacidadCanal))))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(lblFechaNac))
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(lblMail)))
                                    .addComponent(jLabel24)
                                    .addComponent(lblCategoriaCanal))))))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(ConsultarL))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(172, 172, 172)
                        .addComponent(ConsultarV))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConsultarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarVActionPerformed
        String video_nom = listVideos.getSelectedValue();
        int id_user = dt.getId();
        JInternalFrame f = new consultarVideo(v.obtenerVideoDt(video_nom, id_user));
        p.cambiarSize(f.getWidth(), f.getHeight());
        p.AgregarInternalFrame(f);
        this.dispose();
    }//GEN-LAST:event_ConsultarVActionPerformed

    private void ConsultarLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarLActionPerformed
        String lista = listListas.getSelectedValue();
        int id_user = dt.getId();
        p.AgregarInternalFrame(new consultarListaDR(p, lista, id_user));
        this.dispose();
    }//GEN-LAST:event_ConsultarLActionPerformed

    private void listListasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listListasValueChanged
        ConsultarL.setEnabled(true);
    }//GEN-LAST:event_listListasValueChanged

    private void listVideosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listVideosValueChanged
        ConsultarV.setEnabled(true);
    }//GEN-LAST:event_listVideosValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConsultarL;
    private javax.swing.JButton ConsultarV;
    private javax.swing.JLabel ImagenLabel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblCategoriaCanal;
    private javax.swing.JTextArea lblDescripcion;
    private javax.swing.JLabel lblFechaNac;
    private javax.swing.JLabel lblMail;
    private javax.swing.JLabel lblNickname;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombreCanal;
    private javax.swing.JLabel lblNombreCanal1;
    private javax.swing.JLabel lblPrivacidadCanal;
    private javax.swing.JList<String> listListas;
    private javax.swing.JList<String> listSeguidores;
    private javax.swing.JList<String> listSiguiendo;
    private javax.swing.JList<String> listVideos;
    // End of variables declaration//GEN-END:variables

    private void cargarListas() {
        DefaultListModel<String> model = new DefaultListModel<>();      
        if(listas.size() != 0) {
            for(int i = 0; i < listas.size(); i++) {
                model.addElement((String)listas.get(i));
            }
            listListas.setModel(model);
        }

        if(videos.size() != 0) {
            model = new DefaultListModel<>();
            for(int i = 0; i < videos.size(); i++) {
                model.addElement(videos.get(i).getNombre());
            }
            listVideos.setModel(model);
        }
        
        if(!seguidores.isEmpty()) {
            model = new DefaultListModel<>();
            Iterator<String> it = seguidores.iterator();
            while(it.hasNext()) {
                model.addElement(it.next());
            }
            listSeguidores.setModel(model);
        }
        
        if(!siguiendo.isEmpty()) {
            model = new DefaultListModel<>();
            Iterator<String> its = siguiendo.iterator();
            
            while(its.hasNext()) {
                model.addElement(its.next());
            }
            listSiguiendo.setModel(model);
        }
    }
}
