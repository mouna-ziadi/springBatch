package tn.esprit.spring.stationskibatch.batch;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.esprit.spring.stationskibatch.entities.Skieur;
import tn.esprit.spring.stationskibatch.repositories.SkieurRepository;
import java.util.List;

@Slf4j
@Component
public class SkieurWriter implements ItemWriter<Skieur> {

   @Autowired
    SkieurRepository skieurRepository;

    /* 13. écrire nos données dans la base de données*/
    public void write(List<? extends Skieur> skieurs) {
        log.info("dans cette étape, nous pourrons stocker nos informations" +
                "dans une autre base de données, un fichier externe ou la meme" +
                " base de données si nous le souhaitons");
      // TODO 8
        skieurRepository.saveAll(skieurs);
    }
}