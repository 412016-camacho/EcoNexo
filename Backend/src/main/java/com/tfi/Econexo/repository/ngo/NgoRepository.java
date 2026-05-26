package com.tfi.Econexo.repository.ngo;

import com.tfi.Econexo.model.ngo.Ngo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NgoRepository extends JpaRepository<Ngo, Long> {
}
