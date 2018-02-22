/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RecetasWeb.Ejb;

import RecetasWeb.Entity.Receta;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author Jorge
 */
@Local
public interface RecetaEJBLocal {
        
//    Metodo para agregar receta al array
    public void agregarReceta(Receta receta);    

//    Metodo para listar las recetas que se hn almacenado    
    public ArrayList<Receta> listarRecetas();
    
    
    // Metodo para buscar por nombre y/o tiempo
    public ArrayList<Receta> buscarNombreTiempo(String nombre, int tiempo);
    
    // Metodo para mostrar las recetas que tienen los ingredientes a buscar
    public ArrayList<Receta> buscarIngrediente(String ingredientes);
    
    // Método para obtener el detalle de una receta dado un nombre
    public Receta ObtenerRecetaModulo5(String name);
    
    // Método para convertir el arraylist de ingredientes en un string
    public String lisToStringIngredientes(Receta receta);
}
