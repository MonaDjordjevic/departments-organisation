package nst.springboot.restexample01.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ScientificField {
    SOFTVERSKO_INZENJERSTVO("Softversko inzenjerstvo"), MASINSKO_UCENJE("Masinsko ucenje"), VESTACKA_INTALIGENCIJA("Vestacka intaligencija"), BAZE_PODATAKA("Baze podataka"), RACUNARSTVO("Racunarstvo");

    private final String name;

    public static ScientificField fromString(String value) throws Exception {
        return Arrays.stream(values())
                .filter(type -> type.name.equals(value))
                .findFirst()
                .orElseThrow(() -> new Exception("Education title does not exist: " + value));
    }
}
