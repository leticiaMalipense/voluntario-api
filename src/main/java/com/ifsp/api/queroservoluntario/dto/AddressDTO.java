package com.ifsp.api.queroservoluntario.dto;


public class AddressDTO {

    private String cep;
    private String street;
    private String complement;
    private String neighborhood;
    private String city;
    private String abbreviation;
    private String stateName;
    private Long stateId;

    public AddressDTO() {
        super();
    }

    public AddressDTO(AddressViaCepDTO dto) {
        this.cep = dto.getCep();
        this.street = dto.getLogradouro();
        this.complement = dto.getComplemento();
        this.neighborhood = dto.getBairro();
        this.city = dto.getLocalidade();
        this.abbreviation = dto.getUf();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }
}
