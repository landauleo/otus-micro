package leo.landau.controller;

import java.math.BigDecimal;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import leo.landau.model.Account;
import leo.landau.service.AccountServiceImpl;

@Tag(name = "user")
@Controller("/account")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class AccountController {

    @Inject
    private AccountServiceImpl accountServiceImpl;

    @Post
    public HttpResponse<Account> createAccount(@QueryValue Long userId) {
        return HttpResponse.ok(accountServiceImpl.createAccount(userId));
    }

    @Post("/deposit")
    public HttpResponse<Account> deposit(@QueryValue Long userId, @QueryValue BigDecimal amount) {
        return HttpResponse.ok(accountServiceImpl.deposit(userId, amount));
    }

    @Post("/withdraw")
    public HttpResponse<Boolean> withdraw(@QueryValue Long userId, @QueryValue BigDecimal amount) {
        return HttpResponse.ok(accountServiceImpl.withdraw(userId, amount));
    }

    @Get("/{userId}")
    public HttpResponse<BigDecimal> getBalance(@PathVariable Long userId) {
        return HttpResponse.ok(accountServiceImpl.getBalance(userId));
    }

}
