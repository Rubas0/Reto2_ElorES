package com.elorrieta.threads.mensajes;

import java.io.Serializable;

public class Mensaje implements Serializable {

    protected String tipoOperacion = null;
    protected String json = null;

    private static final long serialVersionUID = -1981368956803217148L;
    
    public Mensaje() {
        super();
    }

    public Mensaje(String tipoOperacion, String json) {
        this.tipoOperacion = tipoOperacion;
        this.json = json;
    }

	public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getJson() {
        return json;
    }
}
