/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import logica.controladores.IControladorUsuario;
import logica.controladores.IControladorVideo;
import logica.dt.VideoDt;
import logica.dt.valoracionDt;

/**
 *
 * @author Esteban
 */
public class consultarVideo extends javax.swing.JInternalFrame {

    /**
     * Creates new form consultarVideo
     */
    IControladorVideo cv;
    IControladorUsuario cu;
    
    public consultarVideo(IControladorVideo v, IControladorUsuario u, VideoDt video) {
        initComponents();
        setTitle("Información del video: " + video.getNombre());
        setVisible(true);
        cv = v;
        cu= u;
        
        //Muestro datos del dt
        LabelNombre.setText("Nombre: " + video.getNombre());
        LabelURL.setText("URL: " + video.getUrl());
        String categoria = video.getCategoria();
        if(categoria == null) categoria = "Ninguna";
        LabelCategoria.setText("Categoria: " + categoria);
        LabelDuracion.setText("Duración: " + video.getDuracion() + " minutos");
        String privacidad = "Publico";
        if(video.getPrivacidad()) privacidad = "Privado";
        LabelPrivacidad.setText(privacidad);
        LabelFecha.setText("Fecha de publicación: " + video.getFechaPublicacion().toString());
        DescripcionArea.setText(video.getDescripcion());
        DescripcionArea.setEditable(false);
        
        //Comentarios
        DefaultMutableTreeNode root = cv.obtenerComentariosVideo(video.getId());
        if(root != null) jTree1.setModel(new DefaultTreeModel(root));
        //Valoraciones
        List<valoracionDt> list = cv.obtenerValoracionVideo(video.getId());
        if(list != null) for(int i = 0; i < list.size(); i++) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            String gustar = "Me gusta";
            if(!list.get(i).getGusto()) gustar = "No me gusta";
            model.addRow(new Object[]{list.get(i).getUser(), gustar});
        }
        LabelGustar.setText("Me Gusta: " + video.getLikes() + " No me gusta: " + video.getDislikes());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        LabelGustar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LabelNombre = new javax.swing.JLabel();
        LabelCategoria = new javax.swing.JLabel();
        LabelPrivacidad = new javax.swing.JLabel();
        LabelDuracion = new javax.swing.JLabel();
        LabelFecha = new javax.swing.JLabel();
        LabelDescripcion = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        DescripcionArea = new javax.swing.JTextArea();
        LabelURL = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Aca no hay nada");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setToolTipText("");
        jScrollPane1.setViewportView(jTree1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User", "Valoracion"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        LabelGustar.setText("Me Gusta: XX  No me gusta: XX");

        jLabel2.setText("Comentarios");

        jLabel3.setText("Valoraciones");

        LabelNombre.setText("Nombre:");

        LabelCategoria.setText("Categoria:");

        LabelPrivacidad.setText("Privacidad:");

        LabelDuracion.setText("Duración:");

        LabelFecha.setText("Fecha de publicación: ");

        LabelDescripcion.setText("Descripción:");

        DescripcionArea.setColumns(20);
        DescripcionArea.setRows(5);
        jScrollPane3.setViewportView(DescripcionArea);

        LabelURL.setText("URL:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelGustar, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LabelURL, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(LabelCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(LabelDuracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(LabelFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                                    .addComponent(LabelPrivacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LabelDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNombre)
                    .addComponent(LabelDescripcion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelURL)
                        .addGap(8, 8, 8)
                        .addComponent(LabelCategoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelDuracion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelPrivacidad)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelGustar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea DescripcionArea;
    private javax.swing.JLabel LabelCategoria;
    private javax.swing.JLabel LabelDescripcion;
    private javax.swing.JLabel LabelDuracion;
    private javax.swing.JLabel LabelFecha;
    private javax.swing.JLabel LabelGustar;
    private javax.swing.JLabel LabelNombre;
    private javax.swing.JLabel LabelPrivacidad;
    private javax.swing.JLabel LabelURL;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
