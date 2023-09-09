package com.example.VSAPIBot.Staff;

import com.example.VSAPIBot.Manager.Manager;
import com.example.VSAPIBot.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByStateAndIdManager(String state, Long idManager);

    List<Staff> findByIdProject(Project projectId);

    Staff findByProjectId(Project projectId);

    Staff save(Staff staff);
}
