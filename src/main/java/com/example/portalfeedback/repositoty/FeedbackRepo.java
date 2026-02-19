package com.example.portalfeedback.repositoty;

import com.example.portalfeedback.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, String> {


    @Override
    Optional<Feedback> findById(String s);
    //	@Query("SELECT t FROM Task t WHERE t.user.id = :userId")
    List<Feedback> findByUser_Id(@Param("userId") String userId);

}
