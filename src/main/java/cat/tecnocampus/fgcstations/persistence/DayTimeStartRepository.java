package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.PopularDayOfWeek;
import cat.tecnocampus.fgcstations.domain.DayTimeStart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DayTimeStartRepository extends JpaRepository<DayTimeStart, String> {
    @Query(value = "SELECT day_of_week as dayOfWeek, COUNT(time_start) as count " +
            "FROM day_time_start " +
            "GROUP BY day_of_week " +
            "ORDER BY count DESC", nativeQuery = true)
    List<PopularDayOfWeek> findPopularDayOfWeek();
}
