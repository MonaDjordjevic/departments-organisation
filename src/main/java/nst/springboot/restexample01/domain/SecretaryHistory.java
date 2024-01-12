package nst.springboot.restexample01.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_secretary_history")
public class SecretaryHistory extends History {

    @ManyToOne
    @JoinColumn(name = "secretary_id")
    private Member secretary;
}

