package com.copsdbtest.data;

import com.copsdbtest.dto.SupplierDto;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;

@Slf4j
public class SupplierData {

    private Faker faker = new Faker();

    @Description("generate supplier data")
    public SupplierDto createSupplierData() {
        log.info("createSupplierData::createSupplierData");
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierId(faker.number().numberBetween(1, 99));
        supplierDto.setSupplierName(faker.company().name());
        supplierDto.setContactName(faker.name().fullName());
        supplierDto.setAddress(faker.address().fullAddress());
        supplierDto.setCity(faker.address().city());
        supplierDto.setPostalCode(faker.address().zipCode());
        supplierDto.setCountry(faker.country().name());
        return supplierDto;
    }
}
