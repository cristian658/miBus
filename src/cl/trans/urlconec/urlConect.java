/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.trans.urlconec;


import cl.trans.classes.Cook;
import java.io.IOException;
import org.apache.commons.httpclient.HttpClient;
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
    private String DOMAIN = "	http://web.simt.cl/simtweb/buscarAction.do?d=busquedaRapida&servicio=-1&destino=-1&paradero=-1&busqueda_rapida=jh&ingresar_paradero=pj388";
    private PostMethod method;
    private HttpClient client = new HttpClient();
    private Cook cook;
    
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
           
        cook = new Cook(this.client, this.method);
        cook.getCookie();
        method = new PostMethod(DOMAIN);
        method.addRequestHeader("Content-Type", TYPE);
          
        try {
            int returnCode = client.executeMethod(method);
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
}
