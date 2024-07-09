package org.java.spring_test6;

import java.util.List;

import org.java.spring_test6.db.pojo.Prodotti;
import org.java.spring_test6.db.pojo.Recensioni;
import org.java.spring_test6.db.service.ProdottiService;
import org.java.spring_test6.db.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringTest6Application implements CommandLineRunner{
	@Autowired
	private ProdottiService prs;

	@Autowired
	private ReviewService rs;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringTest6Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// testCRUDProdotti();
		// testMInMaxAvg();
		testCRUDRecensioni();
		System.out.println("\nEND");
	}



	
		
	public void testCRUDProdotti() {
		Prodotti p1 = new Prodotti("go-pro", "telecamera ad alti frames per garantire ottime riprese in movimento", 235.78, 10, 268, "new");
		Prodotti p2 = new Prodotti("orsacchiotto", "adorabile peluche antiallergenico trattato con coloranti naturali", 8.50, 25, 1268, "new");
		Prodotti p3 = new Prodotti("quaderno", "quaderno formato A-4 Quadretti 10mm", 1.20, 20, 5204, "new");
		Prodotti p4 = new Prodotti("spazzola", "bla bla bla", 2.20, 25, 8204, "new");
		Prodotti p5 = new Prodotti("righello", "bla bla bla", 1000.00, 10, 5204, "new");
		Prodotti p6 = new Prodotti("computer", "bla bla ba", 830.20, 40, 24, "used");

		// create
		prs.save(p1);
		prs.save(p2);
		prs.save(p3);
		prs.save(p4);
		prs.save(p5);
		prs.save(p6);

		// read
		prs.getAll().forEach(System.out::println);
		System.out.println("-----------------------------------------------------------");
		System.out.println(prs.getByID(2));

		p3.setQuantity(0);

		// testo un errore
		try {
			p3.setQuantity(-3);
			System.out.println("quantità aggiornate");
		} catch (Exception e) {
			System.out.println(e);
		}

		// delete
		prs.delete(p2);
	}

	public void testMInMaxAvg(){
		System.out.println("Min Price: " + prs.getMinPrice());
        System.out.println("Max Price: " + prs.getMaxPrice());
        System.out.println("Avg Price: " + prs.getAvgPrice());
	}

	public void testCRUDRecensioni(){

		Prodotti p1 = new Prodotti("go-pro", "telecamera ad alti frames per garantire ottime riprese in movimento", 235.78, 10, 268, "new");
		Prodotti p3 = new Prodotti("quaderno", "quaderno formato A-4 Quadretti 10mm", 1.20, 20, 5204, "new");
		Prodotti p4 = new Prodotti("spazzola", "bla bla bla", 2.20, 25, 8204, "new");

		try {
			Recensioni r1 = new Recensioni(5, "Prodotto ottimo", p1);
			Recensioni r2 = new Recensioni(4, "Un buon prodotto", p3);
			Recensioni r3 = new Recensioni(1, "Prodotto scadente", p1);
			Recensioni r4 = new Recensioni(4, "Davvero buono", p4);
			Recensioni r5 = new Recensioni(2, "Non ci simao", p3);
			Recensioni r6 = new Recensioni(1, "Deluso", p3);
			Recensioni r7 = new Recensioni(3, "Accettabile", p1);





			System.out.println(r1);
			System.out.println(r2);
			System.out.println(r3);
			System.out.println(r4);
			System.out.println(r5);
			System.out.println(r6);
			System.out.println(r7);


			rs.save(r1);
			rs.save(r2);
			rs.save(r3);
			rs.save(r4);
			rs.save(r5);
			rs.save(r6);
			rs.save(r7);



			List<Prodotti> prodotti = prs.getAllProductsWReviews();
			List<Recensioni> recensioni = rs.getAllReviews();

			System.out.println(prodotti);
			System.out.println(recensioni);

			for (Recensioni r : recensioni) {

				Prodotti p = r.getProdotto();

				System.out.println("prodotto recensito: " + p.getName() + " | voto: " + r.getRating() + " | commento: " + r.getComment());
			}

			System.out.println("------------------------------------------");

		} catch (Exception e) {

			System.out.println("Error in testRelations: " + e.getMessage());
		}
	}

}
