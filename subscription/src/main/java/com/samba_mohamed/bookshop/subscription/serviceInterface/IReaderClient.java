package com.samba_mohamed.bookshop.subscription.serviceInterface;

import com.samba_mohamed.bookshop.subscription.dto.Reader;

public interface IReaderClient {
    public Reader getReaderById(Long reader_id);
    public Reader getReaderByPrenomAndNom(String prerom, String nom);
}
