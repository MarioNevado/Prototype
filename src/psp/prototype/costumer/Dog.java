package psp.prototype.costumer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import java.io.*;
import java.net.Socket;
import java.security.PrivateKey;
import java.util.Scanner;
import psp.prototype.tools.Configuration;
import psp.prototype.tools.Utils;
import psp.prototype.biz.Animal;

/**
 *
 * @author Luis Quintano
 */
public class Dog {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name;
        Animal uva;
        File file = new File(Configuration.PRIVATE_KEY_FILE);
        PrivateKey privateKey;
        ObjectOutputStream oos = null;
        if (file.exists()) {
            try (Socket socket = new Socket(Configuration.HOST, Configuration.SEND_PORT)) {
                privateKey = Utils.getPrivateKey(Configuration.PRIVATE_KEY_FILE);  //obtener clave privada
                System.out.println("Introducir * para salir");
                do {
                    System.out.print("Introducir voto: ");
                    name = sc.nextLine();
                    byte[] encryptedData = Utils.encryptDataPrivateKey(name.getBytes(), privateKey); 
                    if (!name.equals("*")) {
                        uva = new Animal(encryptedData, "P");
                    } else {
                        uva = new Animal(encryptedData, null);
                    }
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(uva);
                } while (!name.equals("*"));
            } catch (Exception ex) {
                System.err.println("Servidor caído");
            } finally {
                if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException ex) {
                        System.err.println("ERROR al cerrar el ObjectOutputStream: ");
                    }
                }
            }
        } else {
                System.err.println("No has generado la clave Privada");
        }
    }
}
