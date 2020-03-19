package org.galatea.starter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import javax.persistence.Entity;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Getter

public class AlphaVantageResponse {

  //Within the Av response we have Meta Data and Time Series Data, and within Time Series Data we have price data
  @JsonProperty("Meta Data")
  private MetaData metaData;

  @JsonProperty("Time Series (Daily)")
  private Map<String,Object> timeSeriesData;

}
