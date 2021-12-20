package kea_eksamen.mads.controllers;


import kea_eksamen.mads.models.Candidate;
import kea_eksamen.mads.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Candidates {

    @Autowired
    CandidateRepository candidateRepository;

    @GetMapping("/candidates")
    public Iterable<Candidate> getCandidates(){
        return candidateRepository.findAll();
    }

    @GetMapping("/candidates/{candidate_id}")
    public Candidate getCandidateOnId(@PathVariable Long candidate_id){
        return candidateRepository.findById(candidate_id).get();
    }

    @PostMapping("/candidates")
    public Candidate createCandidate(@RequestBody Candidate candidateBody){
        return candidateRepository.save(candidateBody);
    }

    @PutMapping("/candidates/{candidate_id}")
    public Candidate updateCandidate(@PathVariable Long candidate_id, @RequestBody Candidate candidateBody){
        candidateBody.setCandidateId(candidate_id);
        return candidateRepository.save(candidateBody);
    }
    @PatchMapping("/candidates/{candidate_id}")
    public String patchCandidateById(@PathVariable Long candidate_id, @RequestBody Candidate candidateBody){
        return candidateRepository.findById(candidate_id).map(foundCandidate -> {
            if(candidateBody.getCandidateName() != null) foundCandidate.setCandidateName(candidateBody.getCandidateName());
            if(candidateBody.getCandidateParty() != null) foundCandidate.setCandidateParty(candidateBody.getCandidateParty());
            if(candidateBody.getAmountOfVotes() != 0) foundCandidate.setAmountOfVotes(candidateBody.getAmountOfVotes());
            candidateRepository.save(foundCandidate);
            return "Candidate updated";
        }).orElse("Candidate NOT updated");
    }

    @DeleteMapping("/candidates/{candidate_id}")
    public void deleteCandidate(@PathVariable Long candidate_id){
        candidateRepository.deleteById(candidate_id);
    }

}
