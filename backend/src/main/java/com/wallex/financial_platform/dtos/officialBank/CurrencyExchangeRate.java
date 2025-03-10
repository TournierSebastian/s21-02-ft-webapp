package com.wallex.financial_platform.dtos.officialBank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyExchangeRate implements Serializable {
    @JsonProperty(value="codigoMoneda")
    public String codigoMoneda;
    @JsonProperty(value="descripcion")
    public String descripcion;
    @JsonProperty(value="tipoPase")
    public BigDecimal tipoPase;
    @JsonProperty(value="tipoCotizacion")
    public BigDecimal tipoCotizacion;
}
