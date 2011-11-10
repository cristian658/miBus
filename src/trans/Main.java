/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trans;

import cl.trans.urlconec.urlConect;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author Cristian Quezada
 */
public class Main {

    static urlConect url = new urlConect();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        // TODO code application logic here

        url.getPag();
        
    }

}
