package com.example.t4td0.repository;

import com.example.t4td0.model.TarefaExcluida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaExcluidaRepository extends JpaRepository<TarefaExcluida, Long> {
}
