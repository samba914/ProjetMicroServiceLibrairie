package com.samba_mohamed.bookshop.reader.serviceImpl;

import com.samba_mohamed.bookshop.reader.exception.ReaderNotFoundException;
import com.samba_mohamed.bookshop.reader.model.Reader;
import com.samba_mohamed.bookshop.reader.repository.ReaderRepository;
import com.samba_mohamed.bookshop.reader.serviceInterface.IReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReaderService implements IReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public List<Reader> getAllReaders() {
        System.out.println("came here");

        return readerRepository.findAll();
    }

    @Override
    public Reader getReaderById(Long id) {
        return readerRepository.findById(id)
                .orElseThrow(() -> new ReaderNotFoundException("Le lecteur avec l'id " + id + " est introuvable"));
    }


    @Override
    public List<Reader> getReadersByNom(String nom) {
        return readerRepository.findByNomIgnoreCase(nom);
    }

    @Override
    public List<Reader> createManyReaders(List<Reader> readers) {
        return readerRepository.saveAll(readers);
    }


    @Override
    public Reader getByNomAndPrenom(String nom, String prenom) {
        return readerRepository.findByNomAndPrenomAllIgnoreCase(nom, prenom)
                .orElseThrow(() -> new ReaderNotFoundException("Le lecteur avec le nom " + nom + " et le prénom " + prenom + " est introuvable"));
    }

    @Override
    public List<Reader> getReaderByDateDeNaissance(LocalDate dateNaissance) {
        return readerRepository.findByDateNaissance(dateNaissance);
    }

    @Override
    public Reader createReader(Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    public Reader updateReader(Long id, Reader readerDetails) {
        Reader reader = getReaderById(id);
        reader.setNom(readerDetails.getNom());
        reader.setPrenom(readerDetails.getPrenom());
        reader.setGenre(readerDetails.getGenre());
        reader.setDateNaissance(readerDetails.getDateNaissance());
        reader.setAdresse(readerDetails.getAdresse());
        return readerRepository.save(reader);
    }

    @Override
    public void deleteReader(Long id) {
        Reader reader = getReaderById(id);
        readerRepository.delete(reader);
    }


}
