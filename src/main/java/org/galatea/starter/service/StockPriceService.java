package org.galatea.starter.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.galatea.starter.domain.AlphaVantageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A layer for transformation, aggregation, and business required when retrieving data from Alpha Vantage.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StockPriceService {

  @NonNull
  @Autowired
  private StockPriceClient stockPriceClient;

  /**
   * Get the prices for the Symbol that is passed in.
   *
   * //@param days the number of days to get prices for that ticker.
   * @param symbol the symbol to get open, high, low and close prices for.
   * @return a list of prices objects for that symbol for only the specified.
   */

  public AlphaVantageResponse getPricesForSymbolForLastNDays(final String symbol) {
    return stockPriceClient.getPricesForSymbolForLastNDays(symbol);
  }

}
