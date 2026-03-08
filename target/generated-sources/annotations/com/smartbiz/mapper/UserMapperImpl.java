package com.smartbiz.mapper;

import com.smartbiz.dto.UserDTO;
import com.smartbiz.entity.Business;
import com.smartbiz.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-08T18:14:07+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setBusinessId( userBusinessBusinessId( user ) );
        userDTO.setUserId( user.getUserId() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setName( user.getName() );
        userDTO.setBussinessRole( user.getBussinessRole() );
        userDTO.setAdminRole( user.getAdminRole() );
        userDTO.setCreatedAt( user.getCreatedAt() );

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( dto.getEmail() );
        user.name( dto.getName() );
        user.bussinessRole( dto.getBussinessRole() );
        user.adminRole( dto.getAdminRole() );

        return user.build();
    }

    private Long userBusinessBusinessId(User user) {
        if ( user == null ) {
            return null;
        }
        Business business = user.getBusiness();
        if ( business == null ) {
            return null;
        }
        Long businessId = business.getBusinessId();
        if ( businessId == null ) {
            return null;
        }
        return businessId;
    }
}
