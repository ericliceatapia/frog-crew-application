package edu.cs.tcu.frog_crew_application.repository;

import edu.cs.tcu.frog_crew_application.entity.CrewAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewAssignmentRepository extends JpaRepository<CrewAssignment, Long> {

    // Custom finder: Find all crew assignments by opponent name (or you can modify to find by game ID later)
    List<CrewAssignment> findByOpponent(String opponent);
}
