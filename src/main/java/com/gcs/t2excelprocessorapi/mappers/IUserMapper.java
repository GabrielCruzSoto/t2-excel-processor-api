package com.gcs.t2excelprocessorapi.mappers;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.UsersCreatePostRequest;
import com.gcs.t2excelprocessorapi.entities.UserEntity;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "pwd", ignore = true)
    @Mapping(target = "role", source = "role")
    @Mapping(target = "createdOn", ignore = true)
    UserEntity toEntity(UsersCreatePostRequest request);

}
