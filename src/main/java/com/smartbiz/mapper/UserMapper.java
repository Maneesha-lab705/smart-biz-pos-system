package com.smartbiz.mapper;

import com.smartbiz.dto.UserDTO;
import com.smartbiz.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "business.businessId", target = "businessId")
    UserDTO toDTO(User user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "business", ignore = true)
    User toEntity(UserDTO dto);
}
