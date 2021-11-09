package com.taller.concesionario.dto;

//Clase para mostrar mensajes por pantalla en el cliente (front)
public class MensajeDto {

    private String mensaje;

    public MensajeDto(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
