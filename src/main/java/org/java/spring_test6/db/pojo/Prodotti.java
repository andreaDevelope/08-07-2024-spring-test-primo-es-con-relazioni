package org.java.spring_test6.db.pojo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Prodotti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 64, nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String description;

    private double price;
    private int rebate;
    private int quantity;

    @Column(length = 32, nullable = false)
    private String conditions;

    private double finalPrice;

    @OneToMany(mappedBy = "prodotto")
    private List<Recensioni> recensioni;

    public Prodotti(){

    }

    public Prodotti(String name, String description, double price, int rebate, int quantity, String conditions){
        setId(id);
        setName(name);
        setDescription(description);
        setPrice(price);
        setRebate(rebate);
        setQuantity(quantity);
        setConditions(conditions);
        calculateFinalPrice();
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

    public List<Recensioni> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensioni> r) {
        this.recensioni = r;
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

    private void calculateFinalPrice() {
        if (price >= 0 && rebate >= 0) {
            finalPrice = price * (1 - rebate / 100.0);
        }
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
