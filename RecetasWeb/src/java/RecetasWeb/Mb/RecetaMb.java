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
    Array de tipo Receta
    */
    private ArrayList<Receta> Recetav;
    /*
    Nombre de la receta
    */
    private String nombreReceta;
    /*
    Array de los ingredientes
    */
    private ArrayList<String> listaIngredientes;
    /*
    Descripción de la receta
    */
    private String descripcion;
    /*
    Timpo de cocción de la receta
    */
    private int tiempoCoccion;
    /*
    *Metodo init del ManageBean
     */
    @PostConstruct
    public void init() {
        if (objReceta == null) {
            objReceta = new Receta();
            alRecetas = new ArrayList<>();
            alRecetas = recetaEJB.listarRecetas();
            Recetav = new ArrayList<>();
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
    Metodo Get de Recetav
    */
    public ArrayList<Receta> getRecetav() {
        return Recetav;
    }
    
    /*
    Metodo Set de Recetav
    */
    public void setRecetav(ArrayList<Receta> Recetav) {
        this.Recetav = Recetav;
    }

    /*
    * Metodo agregaReceta
    * Valida la informacion que se captura en el campo ingredientes,
    * la cual se convierte en un array y se asigna a la propiedad de receta
     */
    public void agregarReceta() {
        try {
            if (objReceta != null) {
                //Se asigna array de ingredientes a la propiedad
                objReceta.setIngredientes(obtenerIngredientes(ingredientes));
                //Se guarda receta
                recetaEJB.agregarReceta(objReceta);
                objReceta = null;
                ingredientes = "";
                alRecetas = recetaEJB.listarRecetas();
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }

    }

    /*
    * Obtiene los ingredientes que el usuario digito 
    * devuelve un arraylist con todos los ingredientes
     */
    private ArrayList obtenerIngredientes(String cadenaIngredientes) {
        ArrayList<String> ArrayIngre = new ArrayList<>();
        String[] ArreloIngre = cadenaIngredientes.split(",");
        for (String newIngre : ArreloIngre) {
            ArrayIngre.add(newIngre.trim());
        }
        return ArrayIngre;
    }

    
    
    //Lista de recetas encntradas en busqueda
    private ArrayList<Receta> recetaBusqueda;

    //Metodo Get de recetaBusqueda
    public ArrayList<Receta> getRecetaBusqueda() {
        return recetaBusqueda;
    }

    //Metodo Set de recetaBusqueda
    public void setRecetaBusqueda(ArrayList<Receta> recetaBusqueda) {
        this.recetaBusqueda = recetaBusqueda;
    }   
    
    /*
    *Metodo para la consulta de recetas por nombre o por tiempo
    * devuelve un array de recetas
     */
    public ArrayList buscarNombreTiempo() {
        if ((objReceta.getNombre() != null) || (objReceta.getTiempoCoccion() > 0)) {
            recetaBusqueda = new ArrayList<>();
            recetaBusqueda = recetaEJB.buscarNombreTiempo(objReceta.getNombre(), objReceta.getTiempoCoccion());
            objReceta = null;
            return recetaBusqueda;
        }
        return null;
    }
    
    public ArrayList<Receta> buscarIngrediente(String ingredientes) {

        ArrayList<String> ListaIn = obtenerIngredientes(ingredientes);
        for (int cont = 0; cont < alRecetas.size(); cont++) {
            int conta = 0;
            for (int i = 0; i < ListaIn.size(); i++) {
                for (int j = 0; j <alRecetas.get(cont).getIngredientes().size(); j++) {
                    if (alRecetas.get(cont).getIngredientes().get(j).equals(ListaIn.get(i))) {
                        conta++;
                    }

                }
                if (conta == ListaIn.size()) {
                    Recetav.add(alRecetas.get(cont));
                }
                else if(conta <ListaIn.size()){
                Recetav.isEmpty();
                }
            }
        }
        return Recetav;
    }
    
     public void buscarRecetaModuloSebastian5() {
        try {
            Receta recetaObtenida;
            recetaObtenida = this.recetaEJB.ObtenerRecetaModulo5(this.objReceta.getNombre());
            this.nombreReceta = recetaObtenida.getNombre();
            this.descripcion = recetaObtenida.getDescripcion();
            this.tiempoCoccion = recetaObtenida.getTiempoCoccion();
            this.ingredientes = this.listToStringIngredientes(recetaObtenida);
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    private String listToStringIngredientes(Receta receta) {
        return this.recetaEJB.lisToStringIngredientes(receta);
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public ArrayList<String> getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(ArrayList<String> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTiempoCoccion() {
        return tiempoCoccion;
    }

    public void setTiempoCoccion(int tiempoCoccion) {
        this.tiempoCoccion = tiempoCoccion;
    }

}
