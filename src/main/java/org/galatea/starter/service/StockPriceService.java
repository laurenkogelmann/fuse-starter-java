package org.galatea.starter.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.galatea.starter.domain.AlphaVantageResponse;
import org.galatea.starter.domain.MetaData;
import org.galatea.starter.domain.PriceData;
import org.galatea.starter.domain.StockPriceData;
import org.galatea.starter.domain.rpsy.StockPriceRepository;
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
  StockPriceRepository stockPriceRepository;

  @NonNull
  @Autowired
  private StockPriceClient stockPriceClient;

  /**
   * Get the prices for the Symbol that is passed in.
   *
   * @param symbol the symbol to get open, high, low and close prices for.
   * @return a list of prices objects for that symbol
   */
  public AlphaVantageResponse getPricesForSymbolForLastNDays(final String symbol) {
    return stockPriceClient.getPricesForSymbolForLastNDays(symbol);
  }

  public List<StockPriceData> mapAlphaVantageResponseToStockPriceData(AlphaVantageResponse alphaVantageResponse){
    List<StockPriceData> allStockData = new ArrayList<>();
    MetaData metaData = alphaVantageResponse.getMetaData();
    for (Map.Entry timeSeriesElement : alphaVantageResponse.getTimeSeriesData().entrySet()) {
      Date entryDate = (Date) timeSeriesElement.getKey();
      PriceData priceData = (PriceData) timeSeriesElement.getValue();
      StockPriceData thisStockData = StockPriceData.builder().symbol(metaData.getSymbol()).date(entryDate).open(priceData.getOpen()).high(priceData.getHigh()).low(priceData.getLow()).close(priceData.getClose()).build();
      allStockData.add(thisStockData);
    }
    stockPriceRepository.saveAll(allStockData);
    return allStockData;
  }

}