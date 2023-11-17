package fr.ishtamar.security.jwt.repository;

import fr.ishtamar.security.jwt.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
}
