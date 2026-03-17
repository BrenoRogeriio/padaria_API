package com.repositories;

import com.domains.Confeiteiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfeiteiroRepository extends JpaRepository<Confeiteiro, Long> {
}