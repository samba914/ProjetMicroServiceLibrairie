package com.samba_mohamed.bookshop.subscription.serviceImpl;

import com.samba_mohamed.bookshop.subscription.dto.Reader;
import com.samba_mohamed.bookshop.subscription.exception.ReaderNotFoundException;
import com.samba_mohamed.bookshop.subscription.serviceInterface.IReaderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
public class ReaderClient implements IReaderClient {

    @Value("${microservices.lecteur-service-url}")
    private String lecteurServiceUrl;

    public Reader getReaderById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Reader reader = restTemplate.getForObject(lecteurServiceUrl + "/readers/" + id, Reader.class);
        if (reader == null) {
            throw new ReaderNotFoundException("Lecteur introuvable pour l'ID : " + id);
        }
        return reader;
    }
    public Reader getReaderByPrenomAndNom(String prenom , String nom) {
        RestTemplate restTemplate = new RestTemplate();
        String url = lecteurServiceUrl + "/readers/nomAndPrenom?nom=%s&prenom=%s";
        Reader reader = restTemplate.getForObject(String.format(url,nom,prenom), Reader.class);
        if (reader == null) {
            throw new ReaderNotFoundException(String.format("Lecteur introuvable pour le prenom %s et nom %s",prenom,nom));
        }
        return reader;
    }
}
