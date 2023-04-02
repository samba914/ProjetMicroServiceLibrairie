package com.samba_mohamed.bookshop.subscription.serviceImpl;

import com.samba_mohamed.bookshop.subscription.serviceInterface.IRestRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestRequest <T> implements IRestRequest<T> {

    @Override
    public T getRequest(String url,Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> response = restTemplate.getForEntity(url,responseType);
        T el  = null;
        if(response.getStatusCode()  ==  HttpStatus.OK) {
            el = response.getBody();
        }
        return el;
    }
}
