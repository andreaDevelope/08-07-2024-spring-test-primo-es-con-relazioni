package org.java.spring_test6;

import org.java.spring_test6.db.pojo.Prodotti;
import org.java.spring_test6.db.service.ProdottiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringTest6Application implements CommandLineRunner{
	@Autowired
	private ProdottiService prs;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringTest6Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Prodotti p1 = new Prodotti("go-pro", "telecamera ad alti frames per garantire ottime riprese in movimento", 235.78, 10, 268, "new");
		Prodotti p2 = new Prodotti("orsacchiotto", "adorabile peluche antiallergenico trattato con coloranti naturali", 8.50, 25, 1268, "new");
		Prodotti p3 = new Prodotti("quaderno", "quaderno formato A-4 Quadretti 10mm", 1.20, 20, 5204, "new");
		Prodotti p4 = new Prodotti("spazzola", "bla bla bla", 2.20, 25, 8204, "new");
		Prodotti p5 = new Prodotti("righello", "bla bla bla", 0.80, 10, 5204, "new");
		Prodotti p6 = new Prodotti("computer", "bla bla ba", 830.20, 40, 24, "used");
		

;
		prs.save(p1);
		prs.save(p2);
		prs.delete(p2);
		prs.save(p3);
		prs.save(p4);
		prs.save(p5);
		prs.save(p6);


		prs.getAll().forEach(System.out::println);
		System.out.println("-----------------------------------------------------------");
		System.out.println(prs.getByID(2));

		p3.setQuantity(0);

		try {
			p3.setQuantity(-3);
			System.out.println("quantità aggiornate");
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("Min Price: " + prs.getMinPrice());
        System.out.println("Max Price: " + prs.getMaxPrice());
        System.out.println("Avg Price: " + prs.getAvgPrice());
	}

}
