package tn.esprit.spring.stationskibatch.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;
import tn.esprit.spring.stationskibatch.entities.Skieur;
import java.time.ZoneId;

@Slf4j
public class SkieurFieldSetMapper implements FieldSetMapper<Skieur> {

	public Skieur mapFieldSet(FieldSet fieldSet) throws BindException {
		Skieur s = new Skieur(fieldSet.readLong(0),fieldSet.readString(1),fieldSet.readString(2),
				fieldSet.readDate(3).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),fieldSet.readString(4),
				fieldSet.readString(5));
		return  s;
	}

	
  
}