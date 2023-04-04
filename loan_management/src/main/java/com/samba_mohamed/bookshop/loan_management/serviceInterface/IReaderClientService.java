package com.samba_mohamed.bookshop.loan_management.serviceInterface;


import com.samba_mohamed.bookshop.loan_management.dto.Reader;

public interface IReaderClientService {
    public Reader getReaderById(Long reader_id);
}
