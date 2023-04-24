package tn.esprit.spring.stationskibatch.configurations;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.spring.stationskibatch.batch.SkieurProcessor;
import tn.esprit.spring.stationskibatch.batch.SkieurReader;
import tn.esprit.spring.stationskibatch.batch.SkieurWriter;
import tn.esprit.spring.stationskibatch.entities.Skieur;

@Configuration
/*3. Activer le traitement par lot */
// TODO 1
@EnableBatchProcessing
@Slf4j
@AllArgsConstructor
public class SkieurBatchConfig {

	/*4. Création des variables de notre batch (nom job, nom step) */
	private static final String JOB_NAME = "listSkieurJob";
	private static final String STEP_NAME = "processingStep";

	private JobBuilderFactory jobBuilderFactory;

	private StepBuilderFactory stepBuilderFactory;


	/*5 Créer le bean associé au job et le lancer */
	@Bean
	public Job listSkieursJob(Step step1) {

		return jobBuilderFactory.get(JOB_NAME).start(step1).build();
	}

	/*6 Créer le step associé au job et le lancer */
	@Bean
	public Step skieurStep() {

		try {
			return stepBuilderFactory.get(STEP_NAME)
					.<Skieur, Skieur>chunk(3).reader(skieurItemReader().read())
					.processor(skieurItemProcessor()).writer(skieurItemWriter())
					.exceptionHandler((context, throwable) -> log.error("Skipping record on file. cause="+ throwable.getCause()))
					.build();
		} catch (Exception e) {
			log.error("End Batch Step");
			return stepBuilderFactory.get(STEP_NAME).chunk(2).build();
		}

	}
	



	/* 7. étape 1 (ItemReader) fait appel à la classe SkieurReader
	 * qui se charge de modifier la logique des données selon
	 * nos besoins métiers */

	@Bean
	public SkieurReader skieurItemReader() {
		return new SkieurReader();
	}
	/* 8. étape 2 (ItemProcessor) fait appel à la classe SkieurProcessor
	 * qui se charge de modifier la logique des données selon
	 * nos besoins métiers */
	@Bean
	public ItemProcessor<Skieur, Skieur> skieurItemProcessor() {
		return new SkieurProcessor();
	}

	
	/* 9. étape 3 (ItemWriter) fait appel à la classe SkieurWriter
	 * qui se charge de lancer le service de sauvegarde des 
	 * données liées à la partie skieur/abonnement dans la BD*/
	@Bean
	public ItemWriter<Skieur> skieurItemWriter() {
		return new SkieurWriter();
	}
}
