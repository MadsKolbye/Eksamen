package kea_eksamen.mads.controllers;

import kea_eksamen.mads.models.Party;
import kea_eksamen.mads.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Parties {

    @Autowired
    PartyRepository partyRepository;

    @GetMapping("/parties")
    public Iterable<Party> getParties(){
        return partyRepository.findAll();
    }

    @GetMapping("/parties/{party_id}")
    public Party getPartyById(@PathVariable Long party_id){
        return partyRepository.findById(party_id).get();
    }

}
