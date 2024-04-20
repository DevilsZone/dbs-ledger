package org.dbs.ledger.service;

import org.dbs.ledger.dto.request.SignupRequest;
import org.dbs.ledger.dto.request.user.EmailSignInRequest;
import org.dbs.ledger.dto.request.user.MobileSignInRequest;
import org.dbs.ledger.dto.response.SignInResponse;
import org.dbs.ledger.dto.response.UserResponse;

public interface UserService {
    SignInResponse signIn(MobileSignInRequest mobileSignInRequest);

    SignInResponse signIn(EmailSignInRequest emailSignInRequest);

    UserResponse getUser(String userId);

    UserResponse signupUser(SignupRequest signupRequest);
}
