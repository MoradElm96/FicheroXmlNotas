/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioxml;

/**
 *
 * @author alumno
 */
public class NotasMedias {
    
    
    String nombre;
    double nota;

    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

   

    @Override
    public String toString() {
        return "NotasMedias{" + "nombre=" + nombre + ", nota=" + nota + '}';
    }

    public NotasMedias(String nombre, double nota) {
        this.nombre = nombre;
        this.nota = nota;
    }

 
    
    
}
