package org.galatea.starter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class AlphaVantageResponse {

  //Within the Av response we have Meta Data and Time Series Data, and within Time Series Data we have price data
  @JsonProperty("Meta Data")
  private MetaData metaData;

  @JsonProperty("Time Series (Daily)")
  private Map<String,Object> timeSeriesData;

}
