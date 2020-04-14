package org.galatea.starter.domain.rpsy;

import java.time.LocalDate;
import java.util.List;
import org.galatea.starter.domain.StockPriceData;
import org.springframework.data.repository.CrudRepository;

public interface StockPriceRepository extends CrudRepository<StockPriceData, Long> {

  /*
  * Find by symbol first to determine if the symbol exists in the database
  * @param symbol
   */
  List<StockPriceData> findBySymbol(String symbol);

  /*
   * Find records by symbol and ticker in the database
   * Sort by date asc
   * @param symbol
   * @param date
   */
  List<StockPriceData> findBySymbolAndDateIn(String Symbol, List<LocalDate> date);

}
