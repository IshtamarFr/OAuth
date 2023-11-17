package fr.ishtamar.security.jwt.service.impl;

import fr.ishtamar.security.jwt.entity.Site;
import fr.ishtamar.security.jwt.exceptionhandler.EntityNotFoundException;
import fr.ishtamar.security.jwt.repository.SiteRepository;
import fr.ishtamar.security.jwt.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl implements SiteService {
    @Autowired
    private SiteRepository repository;

    public List<Site> getAllSites() {
        return repository.findAll();
    }

    @Override
    public Site getSiteById(final Long id) throws EntityNotFoundException {
        return repository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(Site.class,"id",id.toString()));
    }
}
