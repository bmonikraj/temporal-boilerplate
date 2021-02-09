package com.bmonikraj.controller;

import com.bmonikraj.model.DTO.MoneyTransferRequest;
import com.bmonikraj.model.DTO.MoneyTransferResponse;
import com.bmonikraj.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/money-transfer")
public class MoneyTransferController {

    @Autowired
    MoneyTransferService moneyTransferService;

    @RequestMapping(
            value = "/initiate",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public MoneyTransferResponse initiate(
            @RequestBody MoneyTransferRequest moneyTransferRequest) {
        return moneyTransferService.transferAmountBetweenTwoAccounts(moneyTransferRequest);
    }
}
