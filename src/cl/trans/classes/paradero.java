/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.trans.classes;

/**
 *
 * @author Cristian Quezada
 */
public class paradero {

    private String servicio;
    private String bus;
    private String tiempo;
    private String distancia;

    public paradero(String servicio, String bus, String tiempo, String distancia){
        this.servicio = servicio;
        this.bus = bus;
        this.tiempo = tiempo;
        this.distancia = distancia;
    }

    public String getServicio(){
        return servicio;
    }

    public void setServicio(String servicio){
        this.servicio = servicio;
    }

    public String getBus(){
        return bus;
    }

    public void setBus(String bus){
        this.bus = bus;
    }

    public String getTiempo(){
        return tiempo;
    }

    public void setTiemppo(String tiempo){
        this.tiempo = tiempo;
    }

    public String getDistancia(){
        return tiempo;
    }
    
    public void setDistancia(String distancia){
        this.distancia = distancia;
    }
}
