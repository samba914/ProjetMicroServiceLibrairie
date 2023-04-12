package com.samba_mohamed.bookshop.reader.controller;

import com.samba_mohamed.bookshop.reader.model.Reader;
import com.samba_mohamed.bookshop.reader.serviceInterface.IReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
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
    @Cacheable(value="readers")
    public List<Reader> getAllReaders() {
        return readerService.getAllReaders();
    }

    @CacheEvict(value="readers")
    @PostMapping("/createmanyreaders")
    public ResponseEntity<List<Reader>> createManyReaders(@RequestBody List<Reader> readers) {
        List<Reader> createdLivres = readerService.createManyReaders(readers);
        return new ResponseEntity<>(createdLivres, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "reader", key = "#id")
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

    @CacheEvict(value="readers", allEntries = true)
    @CachePut(value = "reader", key = "#result.id")
    @PostMapping
    public Reader createReader(@RequestBody Reader reader) {
        return readerService.createReader(reader);
    }

    @CachePut(value = "reader", key = "#id")
    @CacheEvict(value="readers", allEntries = true)
    @PutMapping("/{id}")
    public Reader updateReader(@PathVariable Long id, @RequestBody Reader readerDetails) {
        return readerService.updateReader(id, readerDetails);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = {"reader", "readers"}, allEntries = true, key = "#id")
    public ResponseEntity<String>  deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
        return ResponseEntity.ok("Lecteur supprimé avec succès");
    }

}
