package kea_eksamen.mads.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "Candidates")
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long candidateId;

    @Column
    private String candidateName;

    @Column
    private String candidateParty;

    @Column
    private int amountOfVotes;

}
