/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.prototype.biz;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.security.PublicKey;
import java.util.Arrays;
import psp.prototype.tools.Utils;

/**
 *
 * @author Luis Quintano
 */
public class Manager extends Thread {
    private Socket costumer;
    private ObjetoCompartido dog, cat;
    private PublicKey publicKey;


    //constructor
    public Manager(Socket costumer, ObjetoCompartido dog, ObjetoCompartido cat, PublicKey publicKey) {
        this.costumer = costumer;
        this.dog = dog;
        this.cat = cat;
        this.publicKey = publicKey;
    }


    //metodo Run
    @Override
    public void run() {
        Animal animal;
        String data;
        ObjectInputStream ois = null;
        try {
            do {
                ois = new ObjectInputStream(costumer.getInputStream());  
                animal = (Animal) ois.readObject();  
                byte[] decryptedData = Utils.decryptDataPublicKey(animal.getData(), this.publicKey);
                data = new String(decryptedData);
                if (!data.equals("*")) {
                    if (animal.getType().equals("P")) {
                        System.out.print("mapa de perros: ");
                        this.dog.addVote(data, animal);
                    } else if (animal.getType().equals("G")) {
                        System.out.print("mapa de gatos: ");
                        this.cat.addVote(data, animal);
                    }
                }
            } while (!data.equals("*"));
            System.out.println("Cierre controlado del cliente");
        } catch (Exception e) {
            System.err.println("Cierre abrupto del cliente");
        } finally {
            if (this.costumer != null) {
                try {
                    this.costumer.close();
                } catch (IOException ex) {
                    System.err.println("ERROR al cerrar el Socket del Cliente");
                }
            }

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.err.println("ERROR al cerrar el ObjectOutputStream");
                }
            }
        }
    }
}
