package nst.springboot.restexample01.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "personal_no")
    private String personalNo;

    @Column(name = "academic_title")
    private String academicTitle;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "education_title")
    private String educationTitle;

    @Column(name = "scientific_field")
    private String scientificField;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "member_role")
    private MemberRole memberRole = MemberRole.OTHER;
}
