package com.samba_mohamed.bookshop.reader.controller;

import com.samba_mohamed.bookshop.reader.model.Reader;
import com.samba_mohamed.bookshop.reader.serviceInterface.IReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    @Autowired
    private IReaderService readerService;

    @GetMapping
    public List<Reader> getAllReaders() {
        return readerService.getAllReaders();
    }
    @PostMapping("/createmanyreaders")
    public ResponseEntity<List<Reader>> createManyReaders(@RequestBody List<Reader> readers) {
        List<Reader> createdLivres = readerService.createManyReaders(readers);
        return new ResponseEntity<>(createdLivres, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Reader getReaderById(@PathVariable Long id) {
        return readerService.getReaderById(id);
    }

    @GetMapping("/nom/{nom}")
    public List<Reader> getReadersByNom(@PathVariable String nom) {
        return readerService.getReadersByNom(nom);
    }

    @GetMapping("/nomAndPrenom")
    public Reader getByNomAndPrenom(@RequestParam String nom, @RequestParam String prenom) {
        return readerService.getByNomAndPrenom(nom, prenom);
    }

    @GetMapping("/dateNaissance")
    public List<Reader> getReaderByDateDeNaissance(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateNaissance) {
        return readerService.getReaderByDateDeNaissance(dateNaissance);
    }

    @PostMapping
    public Reader createReader(@RequestBody Reader reader) {
        return readerService.createReader(reader);
    }

    @PutMapping("/{id}")
    public Reader updateReader(@PathVariable Long id, @RequestBody Reader readerDetails) {
        return readerService.updateReader(id, readerDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
        return ResponseEntity.ok("Lecteur supprimé avec succès");
    }


}
