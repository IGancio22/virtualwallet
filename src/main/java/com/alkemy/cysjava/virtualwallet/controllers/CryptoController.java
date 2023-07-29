package com.alkemy.cysjava.virtualwallet.controllers;

import com.alkemy.cysjava.virtualwallet.DTOs.*;
import com.alkemy.cysjava.virtualwallet.exceptions.ResourceNotFoundException;
import com.alkemy.cysjava.virtualwallet.mappers.CryptoMapper;
import com.alkemy.cysjava.virtualwallet.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @PostMapping("/register")
    public ResponseEntity<CryptoDTO> addCrypto(@RequestBody @Valid CryptoCreationDTO cryptoCreationDTO){
        CryptoDTO newCryptoDTO = cryptoService.addCrypto(cryptoCreationDTO);
        return new ResponseEntity<>(newCryptoDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CryptoDTO> getUserById(@PathVariable Long id){
        try {
            CryptoDTO cryptoDTO = cryptoService.findUserById(id);
            return new ResponseEntity<>(cryptoDTO, HttpStatus.OK);
        }
        catch(ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CryptoDTO> updateCrypto(@PathVariable Long id, @RequestBody @Valid CryptoUpdateDTO cryptoUpdateDTO){
        CryptoDTO newCryptoDTO = cryptoService.updateCrypto(id, cryptoUpdateDTO);
        return new ResponseEntity<>(newCryptoDTO, HttpStatus.OK);
    }
}

