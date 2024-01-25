package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.HeadHistory;
import nst.springboot.restexample01.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HeadHistoryRepository<T extends History> extends JpaRepository<HeadHistory, Long> {

    List<T> findByDepartmentIdOrderByStartDate(Long departmentId);

    List<T> findByDepartmentIdAndStartDateBeforeOrderByStartDate(Long departmentId, LocalDate startDate);

    public <S extends History> S save(S entity);
}
