package com.wallex.financial_platform.dtos.officialBank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyExchangeResponseDto implements Serializable {
    @JsonProperty(value="status")
    public Integer status;
    @JsonProperty(value="metadata", required = false)
    public Map<String, Map<String, ?>> metadata;
    @JsonProperty(value="results")
    public CurrencyResultsResponseDto results;
}
