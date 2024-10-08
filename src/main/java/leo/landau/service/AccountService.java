package leo.landau.service;

import java.math.BigDecimal;
import javax.transaction.Transactional;

import jakarta.inject.Singleton;
import leo.landau.model.Account;
import leo.landau.repository.AccountRepository;

@Singleton
@Transactional
public class AccountService {

    private AccountRepository accountRepository;

    public Account createAccount(Long userId) {
        return accountRepository.save(new Account(userId, BigDecimal.ZERO));
    }

    public Account deposit(Long userId, BigDecimal amount) {
        Account account = accountRepository.findByUserId(userId);
        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }

    public boolean withdraw(Long userId, BigDecimal amount) {
        Account account = accountRepository.findByUserId(userId);
        if (account.getBalance().compareTo(amount) >= 0) {
            account.setBalance(account.getBalance().subtract(amount));
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    public BigDecimal getBalance(Long userId) {
        return accountRepository.findByUserId(userId).getBalance();
    }

}
