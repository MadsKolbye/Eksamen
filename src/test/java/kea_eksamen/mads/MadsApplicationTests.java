package kea_eksamen.mads;

import kea_eksamen.mads.models.Candidate;
import kea_eksamen.mads.models.Party;
import kea_eksamen.mads.repositories.CandidateRepository;
import kea_eksamen.mads.repositories.PartyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MadsApplicationTests {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    PartyRepository partyRepository;

    @Test
    public void createCandidate(){
        Candidate candidate = new Candidate();
        candidate.setCandidateId(20L);
        candidate.setCandidateName("Yahya Hassan");
        candidate.setAmountOfVotes(1337);
        candidate.setCandidateParty("Nihilistisk Folkeparti");
        candidateRepository.save(candidate);
        assertNotNull(candidateRepository.findById(20L));
    }

    @Test
    public void readAllCandidates(){
        List<Candidate> candidates = candidateRepository.findAll();
        assertThat(candidates).size().isGreaterThan(0);
    }

    @Test
    public void readCandidateById(){
        Candidate candidate = candidateRepository.findById(3L).get();
        assertEquals("Pia Nyring", candidate.getCandidateName());
    }

    @Test
    public void updateCandidate(){
        Candidate candidate = candidateRepository.findById(21L).get();
        candidate.setCandidateName("Mads Møller");
        candidateRepository.save(candidate);
        assertNotEquals("Martin", candidateRepository.findById(21L).get().getCandidateName());
    }

    @Test
    public void deleteCandidate(){
        candidateRepository.deleteById(21L);
        assertThat(candidateRepository.existsById(21L)).isFalse();
    }

    @Test
    public void readAllParties(){
        List<Party> parties = partyRepository.findAll();
        assertThat(parties).size().isGreaterThan(0);
    }

    @Test
    public void readPartyById(){
        Party party = partyRepository.findById(3L).get();
        assertEquals("Socialdemokratiet", party.getPartyName());
    }
}
