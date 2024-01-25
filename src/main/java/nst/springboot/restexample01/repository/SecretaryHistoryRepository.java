package nst.springboot.restexample01.repository;

import jakarta.validation.constraints.PastOrPresent;
import nst.springboot.restexample01.domain.History;
import nst.springboot.restexample01.domain.SecretaryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SecretaryHistoryRepository<T extends History> extends JpaRepository<SecretaryHistory, Long> {

    List<SecretaryHistory> findSecretaryHistoriesByDepartmentIdOrderByStartDate(Long departmentId);

    List<SecretaryHistory> findByDepartmentIdAndStartDateBeforeOrderByStartDate(Long departmentId, @PastOrPresent LocalDate startDate);

    public <S extends History> S save(S entity);

}
