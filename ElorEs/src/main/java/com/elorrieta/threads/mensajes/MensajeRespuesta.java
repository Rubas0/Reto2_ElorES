package com.elorrieta.threads.mensajes;

import java.io.Serializable;

public class MensajeRespuesta implements Serializable {

    private static final long serialVersionUID = -1981368956803217148L;

    protected String tipoOperacion = null;
    protected String json = null;

    public MensajeRespuesta() {
        super();
    }

    public MensajeRespuesta(String tipoOperacion, String json) {
        this.tipoOperacion = tipoOperacion;
        this.json = json;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

}
