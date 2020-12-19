package jpa.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends JpaRepository<Line, Long> {
    @Modifying
    @Query(value = "DELETE FROM Line line WHERE line.id = :id",
            nativeQuery = true)
    void deleteLineBy(@Param("id") Long id);

    @Query(value = "SELECT * FROM Line line WHERE line.id = :id",
            nativeQuery = true)
    Line findLineById(@Param("id") Long id);
}
