package com.example.project.mappers;

import com.example.project.dtos.UserDto;
import com.example.project.entities.Role;
import com.example.project.entities.User;
import com.example.project.user.CreateUserRequest;
import com.example.project.user.UpdateUserRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User entity);

    @Mapping(source = "password", target = "password")
    User toEntity(CreateUserRequest createUserRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void update(UpdateUserRequest request, @MappingTarget User user);

    default Role mapRole(String role) {
        return role != null ? Role.valueOf(role.toUpperCase()) : null;
    }
}