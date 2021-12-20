package kea_eksamen.mads.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "Parties")
@Entity
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long partyId;

    @Column
    private String partyName;

    @Column
    private String chairmanName;

    @Column
    private String percentageOfVotes;

    @Column
    private String partyImage;

}
