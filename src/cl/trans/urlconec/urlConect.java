/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.trans.urlconec;


import cl.trans.classes.Cook;
import cl.trans.classes.paradero;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;



/**
 *
 * @author Cristian Quezada
 */
public class urlConect {

    private String TYPE = "application/x-www-form-urlencoded";
    private String DOMAIN = "http://web.simt.cl/simtweb/buscarAction.do?d=busquedaParadero&servicio=-1&destino=-1&paradero=-1&busqueda_rapida=PC616%20C08&ingresar_paradero=";
    private GetMethod method;
    private HttpClient client = new HttpClient();
    private Cook cook;
    private String paradero = "pj388";
    private ArrayList<paradero> paraderos = new ArrayList<paradero>();
    private paradero parad;
    
    private BufferedReader br = null;
    private String readLine;
    
    public urlConect(String paradero) {
        this.paradero = paradero;
    }
   
    public String getParadero() {
        return paradero;
    }

    public void setParadero(String paradero) {
        this.paradero = paradero;
    }
    
    
    
    public GetMethod getPag(){
        
        
        method = new GetMethod(DOMAIN+paradero);
        cook = new Cook(client, method);
        cook.getCookie();
        method.addRequestHeader("Content-Type", TYPE);

        try {
            int returnCode = client.executeMethod(method);
            //System.out.println(returnCode);
           
        } catch (HttpException e) {
            //Logger.getLogger(urlConect.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            //Logger.getLogger(urlConect.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return method;
       
    }
    
    public void getParaderos(GetMethod method) {
        
        String servicio = "";
        String bus = "";
        String tiempo = "";
        String distancia = "";
        
        try {
             br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
            while ((readLine = br.readLine()) != null) {
               // System.out.println(readLine);
                
                
                if(readLine.contains("<div class=\"texto_respuesta\" id=\"servicio_respuesta_solo_paradero\">")){
                    readLine=readLine.trim();
                    readLine=readLine.replace("<div class=\"texto_respuesta\" id=\"servicio_respuesta_solo_paradero\">", "");
                    readLine=readLine.replace("</div>", "");
                    servicio = readLine;
                    //System.out.println(readLine);
                }
                if(readLine.contains("<div class=\"texto_respuesta\" id=\"bus_respuesta_solo_paradero\">")){
                    readLine=readLine.trim();
                    readLine=readLine.replace("<div class=\"texto_respuesta\" id=\"bus_respuesta_solo_paradero\">", "");
                    readLine=readLine.replace("</div>", "");
                    bus = readLine;
                    //System.out.println(readLine);
                }
                if(readLine.contains("<div class=\"texto_respuesta\" id=\"tiempo_respuesta_solo_paradero\">")){
                    readLine=readLine.trim();
                    readLine=readLine.replace("<div class=\"texto_respuesta\" id=\"tiempo_respuesta_solo_paradero\">", "");
                    readLine=readLine.replace("</div>", "");
                    tiempo = readLine;
                    //System.out.println(readLine);
                }
                if(readLine.contains("<div class=\"texto_respuesta\" id=\"distancia_respuesta_solo_paradero\">")){
                    readLine=readLine.trim();
                    readLine=readLine.replace("<div class=\"texto_respuesta\" id=\"distancia_respuesta_solo_paradero\">", "");
                    readLine=readLine.replace("</div>", "");
                    distancia = readLine;
                    //System.out.println(readLine);
                }
                
                if(servicio != "" && bus != "" && tiempo != "" && distancia != "") {
                    
                    parad = new paradero(servicio, bus, tiempo, distancia);
                    //System.out.println("Me gusta " + parad.getServicio()); 
                    paraderos.add(parad); 
                    servicio = "";
                    bus = "";
                    tiempo = "";
                    distancia = "";
                    
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(urlConect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (paradero paradero : paraderos)  {
            System.out.println("Me gusta " + paradero.getServicio());  
        }  
        
    }
    
    
}
