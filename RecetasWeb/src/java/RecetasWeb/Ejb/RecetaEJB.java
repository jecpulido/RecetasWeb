/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RecetasWeb.Ejb;

import javax.ejb.Stateless;
import RecetasWeb.Entity.Receta;
import java.util.ArrayList;

/**
 *
 * @author Jorge
 */
@Stateless
public class RecetaEJB implements RecetaEJBLocal {

    //ArrayList de tipo lista
    private ArrayList<Receta> recetas;

    //Constructor de la clase que inicializa la lista de recetas
    public RecetaEJB() {
        if (recetas == null) {
            recetas = new ArrayList<>();
        }
    }

    //Metodo el cual recibe un objeto tipo receta y se agrega a la lista
    @Override
    public void agregarReceta(Receta receta) {
        try {
            if (receta != null) {
                recetas.add(receta);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //Metodo que devuelve una lista de recetas
    @Override
    public ArrayList<Receta> listarRecetas() {
        try {
            return recetas;
        } catch (Exception e) {
            throw e;
        }
    }

    //Metodo busca por nombre y/o receta
    @Override
    public ArrayList<Receta> buscarNombreTiempo(String nombre, int tiempo) {
        ArrayList<Receta> recetaBusqueda = new ArrayList<>();
        for (Receta receta : recetas) {
            if ((nombre != null) && (tiempo > 0)) {
                if (receta.getNombre().contains(nombre) && receta.getTiempoCoccion() == tiempo) {
                    recetaBusqueda.add(receta);
                }
            } else if (nombre != null) {
                if (receta.getNombre().contains(nombre)) {
                    recetaBusqueda.add(receta);
                }
            } else if (tiempo > 0) {
                if (receta.getTiempoCoccion() == tiempo) {                    
                    recetaBusqueda.add(receta);
                }
            }

        }
        return recetaBusqueda;
    }

}
