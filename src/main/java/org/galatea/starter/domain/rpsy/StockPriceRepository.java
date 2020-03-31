package org.galatea.starter.domain.rpsy;

import java.time.LocalDate;
import java.util.List;
import java.util.Date;
import org.galatea.starter.domain.StockPriceTable;
import org.springframework.data.repository.CrudRepository;

public interface StockPriceRepository extends CrudRepository<StockPriceTable, Long> {

  /*
  * Find by symbol first to determine if the symbol exists in the database
  * @param symbol
   */
  List<StockPriceTable> findBySymbol(String symbol);

  /*
   * Find records by symbol and ticker in the database
   * Sort by date asc
   * @param symbol
   * @param date
   */
  List<StockPriceTable> findBySymbolAndDateIn(String Symbol, List<Date> date);

}
