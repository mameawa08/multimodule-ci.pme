package com.administration.service;

import com.administration.exception.ScoringConnectException;

public interface IScoringConnectService {

    public Long getEntreprise(Long id) throws ScoringConnectException;
}
