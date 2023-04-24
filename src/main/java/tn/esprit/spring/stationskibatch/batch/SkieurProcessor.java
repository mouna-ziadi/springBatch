package tn.esprit.spring.stationskibatch.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import tn.esprit.spring.stationskibatch.entities.Skieur;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
public class SkieurProcessor implements ItemProcessor<Skieur, Skieur> {


	/*12. logique métier de notre job*/
	@Override
	public Skieur process(Skieur skieur) {


		// récupérer la date début d'abonnement relative à la date du jour
		LocalDate dateDebutAbonnement = LocalDate.now();
		// calculer par défaut la date fin pour un abonnement mensuel
		LocalDate dateFinAbonnement = LocalDate.now().plusMonths(1);
		// affecter par défaut le prix d'abonnement pour un abonnement mensuel
		Float prixAbon = new Float(60);
		Long numAbonnement = ThreadLocalRandom.current().nextLong(1000000); // numéro abonnement généré automatiquement

		// calculer la date fin  et affecter  le prix d'abonnement
		// pour les deux autres types d'abonnement


		switch (skieur.getAbonnement().getTypeAbon().name()) {
			case "ANNUEL":
				dateFinAbonnement = LocalDate.now().plusMonths(7);
				prixAbon = new Float(600);
				skieur.getAbonnement().setPrixAbon(prixAbon);

				break;
			case "SEMESTRIEL":
				dateFinAbonnement = LocalDate.now().plusYears(1).plusMonths(2);
				prixAbon = new Float(300);
				skieur.getAbonnement().setPrixAbon(prixAbon);
			default:
				break;
		}
		//TODO 6
		return skieur;
	}
}
