package com.vieirarafael.propostafinapp.repositories;

import com.vieirarafael.propostafinapp.entities.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
