package pl.pomykalskimateusz.evolution.repository.bet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends CrudRepository<BetEntity, Long> { }
