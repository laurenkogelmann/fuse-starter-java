package org.galatea.starter.entrypoint;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Positive;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.aspect4log.Log;
import net.sf.aspect4log.Log.Level;
import org.galatea.starter.domain.StockPriceTable;
import org.galatea.starter.service.StockPriceService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Log(enterLevel = Level.INFO, exitLevel = Level.INFO)
@Validated
@RestController
@RequiredArgsConstructor

public class StockPriceRestController {
  @NonNull
  private StockPriceService StockPriceService;

  /**
   * Get the prices for the last N days for the symbol passed in.
   *
   * @param symbol to get open, high, low and close prices for.
   * @param days number of days to get these prices for.
   * @return a List of StockPrice objects for the given symbol for the given number of days.
   */
  @GetMapping(value = "${mvc.av.getAllPricesPath}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public List<StockPriceTable> getTickerPrices(
      @RequestParam (value = "symbol")
      @NotEmpty (message = "Stock symbol must be populated.")  //Check that Symbol is populated
      @Size(min=1, max=5, message = "Stock symbol must be between 1 and 5 characters.") //Check that Symbol is valid # of chars
      @Pattern(regexp = "[a-zA-Z0-9]+", message = "Stock symbol must be alphanumeric.") //Check that Symbol is alphanumeric
      final List<String> symbol,
      @RequestParam(value = "days")
      @NotEmpty (message = "Number of days must be populated.")  //Check that days is populated
      @Pattern(regexp = "[0-9]+", message = "Number of days must be a numeric value.") //Check that days is a number
      @Positive(message = "Number of Days must be a positive number. Zero is invalid.") //Check that days is positive
      final List<String> days)
  {
    return StockPriceService.getPricesForSymbolForLastNDays(symbol, days);
    log.info("Received a valid request from the client.");
  }

}
