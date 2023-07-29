package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.CryptoCreationDTO;
import com.alkemy.cysjava.virtualwallet.DTOs.CryptoDTO;
import com.alkemy.cysjava.virtualwallet.exceptions.BadRequestException;
import com.alkemy.cysjava.virtualwallet.mappers.CryptoMapper;
import com.alkemy.cysjava.virtualwallet.models.Crypto;
import com.alkemy.cysjava.virtualwallet.repositories.AccountRepository;
import com.alkemy.cysjava.virtualwallet.repositories.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class CryptoService {
    private final CryptoRepository cryptoRepository;
    private final CryptoMapper cryptoMapper;
    private final AccountRepository accountRepository;
    public CryptoService(CryptoRepository cryptoRepository, CryptoMapper cryptoMapper, AccountRepository accountRepository) {
        this.cryptoMapper = cryptoMapper;
        this.cryptoRepository = cryptoRepository;
        this.accountRepository = accountRepository;
    }

    public CryptoDTO addCrypto(CryptoCreationDTO cryptoCreationDTO) {
        Crypto crypto = cryptoMapper.toCrypto(cryptoCreationDTO);

        cryptoCreationDTO.setName(cryptoCreationDTO.getName().trim());
        cryptoCreationDTO.setAmount(cryptoCreationDTO.getAmount());
        cryptoCreationDTO.setAccount(cryptoCreationDTO.getAccount());
        crypto.setCreationDate(new Timestamp(System.currentTimeMillis()));

        Crypto createdCrypto = cryptoRepository.save(crypto);
        return cryptoMapper.toCryptoDTO(createdCrypto);
    }
}
