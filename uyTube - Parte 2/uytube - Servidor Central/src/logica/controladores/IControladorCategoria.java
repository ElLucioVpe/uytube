/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.controladores;

import java.util.List;
import logica.ListaDeReproduccion;
import logica.Video;
import logica.dt.CategoriaDt;
import logica.dt.ListaDeReproduccionDt;
import logica.dt.VideoDt;

/**
 *
 * @author Xavel
 */
public interface IControladorCategoria {
    
    public abstract void AltaCategoria(String nombre);
    public abstract List<CategoriaDt> ListarCategorias();
    public abstract CategoriaDt ConsultarCategorias(String c);
    public abstract List<ListaDeReproduccion> obtenerListasCategoria(String nom);
    public abstract List<Video> obtenerVideosCategoria(String nom);
    
    //Para la web
    public abstract List<ListaDeReproduccionDt> obtenerListasDtCategoria(String nom);
    public abstract List<VideoDt> obtenerVideosDtCategoria(String nom);
}
