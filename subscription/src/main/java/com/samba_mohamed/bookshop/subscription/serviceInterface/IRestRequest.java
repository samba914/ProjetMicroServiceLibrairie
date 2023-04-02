package com.samba_mohamed.bookshop.subscription.serviceInterface;

public interface IRestRequest<T> {
    public T getRequest(String url,Class<T> responseType);
}
