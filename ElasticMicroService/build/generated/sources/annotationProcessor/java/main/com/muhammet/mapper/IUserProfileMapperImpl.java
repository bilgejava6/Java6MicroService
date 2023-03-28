package com.muhammet.mapper;

import com.muhammet.dto.request.AddUserRequestDto;
import com.muhammet.repository.entity.UserProfile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-22T14:28:18+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.3.1 (Oracle Corporation)"
)
@Component
public class IUserProfileMapperImpl implements IUserProfileMapper {

    @Override
    public UserProfile toProfile(AddUserRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder<?, ?> userProfile = UserProfile.builder();

        userProfile.userprofileid( dto.getId() );
        if ( dto.getId() != null ) {
            userProfile.id( String.valueOf( dto.getId() ) );
        }
        userProfile.authid( dto.getAuthid() );
        userProfile.username( dto.getUsername() );
        userProfile.email( dto.getEmail() );
        userProfile.ad( dto.getAd() );
        userProfile.adres( dto.getAdres() );
        userProfile.telefon( dto.getTelefon() );
        userProfile.avatar( dto.getAvatar() );

        return userProfile.build();
    }
}
