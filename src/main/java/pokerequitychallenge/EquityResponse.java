package pokerequitychallenge;

import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@AllArgsConstructor
public class EquityResponse {

    @NotNull
    @Size(max = 10)
    Map<Integer, Float> equities;
}
