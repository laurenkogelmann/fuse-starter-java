package org.galatea.starter.service;

import java.time.LocalDate;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.galatea.starter.domain.AlphaVantageResponse;
import org.galatea.starter.domain.StockPriceData;
import org.galatea.starter.domain.rpsy.StockPriceRepository;
import org.galatea.starter.utils.DateTime;
import org.springframework.stereotype.Service;

/**
 * A layer for transformation, aggregation, and business required when retrieving data from Alpha
 * Vantage.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StockPriceService {

  @NonNull
  StockPriceRepository stockPriceRepository;

  @NonNull
  private StockPriceClient stockPriceClient;

  DateTime dateTime;

  /**
   * Get the prices for the Symbol that is passed in.
   *
   * @param symbol the symbol to get open, high, low and close prices for.
   * @return a list of prices objects for that symbol for only the specified.
   */

  public AlphaVantageResponse getPricesForSymbolForLastNDays(final String symbol) {
    return stockPriceClient.getPricesForSymbolForLastNDays(symbol);
  }

  public List<StockPriceData> returnPricesToClient(String symbol, int days) {

    List<LocalDate> getStockDates = dateTime.getDatesNeededForStockData(days);
    List<StockPriceData> stockDatesInDatabase = stockPriceRepository.findBySymbolAndDateIn(symbol, getStockDates);

    if (stockPriceRepository.findBySymbol(symbol) == null) {
      log.info("Ticker was not found in database, querying Alpha Vantage.");
      getPricesForSymbolForLastNDays(symbol);
    } else if (stockDatesInDatabase.size() == getStockDates.size()) {
      log.info("The ticker was found in the database, and all price data exists.");
      return stockPriceRepository.findBySymbolAndDateIn(symbol, getStockDates);
    } else if (stockDatesInDatabase.size() != getStockDates.size()){
      log.info("Not all prices were in the database for the given dates, querying Alpha Vantage.");
      getPricesForSymbolForLastNDays(symbol);
    }
    return stockDatesInDatabase;
  }

}

