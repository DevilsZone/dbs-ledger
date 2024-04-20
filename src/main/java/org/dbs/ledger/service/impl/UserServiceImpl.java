package org.dbs.ledger.service.impl;

import managers.jwt.impl.JwtAccessTokenManager;
import managers.jwt.models.JwtToken;
import org.dbs.ledger.dto.request.SignupRequest;
import org.dbs.ledger.dto.request.user.EmailSignInRequest;
import org.dbs.ledger.dto.request.user.MobileSignInRequest;
import org.dbs.ledger.dto.response.SignInResponse;
import org.dbs.ledger.dto.response.UserResponse;
import org.dbs.ledger.dto.response.wrapper.ErrorResponse;
import org.dbs.ledger.enums.ErrorCode;
import org.dbs.ledger.enums.Status;
import org.dbs.ledger.exceptions.RestException;
import org.dbs.ledger.helper.IdHelper;
import org.dbs.ledger.model.User;
import org.dbs.ledger.repository.UserRepository;
import org.dbs.ledger.service.UserService;
import org.dbs.ledger.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserTransformer userTransformer;

    private final IdHelper idHelper;

    private final JwtAccessTokenManager jwtAccessTokenManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserTransformer userTransformer, IdHelper idHelper, JwtAccessTokenManager jwtAccessTokenManager) {
        this.userRepository = userRepository;
        this.userTransformer = userTransformer;
        this.idHelper = idHelper;
        this.jwtAccessTokenManager = jwtAccessTokenManager;
    }

    @Override
    public SignInResponse signIn(MobileSignInRequest mobileSignInRequest) {
        User user = userRepository.findUserByMobileAndStatus(userTransformer.convertMobileRequestToModel(mobileSignInRequest.getMobileRequest()), Status.ACTIVE).orElseThrow(
                () -> new RestException(HttpStatus.UNAUTHORIZED, ErrorResponse.from(ErrorCode.INVALID_CREDENTIALS))
        );
        if (!user.getPassword().equals(mobileSignInRequest.getPassword())) {
            throw new RestException(HttpStatus.UNAUTHORIZED, ErrorResponse.from(ErrorCode.INVALID_CREDENTIALS));
        }
        JwtToken token = jwtAccessTokenManager.createToken(userTransformer.convertUserToJwtPayload(user));
        return userTransformer.convertTokenToSignInResponse(token);
    }

    @Override
    public SignInResponse signIn(EmailSignInRequest emailSignInRequest) {
        User user = userRepository.findUserByEmailAndStatus(userTransformer.convertEmailRequestToModel(emailSignInRequest.getEmailRequest()), Status.ACTIVE).orElseThrow(
                () -> new RestException(HttpStatus.UNAUTHORIZED, ErrorResponse.from(ErrorCode.INVALID_CREDENTIALS))
        );
        if (!user.getPassword().equals(emailSignInRequest.getPassword())) {
            throw new RestException(HttpStatus.UNAUTHORIZED, ErrorResponse.from(ErrorCode.INVALID_CREDENTIALS));
        }
        JwtToken token = jwtAccessTokenManager.createToken(userTransformer.convertUserToJwtPayload(user));
        return userTransformer.convertTokenToSignInResponse(token);
    }

    @Override
    public UserResponse getUser(String userId) {
        User user = userRepository.findUserByIdAndStatus(userId, Status.ACTIVE).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.USER_NOT_FOUND))
        );
        return userTransformer.convertModelToResponse(user);
    }

    @Override
    public UserResponse signupUser(SignupRequest signupRequest) {
        User user = userTransformer.convertSignupRequestToModel(signupRequest);
        user.setId(idHelper.getNextId());
        user.setStatus(Status.ACTIVE);

        try {
            User createdUser = userRepository.save(user);
            return userTransformer.convertModelToResponse(createdUser);
        } catch (DuplicateKeyException duplicateKeyException) {
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.USER_ALREADY_EXIST));
        }
    }
}
