/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.trans.main;

import cl.trans.urlconec.urlConect;
import org.apache.commons.httpclient.methods.GetMethod;
/**
 *
 * @author cristian
 */
public class main {
    
     static urlConect url;
     static GetMethod method;
     
     
    
    public static void main(String[] args) {
        url = new urlConect("pj388");
        method = url.getPag();
        
        url.getParaderos(method);
       
        
        
    }
    
}
