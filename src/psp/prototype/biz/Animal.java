/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.prototype.biz;

import java.io.Serializable;

/**
 *
 * @author Luis Quintano
 */
public class Animal implements Serializable {
    
    private byte[] data;
    private String type;
    
    
    public Animal(byte[] bytesNombre, String tipo) {
        this.data = bytesNombre;
        this.type = tipo;
    }
    
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
