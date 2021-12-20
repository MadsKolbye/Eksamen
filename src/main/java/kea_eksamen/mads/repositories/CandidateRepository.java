package kea_eksamen.mads.repositories;

import kea_eksamen.mads.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
