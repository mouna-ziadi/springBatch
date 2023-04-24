package tn.esprit.spring.stationskibatch.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tn.esprit.spring.stationskibatch.entities.Skieur;

@Slf4j
@Component
public class SkieurReader implements ItemReader<ItemReader<Skieur>> {



    /*4. Création des variables de notre batch (nom fichier, nom lecteur ) */
    private static final String FILE_NAME = "skieur.csv";
    private static final String READER_NAME = "skieurItemReader";

    /*5. Lister les champs que nous souhaitons parser dans le
     * fichier excel*/
    private String names = "numSkieur,nomS,prenomS,dateNaissance,ville,typeAbon";

    /*6. Définir la stratégie de délimitation des différents champs*/
    private String delimiter = ",";


    /*10. étape 1 (ItemReader) Créer le reader pour récupérer les données depuis
     * le fichier csv*/
    @Override
    public ItemReader<Skieur> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        FlatFileItemReader<Skieur> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(FILE_NAME));
        reader.setName(READER_NAME);
        reader.setLinesToSkip(1);
        /*11. récupérer les données ligne par ligne du fichier excel */
        reader.setLineMapper(skieurLineMapper());

        return reader;
    }

    /*11. récupérer les données ligne par ligne du fichier excel */
    public LineMapper<Skieur> skieurLineMapper() {

        final DefaultLineMapper<Skieur> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(delimiter);
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(names.split(delimiter));
        final SkieurFieldSetMapper fieldSetMapper = new SkieurFieldSetMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }
}
