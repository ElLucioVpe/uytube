/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import javax.swing.JOptionPane;
import logica.controladores.IControladorUsuario;

/**
 *
 * @author Esteban
 */
public class ControladorUsuario implements IControladorUsuario {
    
    public ControladorUsuario() {
    }
    
    public Connection conectar() {
        Connection con = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:file:data/uytubedb;hsqldb.lock_file=false", "root", "root");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error de Conexion: "+e.getMessage());
        }
        return con;
    }
    
    @Override
    public void AltaUsuario(String nick, String nom, String apell, String mail, String fnac, String img) {
        try {
            Connection con = conectar();
            Statement st = con.createStatement();
                
            st.execute("INSERT INTO USUARIO VALUES ('"
                + nick + "','" + nom + "','" + apell + "','" + mail + "','"+fnac+"','" + img + "')"); 
            //la imagen asi nomas por ahora

            JOptionPane.showMessageDialog(null,"El usuario se ha registrado con exito");
            st.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Exception: "+e.getMessage());
        }
        
    }
}
