package com.example.VSAPIBot.Staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByState(String state);

    Staff findByProjectId(Long projectId);

    Staff save(Staff staff);
}
