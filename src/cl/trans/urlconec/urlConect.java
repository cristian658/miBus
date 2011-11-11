/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.trans.urlconec;


import cl.trans.classes.Cook;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;



/**
 *
 * @author Cristian Quezada
 */
public class urlConect {

    private String AGENT = "Mozilla/5.0 (Windows; U; Windows NT 6.1; es-ES; rv:1.9.2.16) Gecko/20110319 Firefox/3.6.16";
    private String POWERED="Servlet 2.4; JBoss-4.2.2.GA (build: SVNTag=JBoss_4_2_2_GA date=200710221139)/Tomcat-5.5";
    private String TYPE = "application/x-www-form-urlencoded ";
    private String DOMAIN = "http://web.simt.cl/simtweb/buscarAction.do?d=busquedaRapida&servicio=-1&destino=-1&paradero=-1&busqueda_rapida=jh&ingresar_paradero=pj388";
    private PostMethod method;
    private HttpClient client = new HttpClient();
    private Cook cook;
    private BufferedReader br = null;
    private String readLine;
    
    public String getAgent(){
        return AGENT;
    }

    public String getPowered() {
        return POWERED;
    }

    public String getType() {
        return TYPE;
    }

    public void setAgent(String AGENT) {
        this.AGENT = AGENT;
    }

    public void setPowered(String POWERED) {
        this.POWERED = POWERED;
    }

    public void setType(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getDomain() {
        return DOMAIN;
    }

    public void setDomain(String DOMAIN) {
        this.DOMAIN = DOMAIN;
    }

    public void getPag(){

        method = new PostMethod(DOMAIN);
        //cook = new Cook(client, method);
        //cook.getCookie();
        method.addRequestHeader("Content-Type", TYPE);
        try {
            int returnCode = client.executeMethod(method);
            System.out.println(returnCode);
            br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
        } catch (HttpException e) {
            //Logger.getLogger(urlConect.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            //Logger.getLogger(urlConect.class.getName()).log(Level.SEVERE, null, e);
        }
        try {
            while ((readLine = br.readLine()) != null) {
                System.out.println(readLine);
            }
        } catch (IOException ex) {
            //Logger.getLogger(urlConect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
