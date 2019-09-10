/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;

import java.util.List;
import javax.swing.JTable;
import logica.Categoria;
import logica.ListaDeReproduccion;
import logica.Video;
import logica.dt.CategoriaDt;

/**
 *
 * @author Xavel
 */
public interface IControladorCategoria {
    
    public void AltaCategoria(String nombre);
    public List<CategoriaDt> ListarCategorias();
    public CategoriaDt ConsultarCategorias(String c);
    public List<ListaDeReproduccion> obtenerListasCategoria(String nom);
    public List<Video> obtenerVideosCategoria(String nom);
    
    
}
