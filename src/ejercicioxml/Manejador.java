/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioxml;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author alumno
 */
//importar el org
public class Manejador extends DefaultHandler {
    
    Alumno alumno;
    ArrayList<Alumno> listaAlumnos = new ArrayList();
    
    StringBuilder sb = new StringBuilder();
    
    //crear override methods

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sb.append(ch, start, length); 
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      
        switch(qName){
            
            case "nombre":
                alumno.setNombre(sb.toString());
                
                
                break;
                
            case "nota1":
                
                alumno.setNota1(Integer.parseInt(sb.toString()));
                
                
                break;
                
            case "nota2":
                alumno.setNota2(Integer.parseInt(sb.toString()));
                break;
                
            case "proyecto":
                
                alumno.setProyecto(Integer.parseInt(sb.toString()));
                
                break;
                
            case "practica":
                
                alumno.setPractica(Integer.parseInt(sb.toString()));
                
                break;
            
            
        }
        
        
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       
        switch(qName){
             
            case "alumno":
                    
                alumno= new Alumno();
                listaAlumnos.add(alumno);
                
                alumno.setId(attributes.getValue("id"));
                   
                break;
                    
            case "nombre":
            case "nota1":
            case "nota2":
            case "proyecto":
            case "practica":
                
                sb.delete(0, sb.length());
                
                break;
        
        
        
        }
        
        
    }
    
    
    
    
    public ArrayList<Alumno> getLista(){
        return listaAlumnos;
    }
    
    
    
    
    
}
