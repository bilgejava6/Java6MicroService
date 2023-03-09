package com.muhammet.service;

import com.muhammet.dto.request.DoLoginRequestDto;
import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.exception.AuthServiceException;
import com.muhammet.exception.EErrorType;
import com.muhammet.mapper.IAuthMapper;
import com.muhammet.repository.IAuthRepository;
import com.muhammet.repository.entity.Auth;
import com.muhammet.utility.JwtTokenManager;
import com.muhammet.utility.ServiceManager;
import com.muhammet.utility.TokenManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final JwtTokenManager tokenManager;
    public  AuthService(IAuthRepository repository,JwtTokenManager tokenManager){
        super(repository);
        this.repository=repository;
        this.tokenManager = tokenManager;
    }

    public Auth register(RegisterRequestDto dto){
        if(repository.isUsername(dto.getUsername()))
            throw new AuthServiceException(EErrorType.REGISTER_ERROR_USERNAME);
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        /**
         * Repo -> repository.save(auth) bu bana kaydettiği entity döner
         * Servi-> save(auth) bana kaydettiği entity döner
         * direkt -> auth, bir şekilde kayıt edilen entity nin bilgileri işlenir ve bunu döner
         */
        return save(auth);
        //return repository.save(auth);
    }
    public Optional<Auth> findOptionalByUsernameAndPassword(String username, String password){
        return repository.findOptionalByUsernameAndPassword(username, password);
    }

    /**
     * Kullanıcıyı doğrulayacak ve kullanıcının sistem içinde hareket edebilmesi için
     * ona özel bir kimlik verecek.
     * Token -> JWT token
     * @param dto
     * @return
     */
    public String doLogin(DoLoginRequestDto dto) {
        Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
        if(auth.isEmpty())
            throw new AuthServiceException(EErrorType.LOGIN_ERROR_USERNAME_PASSWORD);
        return tokenManager.createToken(auth.get().getId()).get();
    }

    public List<Auth> findAll(String token){
        Optional<Long> id= tokenManager.getByIdFromToken(token);
        if(id.isEmpty())
            throw new AuthServiceException(EErrorType.INVALID_TOKEN);
        if(findById(id.get()).isEmpty())
            throw new AuthServiceException(EErrorType.INVALID_TOKEN);
        return findAll();
    }
}
