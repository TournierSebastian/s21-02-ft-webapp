package com.wallex.financial_platform.dtos.officialBank;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StadisticVariableResponseDto {
    @JsonProperty(value="idVariable", required = true)
    private String idVariable;
    @JsonProperty(value="descripcion")
    private String descripcion;
    @JsonProperty(value="categoria")
    private String categoria;
    @JsonProperty(value="fecha", required = true)
    private Date fecha;
    @JsonProperty(value="valor", required = true)
    private Double valor;
}
