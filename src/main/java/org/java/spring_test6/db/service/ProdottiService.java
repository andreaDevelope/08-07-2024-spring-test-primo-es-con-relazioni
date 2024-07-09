package org.java.spring_test6.db.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.spring_test6.db.pojo.Prodotti;
import org.java.spring_test6.db.pojo.Recensioni;
import org.java.spring_test6.db.repo.ProdottiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ProdottiService {

    @Autowired
    private ProdottiRepo pr;

    @Autowired
    private ReviewService rs;

    // PER STAMPARE TUTTE LE RECENSIONI ASSOCIATE AL PRODOTTO
    @Transactional
    public List<Prodotti> getAllProductsWReviews() {
        List<Prodotti> prodotti = pr.findAll();

        for (Prodotti prodotto : prodotti) {
            Hibernate.initialize(prodotto.getRecensioni());
        }

        return prodotti;
    }

    // PER OTTENERE TUTTE LE RECENSIONI ASSOCIATE AL PRODOTTO 
    @Transactional
    public Optional<Prodotti> getRecensioniProdotto(int id) {
        Optional<Prodotti> optProdotti = getByID(id);

        if (optProdotti.isEmpty())
            return Optional.empty();

        Hibernate.initialize(optProdotti.get().getRecensioni());

        return optProdotti;
    }

    // PER ELIMINARE IL PRODOTTO E TUTTE LE RECENSIONI ASSOCIATE
    @Transactional
    public void deleteProductAndReviews(int productId) {
        Optional<Prodotti> optProdotti = getByID(productId);
        if (optProdotti.isPresent()) {
            Prodotti prodotto = optProdotti.get();
            for (Recensioni recensione : prodotto.getRecensioni()) {
                rs.delete(recensione);
            }
            pr.delete(prodotto);
        }
    }

    public List<Prodotti> getAll(){
        return pr.findAll();
    }

    public Optional<Prodotti> getByID(int id){
        return pr.findById(id);
    }

    public void save(Prodotti prodotto){
        pr.save(prodotto);
    }

    public void delete(Prodotti prodotto){
        pr.delete(prodotto);
    }

    // metodi jpa per max, min, e media
    public double getMinPrice() {
        return pr.findMinPrice();
    }

    public double getMaxPrice() {
        return pr.findMaxPrice();
    }

    public double getAvgPrice() {
        return pr.findAvgPrice();
    }
}
