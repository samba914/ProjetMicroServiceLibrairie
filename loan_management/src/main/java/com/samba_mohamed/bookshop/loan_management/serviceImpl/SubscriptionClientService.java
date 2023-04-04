package com.samba_mohamed.bookshop.loan_management.serviceImpl;

import com.samba_mohamed.bookshop.loan_management.dto.Book;
import com.samba_mohamed.bookshop.loan_management.dto.Subscription;
import com.samba_mohamed.bookshop.loan_management.exception.BookNotFoundException;
import com.samba_mohamed.bookshop.loan_management.exception.NoSubscriptionValidFoundForReaderException;
import com.samba_mohamed.bookshop.loan_management.serviceInterface.ISubsciptionClientService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SubscriptionClientService implements ISubsciptionClientService {
    @Value("${microservices.subscription-service-url}")
    private String subscriptionClientUrl ;

    @Override
    public Subscription getSubscriptionValidByReaderId(Long readerId) {
        RestTemplate restTemplate = new RestTemplate();
        Subscription subscription = restTemplate.getForObject(subscriptionClientUrl + "/subscriptions/reader/valid/" + readerId, Subscription.class);
        return subscription;
    }
}
