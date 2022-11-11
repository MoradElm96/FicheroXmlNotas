/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioxml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author alumno
 */
public class EjercicioXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TransformerConfigurationException, TransformerException {

        double mediaAlumnos = 0;
        

        NotasMedias notamedia;

        ArrayList<NotasMedias> listaNotas = new ArrayList();

        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();

            File file = new File("notas.xml");

            Manejador m = new Manejador();

            sp.parse(file, m);

            double mediaNotas = 0;
            ArrayList<Alumno> lista = m.getLista();

            for (Alumno alumno : lista) {
                //castear
                mediaNotas = (double) (alumno.getNota1() + alumno.getNota2() + alumno.getPractica() + alumno.getProyecto()) / 4;

                mediaAlumnos += mediaNotas;

                notamedia = new NotasMedias(alumno.getNombre(), mediaNotas);

                listaNotas.add(notamedia);

            }

            mediaAlumnos = mediaAlumnos / lista.size();

            nuevoXml(listaNotas, mediaAlumnos);

        } catch (IOException ex) {
            Logger.getLogger(EjercicioXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(EjercicioXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(EjercicioXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 
    public static void nuevoXml(ArrayList<NotasMedias> lista, double mediaAlumnos) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //importar con org
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element root = doc.createElement("Notas");
        doc.appendChild(root);
        Attr notamedia = doc.createAttribute("MediaCurso");
        notamedia.setValue(Double.toString(mediaAlumnos));//para parsear porque recibe un string
        root.setAttributeNode(notamedia); //ojito, es node

        for (NotasMedias alumnos : lista) {

            Element alumno = doc.createElement("alumno");
            root.appendChild(alumno);

            Element nombre = doc.createElement("nombre");
            nombre.appendChild(doc.createTextNode(alumnos.getNombre()));
            alumno.appendChild(nombre);

            Element nota = doc.createElement("nota");
            nota.appendChild(doc.createTextNode(Double.toString(alumnos.getNota())));

            alumno.appendChild(nota);

        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource ds = new DOMSource(doc);
        
        StreamResult sr = new StreamResult(new File("notasCalculadas.xml"));
        StreamResult sr1 = new StreamResult(System.out);
        transformer.transform(ds,sr);

    }

}
