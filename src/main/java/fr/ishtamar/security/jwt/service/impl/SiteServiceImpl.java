package fr.ishtamar.security.jwt.service.impl;

import fr.ishtamar.security.jwt.entity.Site;
import fr.ishtamar.security.jwt.exceptionhandler.EntityNotFoundException;
import fr.ishtamar.security.jwt.repository.SiteRepository;
import fr.ishtamar.security.jwt.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class SiteServiceImpl implements SiteService {
    @Autowired
    private SiteRepository repository;

    public List<Site> getAllSites() {
        return repository.findAll();
    }

    private final static List<String> alphabet= Arrays.asList("0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F");

    @Override
    public Site getSiteById(final Long id) throws EntityNotFoundException {
        return repository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(Site.class,"id",id.toString()));
    }

    @Override
    public StringBuilder generateSecret() {
        int len= alphabet.size();
        Random r=new Random();
        StringBuilder secret= new StringBuilder();

        for(int i=0;i<64;i++) {
            secret.append(alphabet.get(r.nextInt(len)));
        }
        return secret;
    }
}
