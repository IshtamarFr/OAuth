package fr.ishtamar.security.jwt.repository;

import fr.ishtamar.security.jwt.entity.Truc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrucRepository extends JpaRepository<Truc, Long> {
}
