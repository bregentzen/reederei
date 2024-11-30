package de.hsos.swa.reederei.flottenmanagement.enitity;

import jakarta.enterprise.context.Dependent;

import java.util.Optional;


public interface SchiffVerwaltung {
    public Optional<Schiff> getSchiff(Long id);
    public Optional<Schiff> createSchiff(Schiff schiff);
}
