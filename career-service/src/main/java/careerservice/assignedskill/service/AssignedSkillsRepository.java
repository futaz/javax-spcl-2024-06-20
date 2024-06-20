package careerservice.assignedskill.service;

import careerservice.assignedskill.model.AssignedSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AssignedSkillsRepository extends JpaRepository<AssignedSkills, Long> {

    @Query("select distinct s from AssignedSkills s join fetch s.leveledSkills")
    Optional<AssignedSkills> findByEmployeeId(long employeeId);
}
