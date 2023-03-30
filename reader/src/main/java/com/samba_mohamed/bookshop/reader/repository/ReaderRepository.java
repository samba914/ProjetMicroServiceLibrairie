package com.samba_mohamed.bookshop.reader.repository;

import com.samba_mohamed.bookshop.reader.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

    List<Reader> findByNomIgnoreCase(String nom);

    Optional<Reader> findByNomAndPrenomAllIgnoreCase(String nom,String prenom);

    List<Reader> findByDateNaissance(LocalDate dateNaissance);

}