package fr.ishtamar.security.jwt.service;


import fr.ishtamar.security.jwt.entity.Truc;
import fr.ishtamar.security.jwt.exceptionhandler.EntityNotFoundException;

public interface TrucService {
    public Truc getTrucById(final Long id) throws EntityNotFoundException;
}
