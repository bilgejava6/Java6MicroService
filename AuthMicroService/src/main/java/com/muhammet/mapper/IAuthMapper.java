package com.muhammet.mapper;

import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.dto.request.UserProfileSaveRequestDto;
import com.muhammet.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(final RegisterRequestDto dto);
    @Mapping(target = "authid",source = "id")
    UserProfileSaveRequestDto fromAuth(final Auth auth);
}
