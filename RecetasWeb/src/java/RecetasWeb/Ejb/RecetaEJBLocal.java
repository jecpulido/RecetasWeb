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
    
   
    
}
