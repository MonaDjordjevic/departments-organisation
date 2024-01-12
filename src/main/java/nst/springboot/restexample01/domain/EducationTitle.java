package nst.springboot.restexample01.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum EducationTitle {
    MASTER("Master"), DOKTOR_NAUKA("Doktor nauka"), DIPLOMIRANI_INZENJER("Diplomirani inzenjer"), MAGISTAR("Magistar"), SPECIJALISTA_INFORMATICAR("Specijalista informaticar");

    private final String name;

    public static EducationTitle fromString(String value) throws Exception {
        return Arrays.stream(values())
                .filter(type -> type.name.equals(value))
                .findFirst()
                .orElseThrow(() -> new Exception("Education title does not exist: " + value));
    }
}
