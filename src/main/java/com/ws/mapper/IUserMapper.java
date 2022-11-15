package com.ws.mapper;

import com.ws.entity.UserEntity;
import com.ws.entity.dto.UserDto;
import com.ws.entity.dto.data.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {IRoleMapper.class , IEmployeeMapper.class})
public interface IUserMapper {

    UserDto toDto(UserEntity userEntity);
    UserEntity toEntity(UserDto storeDto);

    @Mapping(target = "role" , expression = "java(Set.of(new RoleEntity(user.getRole())))")
    //@Mapping(target = "employee" , source = "employee")
    UserEntity dataToEntity(UserRequest user);





}
