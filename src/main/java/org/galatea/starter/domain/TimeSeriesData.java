package org.galatea.starter.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeSeriesData {

  @JsonFormat(
      shape = Shape.STRING,
      pattern = "yyyy-MM-dd")
  @JsonProperty("yyyy-MM-dd")
  private Date priceDate;

}
