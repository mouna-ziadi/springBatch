package tn.esprit.spring.stationskibatch.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table( name = "Skieur")
public class Skieur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idSkieur")
    private Integer idSkieur; // Clé primaire
    private Long numSkieur;
    private String nomS;
    private String prenomS;
    private LocalDate dateNaissance;
    private String ville;

    @Transient
    private String typeAbon;
    @ManyToMany
    private Set<Piste> pistes;
    @OneToMany(mappedBy = "skieur",cascade = CascadeType.ALL)
    private Set<Inscription> inscriptions;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Abonnement abonnement;

    public Skieur(Long numSkieur, String nomS, String prenomS, LocalDate dateNaissance, String ville,String typeAbon) {
        this.numSkieur = numSkieur;
        this.nomS = nomS;
        this.prenomS = prenomS;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.typeAbon=typeAbon;
    }


}
