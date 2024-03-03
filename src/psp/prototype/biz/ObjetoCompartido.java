/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psp.prototype.biz;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Luis Quintano
 */
public class ObjetoCompartido {
    private final Map<String, Integer> animals = new TreeMap<>();
    
    public synchronized void addVote(String decryptedData, Animal animal) {
        if (!this.animals.containsKey(decryptedData)) {
            this.animals.put(decryptedData, 1);
        } else {
                this.animals.put(decryptedData, this.animals.get(decryptedData) + 1);
        }
        System.out.println(animals);
        if (animal.getType().equals("P")) {
            System.out.println("--------Listado-de-Perros--------");
        } else {
            System.out.println("--------Listado-de-Gatos--------");
        }
        for (Map.Entry<String, Integer> entry : this.animals.entrySet()) {
            String name = entry.getKey();
            Integer votes = entry.getValue();
            System.out.println(name + ": " + votes + " votos");
        }
    }
}
