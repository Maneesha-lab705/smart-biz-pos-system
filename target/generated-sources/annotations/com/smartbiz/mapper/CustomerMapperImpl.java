package com.smartbiz.mapper;

import com.smartbiz.dto.CustomerDTO;
import com.smartbiz.entity.Business;
import com.smartbiz.entity.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-21T22:48:51+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO toDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setBusinessId( customerBusinessBusinessId( customer ) );
        customerDTO.setBusinessName( customerBusinessBusinessName( customer ) );
        customerDTO.setCustomerId( customer.getCustomerId() );
        customerDTO.setName( customer.getName() );
        customerDTO.setEmail( customer.getEmail() );
        customerDTO.setPhone( customer.getPhone() );

        return customerDTO;
    }

    @Override
    public Customer toEntity(CustomerDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.name( dto.getName() );
        customer.email( dto.getEmail() );
        customer.phone( dto.getPhone() );

        return customer.build();
    }

    private Long customerBusinessBusinessId(Customer customer) {
        if ( customer == null ) {
            return null;
        }
        Business business = customer.getBusiness();
        if ( business == null ) {
            return null;
        }
        Long businessId = business.getBusinessId();
        if ( businessId == null ) {
            return null;
        }
        return businessId;
    }

    private String customerBusinessBusinessName(Customer customer) {
        if ( customer == null ) {
            return null;
        }
        Business business = customer.getBusiness();
        if ( business == null ) {
            return null;
        }
        String businessName = business.getBusinessName();
        if ( businessName == null ) {
            return null;
        }
        return businessName;
    }
}
