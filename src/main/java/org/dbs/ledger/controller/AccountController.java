package org.dbs.ledger.controller;

import jakarta.validation.Valid;
import org.dbs.ledger.configuration.contexts.UserContext;
import org.dbs.ledger.dto.request.SignupRequest;
import org.dbs.ledger.dto.request.user.EmailSignInRequest;
import org.dbs.ledger.dto.request.user.MobileSignInRequest;
import org.dbs.ledger.dto.response.SignInResponse;
import org.dbs.ledger.dto.response.AccountResponse;
import org.dbs.ledger.dto.response.wrapper.ResponseWrapper;
import org.dbs.ledger.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    private final UserContext userContext;

    @Autowired
    public AccountController(AccountService accountService, UserContext userContext) {
        this.accountService = accountService;
        this.userContext = userContext;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<AccountResponse>> getAccount() {
        AccountResponse accountResponse = accountService.getAccount(userContext.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(accountResponse));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseWrapper<AccountResponse>> signup(@RequestBody @Valid SignupRequest signupRequest) {
        AccountResponse accountResponse = accountService.signupAccount(signupRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(accountResponse));
    }

    @PostMapping("/mobile/sign-in")
    public ResponseEntity<ResponseWrapper<SignInResponse>> signInViaMobile(@RequestBody @Valid MobileSignInRequest mobileSignInRequest) {
        SignInResponse signInResponse = accountService.signIn(mobileSignInRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(signInResponse));
    }

    @PostMapping("/email/sign-in")
    public ResponseEntity<ResponseWrapper<SignInResponse>> signInViaEmail(@RequestBody @Valid EmailSignInRequest emailSignInRequest) {
        SignInResponse signInResponse = accountService.signIn(emailSignInRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(signInResponse));
    }
}
