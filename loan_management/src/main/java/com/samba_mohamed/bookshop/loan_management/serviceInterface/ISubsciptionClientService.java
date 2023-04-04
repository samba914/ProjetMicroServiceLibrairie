package com.samba_mohamed.bookshop.loan_management.serviceInterface;

import com.samba_mohamed.bookshop.loan_management.dto.Subscription;

public interface ISubsciptionClientService {

    public Subscription getSubscriptionValidByReaderId (Long readerId) ;
}
