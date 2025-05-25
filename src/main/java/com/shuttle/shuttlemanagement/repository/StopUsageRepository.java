package com.shuttle.shuttlemanagement.repository;

import com.shuttle.shuttlemanagement.entity.StopUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface StopUsageRepository extends JpaRepository<StopUsage, Long> {
    // Group by stop and count usages
    @Query("SELECT s.stop.name, COUNT(s) FROM StopUsage s GROUP BY s.stop.name ORDER BY COUNT(s) DESC")
    List<Object[]> getStopUsageStats();

    @Query("SELECT s.stop.id, COUNT(s) as usage FROM StopUsage s GROUP BY s.stop.id ORDER BY usage DESC")
    List<Object[]> findStopUsageCounts();  ///sort stops by usage count descending.

    boolean existsByUserIdAndStopIdAndTimestampBetween(Long userId, Long stopId, LocalDateTime start, LocalDateTime end);


}

