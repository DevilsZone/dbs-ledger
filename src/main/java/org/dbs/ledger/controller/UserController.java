package org.dbs.ledger.controller;

import jakarta.validation.Valid;
import org.dbs.ledger.configuration.contexts.UserContext;
import org.dbs.ledger.dto.request.SignupRequest;
import org.dbs.ledger.dto.request.user.EmailSignInRequest;
import org.dbs.ledger.dto.request.user.MobileSignInRequest;
import org.dbs.ledger.dto.response.SignInResponse;
import org.dbs.ledger.dto.response.UserResponse;
import org.dbs.ledger.dto.response.wrapper.ResponseWrapper;
import org.dbs.ledger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserContext userContext;

    @Autowired
    public UserController(UserService userService, UserContext userContext) {
        this.userService = userService;
        this.userContext = userContext;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<UserResponse>> getUser() {
        UserResponse userResponse = userService.getUser(userContext.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(userResponse));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseWrapper<UserResponse>> signup(@RequestBody @Valid SignupRequest signupRequest) {
        UserResponse userResponse = userService.signupUser(signupRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(userResponse));
    }

    @PostMapping("/mobile/sign-in")
    public ResponseEntity<ResponseWrapper<SignInResponse>> signInViaMobile(@RequestBody @Valid MobileSignInRequest mobileSignInRequest) {
        SignInResponse signInResponse = userService.signIn(mobileSignInRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(signInResponse));
    }

    @PostMapping("/email/sign-in")
    public ResponseEntity<ResponseWrapper<SignInResponse>> signInViaEmail(@RequestBody @Valid EmailSignInRequest emailSignInRequest) {
        SignInResponse signInResponse = userService.signIn(emailSignInRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(signInResponse));
    }
}
