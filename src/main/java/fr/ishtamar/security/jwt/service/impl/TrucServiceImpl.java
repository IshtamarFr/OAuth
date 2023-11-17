package fr.ishtamar.security.jwt.service.impl;

import fr.ishtamar.security.jwt.entity.Truc;
import fr.ishtamar.security.jwt.exceptionhandler.EntityNotFoundException;
import fr.ishtamar.security.jwt.repository.TrucRepository;
import fr.ishtamar.security.jwt.service.TrucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrucServiceImpl implements TrucService {
    @Autowired
    private TrucRepository repository;

    public List<Truc> getAllTrucs() {
        return repository.findAll();
    }

    @Override
    public Truc getTrucById(final Long id) throws EntityNotFoundException {
        return repository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(Truc.class,"id",id.toString()));
    }
}
