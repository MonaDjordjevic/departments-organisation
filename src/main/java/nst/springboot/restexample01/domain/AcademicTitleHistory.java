package nst.springboot.restexample01.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_academic_title_history")
public class AcademicTitleHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "scientific_field")
    private String scientificField;

    @Column(name = "academic_title")
    private String academicTitle;
}
