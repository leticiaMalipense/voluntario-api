package com.ifsp.api.queroservoluntario.service;

import com.ifsp.api.queroservoluntario.dto.AddressDTO;
import com.ifsp.api.queroservoluntario.dto.AddressViaCepDTO;
import com.ifsp.api.queroservoluntario.entity.AddressEntity;
import com.ifsp.api.queroservoluntario.entity.StateEntity;
import com.ifsp.api.queroservoluntario.repository.AddressRepository;
import com.ifsp.api.queroservoluntario.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Value("${url.viacep}")
    private String urlViaCep;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StateRepository stateRepository;

    @Cacheable("cep")
    public AddressDTO getByCep(String cep) {
        String url = urlViaCep.replace("{cep}", cep);
        try {
            ResponseEntity<AddressViaCepDTO> forEntity = restTemplate.getForEntity(url, AddressViaCepDTO.class);
            AddressDTO address = new AddressDTO(forEntity.getBody());

            StateEntity state = stateRepository.findByAbbreviation(address.getAbbreviation());
            address.setStateName(state.getName());
            address.setStateId(state.getId());

            return address;
        } catch (Exception e) {
            return null;
        }
    }

    public void save(AddressEntity addressModel) {
        addressRepository.save(addressModel);
    }

    public void findById(Long id) {
        addressRepository.findById(id);
    }
}
