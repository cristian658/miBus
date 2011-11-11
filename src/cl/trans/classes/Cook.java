/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.trans.classes;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
/**
 *
 * @author Cristian Quezada
 */
public class Cook {
    
    private HttpClient client;
    private PostMethod method;
    
    public Cook(HttpClient client,PostMethod method){
        
        this.client = client;
        this.method = method;
        
    }
    
    public void getCookie(){

		try{
			this.client.executeMethod(this.method);
			Cookie[] cookies = (Cookie[]) this.client.getState().getCookies();
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				System.err.println(
						"Cookie: " + cookie.getName() +
						", Value: " + cookie.getValue() +
						", IsPersistent?: " + cookie.isPersistent() +
						", Expiry Date: " + cookie.getExpiryDate() +
						", Comment: " + cookie.getComment());
			}
		} catch(Exception e) {
			System.err.println(e);
		} finally {
			method.releaseConnection();
		}
	}
    
}
