package org.tutorials.springbootmodelmappermapstructlibraries.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.tutorials.springbootmodelmappermapstructlibraries.dto.UserDto;
import org.tutorials.springbootmodelmappermapstructlibraries.entity.User;
import org.tutorials.springbootmodelmappermapstructlibraries.mapper.AutoUserMapper;
import org.tutorials.springbootmodelmappermapstructlibraries.mapper.UserMapper;
import org.tutorials.springbootmodelmappermapstructlibraries.repository.UserRepository;
import org.tutorials.springbootmodelmappermapstructlibraries.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    // config model mapper library
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        // Convert UserDto into User JPA Entity
        // User user = UserMapper.mapToUser(userDto);

        // Config ModelMapper to convert UserDto into User JPA Entity
        // User user = modelMapper.map(userDto, User.class);

        // Config mapstruct to convert UserDto into User JPA Entity
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        // Convert User JPA Entity into UserDto
        // UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        // Config ModelMapper to convert User JPA Entity into UserDto
        // UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        // Config mapstruct to convert User JPA Entity into UserDto
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);

        // Return UserDto
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        // return UserMapper.mapToUserDto(user);

        // config model mapper library
        // return modelMapper.map(user, UserDto.class);

        // Config mapstruct library
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        // return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        // config model mapper library
        // return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        // Config mapstruct library
        return users.stream().map(AutoUserMapper.MAPPER::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        // return UserMapper.mapToUserDto(updatedUser);
        // config model mapper library
        // return modelMapper.map(updatedUser, UserDto.class);

        // Config mapstruct library
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
