/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author rcerrato
 */
public class Libreria {
    public static Libro libros [] = new Libro[4];

    public static void inicio(){
        String nombres [] = {"Arte","Musica","Deportes","Yoga"};
        double precios [] = {12,23,45,56};
        for(int i=0;i<libros.length;i++){ 
            libros[i]=new Libro(nombres[i],precios[i]);
        }
    }
    public static Libro getLibro(String nombre){
        for(int i=0;i<libros.length;i++){
            if(nombre.equals(libros[i].getNombre()))
                return libros[i];
        }
        return null;
    }
    

}

