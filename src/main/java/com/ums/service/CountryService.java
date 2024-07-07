package com.ums.service;


import com.ums.entity.Country;
import com.ums.payload.CountryDto;
import com.ums.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository crep;
  public CountryDto convertToDto(Country country) {
      CountryDto dto=new CountryDto();
      dto.setId(country.getId());
      dto.setCountryName(country.getCountryName());
      return dto;
  }

  public List<CountryDto> findAllCountries(){
      List<Country> all = crep.findAll();
      List<CountryDto> collect = all.stream().map(e -> convertToDto(e)).collect(Collectors.toList());
      return collect;
  }
}
