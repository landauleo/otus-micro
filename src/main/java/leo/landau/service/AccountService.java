package leo.landau.service;

import java.math.BigDecimal;

import leo.landau.model.Account;

public interface AccountService {

    Account createAccount(Long userId);

    Account deposit(Long userId, BigDecimal amount);

    boolean withdraw(Long userId, BigDecimal amount);

    BigDecimal getBalance(Long userId);

}
