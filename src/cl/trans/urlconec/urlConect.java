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
import org.apache.commons.httpclient.params.HttpMethodParams;



/**
 *
 * @author Cristian Quezada
 */
public class urlConect {

    private String TYPE = "application/x-www-form-urlencoded";
    private String DOMAIN = "http://m.ibus.cl/index.jsp?";
    private String PARAM = "paradero=";
    private String PARAM2 = "&servicio=&boton.x=28&boton.y=12";
    private GetMethod method;
    private HttpClient client = new HttpClient();
    private Cook cook;
    private String paradero = "pj388";
    private ArrayList<paradero> paraderos = new ArrayList<paradero>();
    private paradero parad;
    private String nomParadero;
    private String errorCodec;
    
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
        
       client.getParams().setParameter(HttpMethodParams.USER_AGENT, "Mozilla/5.0 (Linux; U; Android 2.2.1; de-de; X2 Build/FRG83) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1"); 
        method = new GetMethod(DOMAIN+PARAM+paradero+PARAM2);
        System.out.println(DOMAIN+PARAM+paradero+PARAM2);
        
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
                System.out.println(readLine);
                
                
                if(readLine.contains("respuesta_servicio")){
                    readLine = br.readLine();
                    readLine = readLine.trim();
                    readLine = readLine.replace("</div>", "");
                    servicio = readLine;
                    //System.out.println(servicio);
                    
                }
                if(readLine.contains("<div class=\"textos\" id=\"respuesta_bus\">")){
                    readLine = br.readLine();
                    readLine = readLine.trim();
                    readLine = readLine.replace("</div>", "");
                    bus = readLine;
                    //System.out.println(bus);
                    

                }
                if(readLine.contains("<div class=\"textos\" id=\"respuesta_tiempo\">")){
                    readLine=readLine.trim();
                    readLine = br.readLine();
                    readLine = br.readLine();
                    readLine = br.readLine();
                    readLine=readLine.replace("Entre ", "");
                    readLine=readLine.replace("Menos de ", "");
                    readLine=readLine.replace("En menos de ", "");
                    readLine=readLine.replace("Y", "a");
                    readLine=readLine.replace("</div>", "");
                    tiempo = readLine;
                    //System.out.println(tiempo);
                    
                }
                if(readLine.contains("<div class=\"textos\" id=\"respuesta_distancia\">")){
                    readLine = br.readLine();
                    readLine=readLine.trim();
                    readLine=readLine.replace("</div>", "");
                    distancia = readLine;
                   // System.out.println(distancia);
                    
                }
                if(readLine.contains("<div id=\"nombre_paradero\">")){
                    readLine=readLine.trim();
                    readLine=readLine.replace("<div id=\"nombre_paradero\">", "");
                    readLine=readLine.replace("</div>", "");
                    readLine=readLine.replace("Paradero:", "");
                    nomParadero = readLine;
                    //System.out.println(nomParadero);
                    
                    
                    
                }
                if(readLine.contains("<div class=\"textos\" id=\"respuesta_nulo\">")){
                    readLine = br.readLine();
                    readLine=readLine.trim();
                    readLine=readLine.replace("<p>", "");
                    readLine=readLine.replace("</p>", "");
                    errorCodec = readLine;
                    //System.out.println(errorCodec);
                    
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
           // System.out.println("Me gusta " + paradero.getServicio());  
        }  
        
    }
    
    
}
