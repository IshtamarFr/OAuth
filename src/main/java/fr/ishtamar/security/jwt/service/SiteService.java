package fr.ishtamar.security.jwt.service;


import fr.ishtamar.security.jwt.entity.Site;
import fr.ishtamar.security.jwt.exceptionhandler.EntityNotFoundException;

public interface SiteService {
    public Site getSiteById(final Long id) throws EntityNotFoundException;
}
