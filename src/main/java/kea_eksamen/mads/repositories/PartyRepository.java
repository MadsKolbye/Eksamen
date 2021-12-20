package kea_eksamen.mads.repositories;

import kea_eksamen.mads.models.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party, Long> {
}
