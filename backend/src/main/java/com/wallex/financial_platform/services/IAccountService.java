package com.wallex.financial_platform.services;

import com.wallex.financial_platform.dtos.requests.AccountRequestDTO;
import com.wallex.financial_platform.dtos.responses.AccountResponseDTO;
import com.wallex.financial_platform.dtos.responses.CheckAccountResponseDTO;

import java.util.List;

public interface IAccountService {
    List<AccountResponseDTO> getByUser();
    CheckAccountResponseDTO getByCbu(String Cbu);
    CheckAccountResponseDTO getByAlias(String alias);
    AccountResponseDTO createAccount(AccountRequestDTO accountReq);
}
