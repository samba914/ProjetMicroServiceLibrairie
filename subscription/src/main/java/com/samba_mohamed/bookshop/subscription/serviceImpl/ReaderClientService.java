package com.samba_mohamed.bookshop.subscription.serviceImpl;

import com.samba_mohamed.bookshop.subscription.dto.Reader;
import com.samba_mohamed.bookshop.subscription.exception.ReaderNotFoundException;
import com.samba_mohamed.bookshop.subscription.serviceInterface.IReaderClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReaderClientService implements IReaderClientService {

    @Value("${microservices.lecteur-service-url}")
    private String lecteurServiceUrl;
    @Autowired
    private RedisCacheManager cacheManager;


    //@Cacheable(value = "reader", key = "#id" , cacheManager = "cacheManager")
    public Reader getReaderById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Reader reader = restTemplate.getForObject(lecteurServiceUrl + "/readers/" + id, Reader.class);
        if (reader == null) {
            throw new ReaderNotFoundException("Lecteur introuvable pour l'ID : " + id);
        }
        return reader;
    }
    //@Cacheable(value = "reader", key = "#prenom + #nom" , cacheManager = "cacheManager")
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
