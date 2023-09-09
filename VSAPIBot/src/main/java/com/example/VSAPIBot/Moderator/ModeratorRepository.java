package com.example.VSAPIBot.Moderator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, String> {

    Optional<Moderator> findById(Long loginModerator);

    Moderator save(Moderator moderator);

}