package psp.prototype.server;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;
import psp.prototype.tools.Configuration;
import psp.prototype.tools.Utils;
import psp.prototype.biz.Manager;
import psp.prototype.biz.ObjetoCompartido;

/**
 *
 * @author Luis Quintano
 */
public class Server {
    
    private static Socket costumer;
    
    public static void main(String[] args) {
        File filePublicKey = new File(Configuration.PUBLIC_KEY_FILE);
        PublicKey publicKey;
        ObjetoCompartido dog, cat;
        if (filePublicKey.exists()) {
            dog = new ObjetoCompartido(); 
            cat = new ObjetoCompartido();
            try (ServerSocket ss = new ServerSocket(Configuration.SEND_PORT)) {
                publicKey = Utils.getPublicKey(Configuration.PUBLIC_KEY_FILE); //obtiene clave publica
                do {
                    costumer = ss.accept();
                    Manager hs = new Manager(costumer, dog, cat, publicKey);
                    hs.start();
                } while (true);
            } catch (Exception ex) {
                System.err.println("ERROR en el Server: ");
                ex.printStackTrace();
            }
        } else {
            System.err.println("No has generado la clave Pública. Antes de arrancar el servidor, generala.");
        }
    }
}
