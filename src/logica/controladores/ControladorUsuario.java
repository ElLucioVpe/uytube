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
    
    @Override
    public void AltaUsuario(String nick, String nom, String apell, String mail, Date fnac, String img) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby:uyTubeDerby");
            Statement st = con.createStatement();

            st.execute("INSERT INTO USUARIO VALUES ('"
                + nick + "','" + nom + "','" + apell + "','" + mail + "','"+ fnac + "','" + img + "')"); 
            //la imagen asi nomas por ahora

            st.close();
            con.close();        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Exception: "+e.getMessage());
        }
    }
}
