/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RecetasWeb.Mb;

import RecetasWeb.Ejb.RecetaEJBLocal;
import RecetasWeb.Entity.Receta;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Jorge
 */
@Named(value = "recetaMb")
@RequestScoped
public class RecetaMb {

    /*
    *Objeto EJB
    *Permite acceder a los metodos sobre la lista de recetas
     */
    @EJB
    private RecetaEJBLocal recetaEJB;

    /*
    *Objeto de receta
     */
    private Receta objReceta;

    /*
    *Descripcion de Ingredientes
     */
    private String ingredientes;

    /*
    *Array de tipo Receta
     */
    private ArrayList<Receta> alRecetas;

    /*
    *Metodo init del ManageBean
     */
    @PostConstruct
    public void init() {
        if (objReceta == null) {
            objReceta = new Receta();
            alRecetas = new ArrayList<>();
            alRecetas = recetaEJB.listarRecetas();
        }
    }

    /*
    *Metodo Get de alRecetas
     */
    public ArrayList<Receta> getAlRecetas() {
        return alRecetas;
    }

    /*
    *Metodo Set de alRecetas
    */
    public void setAlRecetas(ArrayList<Receta> alRecetas) {
        this.alRecetas = alRecetas;
    }

    /*
    *Metodo Get de objReceta
    */
    public Receta getObjReceta() {
        return objReceta;
    }

    /*
    *Metodo Set de objReceta
    */
    public void setObjReceta(Receta objReceta) {
        this.objReceta = objReceta;
    }

    /*
    *Metodo get de ingredientes
     */
    public String getIngredientes() {
        return ingredientes;
    }

    /*
    Metodo set de ingredientes
     */
    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    /*
    * Metodo agregaReceta
    * Valida la informacion que se captura en el campo ingredientes,
    * la cual se convierte en un array y se asigna a la propiedad de receta
    */
    public void agregarReceta() {
        try {
            if (objReceta != null) {
                objReceta.setIngredientes(ingredientes.split(","));
                for (String ingre : objReceta.getIngredientes()) {
                    ingre = ingre.trim();
                }
                recetaEJB.agregarReceta(objReceta);
                objReceta = null;
                ingredientes = "";
                alRecetas = recetaEJB.listarRecetas();
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }

    }

}