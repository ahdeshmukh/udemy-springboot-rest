package com.deshmukhamit.udemyspringbootrest.todos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Query("SELECT t FROM Todo t WHERE t.user.id = :uid")
    List<Todo> findByUserId(@Param("uid") int uid);

    //@Query("SELECT t FROM Staff t WHERE t.reportingOfficer.id = :reportingOfficerId")
    //public List<Todo> findStaffsWithSameReportingOfficer(@Param("reportingOfficerId") Integer reportingOfficerId);
}
