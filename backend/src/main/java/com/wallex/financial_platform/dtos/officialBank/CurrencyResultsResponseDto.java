package com.wallex.financial_platform.dtos.officialBank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResultsResponseDto implements Serializable {
    @JsonProperty(value="fecha")
    public Date fecha;
    @JsonProperty(value="detalle")
    public List<CurrencyExchangeRate> detalle;
}
