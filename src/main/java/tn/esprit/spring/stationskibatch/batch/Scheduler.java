package tn.esprit.spring.stationskibatch.batch;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class Scheduler {

    private BatchLauncher batchLauncher;

    
    /*2. Lancer le batch (traitement de lots de données) grace au batch runner */
// TODO 3


    @Scheduled(cron= "0 */5 * * * *")
    public void perform() throws Exception {
        log.info("Batch programmé pour tourner toutes les 5 minutes");
        batchLauncher.run();
    }
}
