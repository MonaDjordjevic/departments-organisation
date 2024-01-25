package nst.springboot.restexample01.repository;

import jakarta.validation.constraints.PastOrPresent;
import nst.springboot.restexample01.domain.AcademicTitleHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author student2
 */
public interface AcademicTitleHistoryRepository extends JpaRepository<AcademicTitleHistory, Long> {
    List<AcademicTitleHistory> findByMemberIdOrderByStartDate(Long memberId);

    List<AcademicTitleHistory> findByMemberIdAndStartDateBeforeOrderByStartDate(Long member_id, @PastOrPresent LocalDate startDate);
}
