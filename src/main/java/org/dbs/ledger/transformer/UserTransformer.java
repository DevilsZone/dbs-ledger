package org.dbs.ledger.transformer;

import managers.jwt.models.JwtPayload;
import managers.jwt.models.JwtToken;
import org.dbs.ledger.annotation.Transformer;
import org.dbs.ledger.dto.request.SignupRequest;
import org.dbs.ledger.dto.request.common.EmailRequest;
import org.dbs.ledger.dto.request.common.MobileRequest;
import org.dbs.ledger.dto.response.SignInResponse;
import org.dbs.ledger.dto.response.UserResponse;
import org.dbs.ledger.dto.response.common.EmailResponse;
import org.dbs.ledger.dto.response.common.MobileResponse;
import org.dbs.ledger.model.User;
import org.dbs.ledger.model.common.Email;
import org.dbs.ledger.model.common.Mobile;
import org.springframework.beans.factory.annotation.Autowired;

@Transformer
public class UserTransformer {
    private final CommonTransformer commonTransformer;

    @Autowired
    public UserTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }

    public Mobile convertMobileRequestToModel(MobileRequest mobileRequest) {
        return commonTransformer.convertMobileRequestToModel(mobileRequest);
    }

    public Email convertEmailRequestToModel(EmailRequest emailRequest) {
        return commonTransformer.convertEmailRequestToModel(emailRequest);
    }

    public User convertSignupRequestToModel(SignupRequest signupRequest) {
        return User
                .builder()
                .name(signupRequest.getName())
                .profileUrl(signupRequest.getProfileUrl())
                .email(convertEmailRequestToModel(signupRequest.getEmail()))
                .mobile(convertMobileRequestToModel(signupRequest.getMobile()))
                .password(signupRequest.getPassword())
                .build();
    }

    public JwtPayload convertUserToJwtPayload(User user) {
        return JwtPayload.builder().sub(user.getId()).build();
    }

    public MobileResponse convertMobileToResponse(Mobile mobile) {
        return commonTransformer.convertModelToResponse(mobile);
    }

    public EmailResponse convertEmailToResponse(Email email) {
        return commonTransformer.convertModelToResponse(email);
    }

    public SignInResponse convertTokenToSignInResponse(JwtToken token) {
        return SignInResponse.builder().accessToken(token.getToken()).build();
    }

    public UserResponse convertModelToResponse(User user) {
        return UserResponse
                .builder()
                .userId(user.getId())
                .email(convertEmailToResponse(user.getEmail()))
                .mobile(convertMobileToResponse(user.getMobile()))
                .name(user.getName())
                .profileUrl(user.getProfileUrl())
                .build();
    }
}
