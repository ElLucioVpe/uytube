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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
        
        try {
            BufferedImage img = ImageIO.read(new File("../data/logo.png"));
            if(img != null){
                ImageIcon icon = new ImageIcon(resize(img, 80, 80));
                logoLabel.setIcon(icon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
        ELBOTON = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        logoLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        RegistrarUsuario = new javax.swing.JMenuItem();
        RegistrarVideo = new javax.swing.JMenuItem();
        RegistroCategoria = new javax.swing.JMenuItem();
        CrearLista = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        listarCategorias = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        ModificarLista = new javax.swing.JMenuItem();

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

        Escritorio.setBackground(new java.awt.Color(0, 0, 0));

        ELBOTON.setText("Cargar Datos");
        ELBOTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ELBOTONActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Bienvenido al sistema de gestión de");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 255, 255));
        jLabel2.setText("uyTube");

        logoLabel.setText("jLabel3");

        Escritorio.setLayer(ELBOTON, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(logoLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EscritorioLayout.createSequentialGroup()
                .addContainerGap(322, Short.MAX_VALUE)
                .addComponent(ELBOTON)
                .addContainerGap())
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(EscritorioLayout.createSequentialGroup()
                        .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EscritorioLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel1)
                .addGroup(EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EscritorioLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel2))
                    .addGroup(EscritorioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(ELBOTON)
                .addContainerGap())
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
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        ModificarLista.setText("Listado de Listas de Reproduccion");
        ModificarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarListaActionPerformed(evt);
            }
        });
        jMenu3.add(ModificarLista);

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
            cambiarSize(f.getWidth(), f.getHeight());
            f.setSize(Escritorio.getWidth(),Escritorio.getHeight());
            f.setLocation(0,0);
            f.setVisible(true);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    protected void cambiarSize(int w, int h) {
        this.setSize(w+15, h+70);
        Escritorio.setSize(w, h+10);
    }

    private void RegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarUsuarioActionPerformed
        // TODO add your handling code here:
        //Agregar Frame para esta funcion
        String x = registroUser.x;

        if(x==null){
            AgregarInternalFrame(new registroUser(this));
        }else{
            JOptionPane.showMessageDialog(null,"Ya tiene una ventana de usuarios abierta");
        }

    }//GEN-LAST:event_RegistrarUsuarioActionPerformed


    private void RegistrarVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarVideoActionPerformed
        String registroVideoX = registroVideo.VideoRegister;

        if(registroVideoX==null){
        try{
            AgregarInternalFrame(new registroVideo());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        }else{
            JOptionPane.showMessageDialog(null,"Ya tiene una ventana de videos abierta");
        }
    }//GEN-LAST:event_RegistrarVideoActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void CrearListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearListaActionPerformed
        AgregarInternalFrame(new crearlistaReproduccion());
    }//GEN-LAST:event_CrearListaActionPerformed


    private void RegistroCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistroCategoriaActionPerformed
        AgregarInternalFrame(new registroCategorias());
    }//GEN-LAST:event_RegistroCategoriaActionPerformed

    private void listarCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarCategoriasActionPerformed
        AgregarInternalFrame(new listarCategorias(this));
    }//GEN-LAST:event_listarCategoriasActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        AgregarInternalFrame(new listarUsuarios(this));
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    private void ModificarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarListaActionPerformed
        AgregarInternalFrame(new listaDeReproduccionFrame(this));
    }//GEN-LAST:event_ModificarListaActionPerformed


    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        AgregarInternalFrame(new listarVideos(this));
    }//GEN-LAST:event_jMenuItem2ActionPerformed


    private void ELBOTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ELBOTONActionPerformed
        // TODO add your handling code here:
       //CATEGORIA
        cate.AltaCategoria("Música");
        cate.AltaCategoria("Deporte");
        cate.AltaCategoria("Carnaval");
        cate.AltaCategoria("Noticias");
        cate.AltaCategoria("Entretenimiento");
        cate.AltaCategoria("Comida");
        cate.AltaCategoria("Videojuegos");
        cate.AltaCategoria("Ciencia y Tecnología");
        cate.AltaCategoria("ONG y activismo");
        cate.AltaCategoria("Gente y blogs");
        cate.AltaCategoria("Mascotas y animales");
        cate.AltaCategoria("Viajes y eventos");

        //USUARIOS
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/M/yyyy");
        try {
        user.AltaUsuario("hrubino", "123", "Horacio", "Rubino", "horacio.rubino@guambia.com.uy", dateformat.parse("25/02/1962") , "hrubino.jpg");
        user.AltaUsuario("mbusca", "123", "Martín", "Buscaglia", "Martin.bus@agadu.org.uy", dateformat.parse("14/06/1972") , "mbusca.jpg");
        user.AltaUsuario("hectorg", "123", "Héctor", "Guido", "hector.gui@elgalpon.org.uy",dateformat.parse("07/01/1954") , "");
        user.AltaUsuario("tabarec", "123", "Tabaré", "Cardozo", "tabare.car@agadu.org.uy", dateformat.parse("24/07/1971"), "tabarec.jpg");
        user.AltaUsuario("cachilas", "123", "Waldemar \"Cachila\"", "Silva", "Cachila.sil@c1080.org.uy", dateformat.parse("01/01/1947") , "cachilas.jpg");
        user.AltaUsuario("juliob", "123", "Julio", "Bocca", "juliobocca@sodre.com.uy", dateformat.parse("16/03/1967") , "");
        user.AltaUsuario("diegop", "123", "Diego", "Parodi", "diego@efectocine.com", dateformat.parse("01/01/1975") , "");
        user.AltaUsuario("kairoh", "123", "Kairo", "Herrera", "kairoher@pilsenrock.com.uy", dateformat.parse("25/04/1840") , "kairoh.jpg");
        user.AltaUsuario("robinh", "123", "Robin", "Henderson", "Robin.h@tinglesa.com.uy", dateformat.parse("03/08/1940") , "");
        user.AltaUsuario("marcelot", "123", "Marcelo", "Tinelli", "marcelot@ideasdelsur.com.ar", dateformat.parse("01/04/1960") , "");
        user.AltaUsuario("novick", "123", "Edgardo", "Novick", "edgardo@novick.com.uy", dateformat.parse("17/07/1952") , "");
        user.AltaUsuario("sergiop", "123", "Sergio", "Puglia", "puglia@alpanpan.com.uy", dateformat.parse("28/01/1950") , "");
        user.AltaUsuario("chino", "123", "Alvaro", "Recoba", "chino@trico.org.uy", dateformat.parse("17/03/1976") , "chino.jpg");
        user.AltaUsuario("tonyp", "123", "Antonio", "Pacheco", "eltony@manya.org.uy", dateformat.parse("14/02/1955") , "tonyp.jpg");
        user.AltaUsuario("nicoJ", "123", "Nicolás", "Jodal", "jodal@artech.com.uy", dateformat.parse("09/08/1960") , "");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       //CANALES
       user.AltaCanal("Canal Horacio", false, 1, "El canal Horacio es para publicar contenido divertido");
       user.AltaCanal("El bocha", false, 2, "Mi canal para colgar cosas");
       user.AltaCanal("Canal Héctor", false,3, "Canal HG");
       user.AltaCanal("Tabaré", false,4, "Mi música e ainda mais");
       user.AltaCanal("El Cachila", true,5, "Para juntar cosas");
       user.AltaCanal("Canal Julio", false,6, "Canal de JB");
       user.AltaCanal("Canal Diego", false,7, "Canal de DP");
       user.AltaCanal("Kairo música", false,8, "Videos de grandes canciones de hoy y siempre");
       user.AltaCanal("Canal Robin", false,9, "Henderson");
       user.AltaCanal("Tinelli total", false,10, "Todo lo que querías y más !");
       user.AltaCanal("Con la gente", false,11, "Preparando las elecciones");
       user.AltaCanal("Puglia invita", false,12, "Programas del ciclo y videos de cocina masterchef");
       user.AltaCanal("Chino Recoba", true,13, "Canal de goles con Nacional");
       user.AltaCanal("Tony Pacheco", true,14, "Todos los goles con Peñarol");
       user.AltaCanal("Desde Genexus", false,15, "Canal información C y T");
       //SEGUIDORES
       user.seguirUsuario("hrubino", "hectorg");
       user.seguirUsuario("hrubino", "diegop");
       user.seguirUsuario("mbusca", "tabarec");
       user.seguirUsuario("mbusca", "cachilas");
       user.seguirUsuario("mbusca", "kairoh");
       user.seguirUsuario("hectorg", "mbusca");
       user.seguirUsuario("hectorg", "juliob");
       user.seguirUsuario("tabarec", "hrubino");
       user.seguirUsuario("tabarec", "cachilas");
       user.seguirUsuario("cachilas", "hrubino");
       user.seguirUsuario("juliob", "mbusca");
       user.seguirUsuario("juliob", "diegop");
       user.seguirUsuario("diegop", "hectorg");
       user.seguirUsuario("kairoh", "sergiop");
       user.seguirUsuario("robinh", "hectorg");
       user.seguirUsuario("robinh", "juliob");
       user.seguirUsuario("robinh", "diegop");
       user.seguirUsuario("marcelot", "cachilas");
       user.seguirUsuario("marcelot", "juliob");
       user.seguirUsuario("marcelot", "kairoh");
       user.seguirUsuario("novick", "hrubino");
       user.seguirUsuario("novick", "tabarec");
       user.seguirUsuario("novick", "cachilas");
       user.seguirUsuario("sergiop", "mbusca");
       user.seguirUsuario("sergiop", "juliob");
       user.seguirUsuario("sergiop", "diegop");
       user.seguirUsuario("chino", "tonyp");
       user.seguirUsuario("tonyp", "sergiop");
       user.seguirUsuario("nicoJ", "diegop");
        //VIDEO
        vid.AltaVideo("Locura celeste", "3.04", "https://youtu.be/PAfbzKcePx0", "Una cancion de fubol", 4, "Música");
        vid.AltaVideo("Niño payaso", "4.18", "https://youtu.be/K-uEIUnyZPg", "la emocion explota", 4 , "Música");
        vid.AltaVideo("Sweet child'o mine", "5.02", "https://youtu.be/1w7OgIMMRc4", "cancion de los rolin",6 , "Música");
        vid.ModificarVideo(3, "", "", "", "", null, false, "Ninguna");
        vid.AltaVideo("Dancing in the Dark", "5.58", "https://youtu.be/129kuDCQtHs", "temaso para la noche de la nostalgia",8  , "Música");
        vid.ModificarVideo(4, "", "", "", "", null, false, "Ninguna");
        vid.AltaVideo("Thriller", "13.42", "https://youtu.be/sOnqjkJTMaA", "este medio raro con los nenes", 6 , "Música");
        vid.ModificarVideo(5, "", "", "", "", null, false, "Ninguna");
        vid.AltaVideo("100 años de FING", "6.26", "https://youtu.be/peGS4TBxSaI", "mucha emocion", 3 , "Noticias");
        vid.ModificarVideo(6, "", "", "", "", null, false, "Ninguna");
        vid.AltaVideo("50 años del InCo", "27.22", "https://youtu.be/GzOJSk4urlM", "la mitad que la fing jeje", 3 , "Noticias");
        vid.ModificarVideo(7, "", "", "", "", null, false, "Ninguna");
        vid.AltaVideo("Ingeniería de Muestra 2017", "1.00", "https://youtu.be/RnaYRA1k5j4", "muy buenos trabajos",3  , "Noticias");
        vid.ModificarVideo(8, "", "", "", "", null, false, "Ninguna");
        vid.AltaVideo("Etapa A contramano Liguilla", "57.15", "https://youtu.be/Es6GRMHXeCQ", "esta estuvo dificil", 5 , "Carnaval");
        vid.AltaVideo("Etapa Don Timoteo Liguilla", "51.38", "https://youtu.be/I_spHBU9ZsI", "esta estuvo facil", 5 , "Carnaval");
        vid.AltaVideo("Show de goles", "4.23", "https://youtu.be/g46w4_kD_lA", "tremendos goles variados", 6 , "Deporte");
        vid.ModificarVideo(11, "", "", "", "", null, false, "Ninguna");
        vid.AltaVideo("Pacheco goles más recordados", "5.48", "https://youtu.be/wlEd6-HsIxI", "tremendos goles de pacheco", 4 , "Deporte");
        vid.AltaVideo("Inauguración Estadio Peñarol", "207.26", "https://youtu.be/U6XPJ8Vz72A", "Presentacion de el nuevo estadio peñarol", 6 , "Deporte");
        vid.ModificarVideo(13, "", "", "", "", null, false, "Ninguna");
        vid.AltaVideo("Recoba 20 mejores goles", "13.36", "https://youtu.be/Gy3fZhWdLEQ", "tremendos goles de recoba", 5 , "Deporte");
        vid.AltaVideo("Entrevista a director CUTI", "5.39", "https://youtu.be/Eq5uBEzI6qs", "entrevista realizada el 13/7/1527", 15 , "Ciencia y Tecnología");
        vid.ModificarVideo(13, "", "", "", "", null, false, "Ninguna");
        vid.AltaVideo("Ventana al futuro Uruguay y déficit de ingenieros", "19.21", "https://youtu.be/zBR2pnASlQE", "Muy complicado este tema, hicimos un video para aclarar", 15 , "Ciencia y Tecnología");
        vid.ModificarVideo(13, "", "", "", "", null, false, "Ninguna");
        // lista de reproduccion
        //por defecto
        user.AltaListaDeReproduccionPorDefecto("Escuchar más tarde");
        user.AltaListaDeReproduccionPorDefecto("Deporte total"); 
        user.AltaListaDeReproduccionPorDefecto("Novedades generales");
        //particular //En categorias multiples pongo una que no tenga nada o ninguna, luego vemos
        user.AltaListaDeReproduccionParticular("Nostalgia", 8, false, "Música");
        user.AltaListaDeReproduccionParticular("De fiesta", 4, true, "Carnaval"); //MUS y CAR
        user.AltaListaDeReproduccionParticular("Novedades FING", 3, false, "Noticias");
        user.AltaListaDeReproduccionParticular("De todo un poco", 5, true, "Ninguna"); //MUS, DEP y CAR
        user.AltaListaDeReproduccionParticular("Noticias y CYT", 15, false, "Ciencia y Tecnología"); //NOT y CYT
        user.AltaListaDeReproduccionParticular("Solo deportes", 6, false, "Deporte");
        //videos en las listas
        user.AgregarVideoListaDeReproduccion(6, 8, "Sweet child'o mine", "Nostalgia");
        user.AgregarVideoListaDeReproduccion(8, 8, "Dancing in the Dark", "Nostalgia");
        user.AgregarVideoListaDeReproduccion(6, 8, "Thriller", "Nostalgia");
        user.AgregarVideoListaDeReproduccion(4, 4, "Locura celeste", "De fiesta");
        user.AgregarVideoListaDeReproduccion(4, 4, "Niño payaso", "De fiesta");
        user.AgregarVideoListaDeReproduccion(5, 4, "Etapa Don Timoteo Liguilla", "De fiesta");
        user.AgregarVideoListaDeReproduccion(3, 3, "100 años de FING", "Novedades FING");
        user.AgregarVideoListaDeReproduccion(3, 3, "50 años del InCo", "Novedades FING");
        user.AgregarVideoListaDeReproduccion(3, 3, "Ingeniería de Muestra 2017", "Novedades FING");
        user.AgregarVideoListaDeReproduccion(4, 5, "Locura celeste", "De todo un poco");
        user.AgregarVideoListaDeReproduccion(4, 5, "Niño payaso", "De todo un poco");
        user.AgregarVideoListaDeReproduccion(5, 5, "Etapa A contramano Liguilla", "De todo un poco");
        user.AgregarVideoListaDeReproduccion(5, 5, "Etapa Don Timoteo Liguilla", "De todo un poco");
        user.AgregarVideoListaDeReproduccion(6, 5, "Inauguración Estadio Peñarol", "De todo un poco");
        user.AgregarVideoListaDeReproduccion(3, 15, "Ingeniería de Muestra 2017", "Noticias y CYT");
        user.AgregarVideoListaDeReproduccion(15, 15, "Ventana al futuro Uruguay y déficit de ingenieros", "Noticias y CYT");
        user.AgregarVideoListaDeReproduccion(6, 6, "Show de goles", "Solo deportes");
        user.AgregarVideoListaDeReproduccion(6, 6, "Inauguración Estadio Peñarol", "Solo deportes");
        SimpleDateFormat dateformat2 = new SimpleDateFormat("dd/M/yyyy HH:mm");
        try {
            vid.ComentarVideo(15, 7, -1 ,"Fue un gran evento" , dateformat2.parse("05/12/17 14:35"));
            vid.ComentarVideo(1, 7, 1 ,"Para el próximo aniversario ofrezco vamo’ con los Momo" , dateformat2.parse("05/12/17 14:35"));
            vid.ComentarVideo(4, 7, 2 ,"Yo ofrezco a la banda tb" , dateformat2.parse("05/12/2017 14:35"));
            vid.ComentarVideo(15, 6, -1 ,"Felicitaciones FING!!!" , dateformat2.parse("05/12/2017 14:35"));
            vid.ComentarVideo(8, 8, -1 ,"Un gusto cubrir eventos como este." , dateformat2.parse("05/12/2017 14:35"));
            vid.ComentarVideo(8, 13, -1 ,"Peñarol peñarol!!!" , dateformat2.parse("05/12/2017 14:35"));
            vid.ComentarVideo(10, 3, -1 ,"Rock and Rolllll" , dateformat2.parse("05/12/2017 14:35"));
            vid.ComentarVideo(10, 4, -1 ,"Anoche explotó!!!" , dateformat2.parse("05/12/2017 14:35"));
            vid.ComentarVideo(10, 1, -1 ,"Me encanta este tema" , dateformat2.parse("05/12/2017 14:35"));
            vid.ComentarVideo(4, 1, 9 ,"Gracias Marce" , dateformat2.parse("05/12/2017 14:35"));
            JOptionPane.showMessageDialog(null,"Comentarios agregados");
        } catch (ParseException e) {
            e.printStackTrace();
        }        
        // Valoraciones
        vid.ValorarVideo(12, 7, false);
        vid.ValorarVideo(12, 8, true);
        vid.ValorarVideo(12, 11, true);
        vid.ValorarVideo(15, 1, false);
        vid.ValorarVideo(15, 7, true);
        vid.ValorarVideo(8, 7, true);
        vid.ValorarVideo(8, 13, true);
        vid.ValorarVideo(10, 1, true);
        vid.ValorarVideo(10, 4, true);
        JOptionPane.showMessageDialog(null,"Valoraciones agregadas");
        JOptionPane.showMessageDialog(null,"Datos cargados");
        ELBOTON.setVisible(false);
    }//GEN-LAST:event_ELBOTONActionPerformed

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }


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
    private javax.swing.JButton ELBOTON;
    private javax.swing.JDesktopPane Escritorio;
    private javax.swing.JMenuItem ModificarLista;
    private javax.swing.JMenuItem RegistrarUsuario;
    private javax.swing.JMenuItem RegistrarVideo;
    private javax.swing.JMenuItem RegistroCategoria;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem listarCategorias;
    private javax.swing.JLabel logoLabel;
    // End of variables declaration//GEN-END:variables
}
