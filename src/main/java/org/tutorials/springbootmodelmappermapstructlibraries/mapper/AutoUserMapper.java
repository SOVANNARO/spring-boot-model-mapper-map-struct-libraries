package org.tutorials.springbootmodelmappermapstructlibraries.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.tutorials.springbootmodelmappermapstructlibraries.dto.UserDto;
import org.tutorials.springbootmodelmappermapstructlibraries.entity.User;

@Mapper
public interface AutoUserMapper {
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
    UserDto mapToUserDto(User user);
    User mapToUser(UserDto userDto);
}
