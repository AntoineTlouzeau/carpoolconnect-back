package com.incubateur.carpoolconnect.repositories;

import com.incubateur.carpoolconnect.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
