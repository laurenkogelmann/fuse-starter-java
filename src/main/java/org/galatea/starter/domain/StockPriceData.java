package org.galatea.starter.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.text.DateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@Entity
public class StockPriceData {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id;

  @NonNull
  @Column(name="STOCK_SYMBOL", length = 5, nullable = false, unique = false)
  protected String symbol;

  @NonNull
  @Column(name="DATE", length = 10, nullable = false, unique = false)
  protected LocalDate date;

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

  DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
  String strDate = dateFormat.format(date);

  /*Need a unique ID to ensure we don't insert duplicate records into our table
  * Unique ID will consist of a combination of the date and symbol
  */
  @NonNull
  @Column(name="UNIQUE_ID", length = 50, nullable = false, unique = true)
  protected String uniqueId = strDate + symbol;

}
