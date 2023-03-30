package com.samba_mohamed.bookshop.reader.serviceInterface;


import com.samba_mohamed.bookshop.reader.model.Reader;

import java.time.LocalDate;
import java.util.List;

public interface IReaderService {
    List<Reader> getAllReaders();

    Reader getReaderById(Long id);

    List<Reader> getReadersByNom(String nom);

    List<Reader> createManyReaders(List<Reader> readers);

    Reader getByNomAndPrenom(String nom,String prenom);

    List<Reader> getReaderByDateDeNaissance(LocalDate dateNaissance);

    Reader createReader(Reader reader);

    Reader updateReader(Long id, Reader readerDetails);

    void deleteReader(Long id);

}
