package com.healthcare.medical_system.mapper;

import com.healthcare.medical_system.dto.UserDTO;
import com.healthcare.medical_system.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
}
