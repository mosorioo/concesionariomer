package com.taller.concesionario.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class Input {

    @Min(1)
    @Max(10)
    private int numberBetweenOneAndTen;


    @NotBlank(message = "Name is mandatory")
    private String campo;
  
    @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
    private String ipAddress;
    

    public int getNumberBetweenOneAndTen() {
        return this.numberBetweenOneAndTen;
    }

    public void setNumberBetweenOneAndTen(int numberBetweenOneAndTen) {
        this.numberBetweenOneAndTen = numberBetweenOneAndTen;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCampo() {
        return this.campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }
    
  }