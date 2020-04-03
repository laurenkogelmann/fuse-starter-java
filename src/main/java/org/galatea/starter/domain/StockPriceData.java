package org.galatea.starter.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@Entity
@IdClass(StockPriceData.class)
@Table(name="STOCK_PRICE_TABLE")
public class StockPriceData implements Serializable {

  @Id
  @Column(name="STOCK_SYMBOL", length = 5, nullable = false, unique = false)
  protected String symbol;

  @Id
  @Column(name="DATE", length = 10, nullable = false, unique = false)
  protected Date date;

  @NonNull
  @Column(name="OPEN_PRICE", length = 20, nullable = false, unique = false)
  protected BigDecimal open;

  @NonNull
  @Column(name="HIGH_PRICE", length = 20, nullable = false, unique = false)
  protected BigDecimal high;

  @NonNull
  @Column(name="LOW_PRICE", length = 20, nullable = false, unique = false)
  protected BigDecimal low;

  @NonNull
  @Column(name="CLOSE_PRICE", length = 20, nullable = false, unique = false)
  protected BigDecimal close;

}
