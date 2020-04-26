package pokerequitychallenge.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pokerequitychallenge.EquityRequest;
import pokerequitychallenge.service.EquityService;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("equity")
@RequiredArgsConstructor
public class EquityController {

    private final EquityService equityService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<Integer, Float> calculateEquity(@NotNull @RequestBody EquityRequest equityRequest) {
        long start = System.currentTimeMillis();
        Map<Integer, Float> result = equityService.calculateEquity(equityRequest);
        log.info("duration: " + (System.currentTimeMillis() - start));
        return result;
    }
}