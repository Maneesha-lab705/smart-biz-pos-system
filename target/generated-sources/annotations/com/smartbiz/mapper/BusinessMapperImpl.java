package com.smartbiz.mapper;

import com.smartbiz.dto.BusinessDTO;
import com.smartbiz.entity.Business;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-04T21:21:17+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class BusinessMapperImpl implements BusinessMapper {

    @Override
    public BusinessDTO toDTO(Business business) {
        if ( business == null ) {
            return null;
        }

        BusinessDTO businessDTO = new BusinessDTO();

        businessDTO.setCreatedAt( business.getCreatedAt() );
        businessDTO.setBusinessId( business.getBusinessId() );
        businessDTO.setBusinessName( business.getBusinessName() );
        businessDTO.setOwnerName( business.getOwnerName() );
        businessDTO.setContactNumber( business.getContactNumber() );
        businessDTO.setEmail( business.getEmail() );
        businessDTO.setPassword( business.getPassword() );
        businessDTO.setStatus( business.getStatus() );
        businessDTO.setSubscriptionPlan( business.getSubscriptionPlan() );

        return businessDTO;
    }

    @Override
    public Business toEntity(BusinessDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Business.BusinessBuilder business = Business.builder();

        business.businessName( dto.getBusinessName() );
        business.ownerName( dto.getOwnerName() );
        business.contactNumber( dto.getContactNumber() );
        business.email( dto.getEmail() );
        business.password( dto.getPassword() );
        business.status( dto.getStatus() );
        business.subscriptionPlan( dto.getSubscriptionPlan() );

        return business.build();
    }
}
