package nst.springboot.restexample01.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_head_history")
public class HeadHistory extends History {

    @ManyToOne
    @JoinColumn(name = "head_id")
    private Member head;
}

