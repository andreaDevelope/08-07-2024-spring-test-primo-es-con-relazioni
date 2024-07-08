package org.java.spring_test6.db.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Prodotti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private double price;
    private int rebate;
    private int quantity;
    private String conditions;
    private double finalPrice;

    public Prodotti(){

    }

    public Prodotti(String name, String description, double price, int rebate, int quantity, String conditions){
        finalPrice = (price * rebate) / 100;
        setId(id);
        setName(name);
        setDescription(description);
        setPrice(price);
        setRebate(rebate);
        setQuantity(quantity);
        setConditions(conditions);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public double getPrice() {
        return price;
    }

    public int getRebate() {
        return rebate;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getConditions() {
        return conditions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        if (price < 0.1) {
            throw new IllegalArgumentException("questo valore deve essere positivo");
        }
        this.price = price;
    }

    public void setRebate(int rebate) throws IllegalArgumentException{
        if (rebate < 1) {
            throw new IllegalArgumentException("questo valore deve essere positivo");
        }
        this.rebate = rebate;
    }

    public void setQuantity(int quantity) throws IllegalArgumentException{

        if (quantity < 0) {
            throw new IllegalArgumentException("questo valore non può essere negativo");
        }else if(quantity == 0){
            System.out.println("questo prodotto è terminato");
        }
        this.quantity = quantity;
    }

    public void setConditions(String condition) {
        this.conditions = condition;
    }

    @Override
    public String toString() {
        return "[" + getId() + "]: name: " + getName() + " description: " + getDescription() + " price: " +
         getPrice() + " rebate: " + getRebate() + " quantity: " + getQuantity() + " condition: " + getConditions() + " prezzo scontato: " + getFinalPrice();
    }

    

}
