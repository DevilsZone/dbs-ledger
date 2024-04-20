package org.dbs.ledger.service;

import org.dbs.ledger.dto.request.SignupRequest;
import org.dbs.ledger.dto.request.user.EmailSignInRequest;
import org.dbs.ledger.dto.request.user.MobileSignInRequest;
import org.dbs.ledger.dto.response.SignInResponse;
import org.dbs.ledger.dto.response.AccountResponse;

public interface AccountService {
    SignInResponse signIn(MobileSignInRequest mobileSignInRequest);

    SignInResponse signIn(EmailSignInRequest emailSignInRequest);

    AccountResponse getAccount(String userId);

    AccountResponse signupAccount(SignupRequest signupRequest);
}
