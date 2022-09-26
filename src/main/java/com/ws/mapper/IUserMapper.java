package com.ws.mapper;

import com.ws.entity.UserEntity;
import com.ws.entity.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = {IRoleMapper.class , IEmployeeMapper.class})
public interface IUserMapper {

    UserDto toDto(UserEntity userEntity);
    UserEntity toEntity(UserDto storeDto);
}
