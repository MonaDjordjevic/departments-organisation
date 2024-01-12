package nst.springboot.restexample01.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum AcademicTitle {
    PROFESOR("Profesor"), VANDREDNI_PROFESOR("Vandredni profesor"), DOCENT("Docent"), ASISTENT("Asistent"), SARADNIK_U_NASTAVI("Saradnik u nastavi");

    private final String name;

    public static AcademicTitle fromString(String value) throws Exception {
        return Arrays.stream(values())
                .filter(type -> type.name.equals(value))
                .findFirst()
                .orElseThrow(() -> new Exception("Academic title does not exist: " + value));
    }
}
