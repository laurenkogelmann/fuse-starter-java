package org.galatea.starter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlphaVantageResponse {

  @JsonProperty("Meta Data")
  private MetaData metaData;

  @JsonProperty("Time Series (Daily)")
  private Map<String,Object> timeSeriesData;

}
