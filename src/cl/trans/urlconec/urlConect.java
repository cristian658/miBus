/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.trans.urlconec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Cristian Quezada
 */
public class urlConect {

    URL url;
    URLConnection con;
    BufferedReader in;
    String linea;
    private String  domain = "http://web.simt.cl/simtweb/buscarAction.do?";
    private String  path = "d=busquedaParadero&servicio=-1&destino=-1&paradero=-1&busqueda_rapida=PC616+C08&ingresar_paradero=pj388";
    private Date  expires;
    private static DateFormat expiresFormat = new SimpleDateFormat("E, dd-MMM-yyyy k:m:s 'GMT'");
    
    public void getPag() throws MalformedURLException, IOException{

        url = new URL(domain+path);
        con = url.openConnection();
        in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        while ((linea = in.readLine()) != null) {
            System.out.println(linea);
        }
    }
}
