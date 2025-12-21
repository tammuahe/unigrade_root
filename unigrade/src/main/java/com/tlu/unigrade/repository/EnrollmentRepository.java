package com.tlu.unigrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tlu.unigrade.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    @Query("""
                select e
                from Enrollment e
                where e.student.id = :studentId
            """)
    List<Enrollment> findAllByStudentId(Long studentId);

    @Query("""
                select e
                from Enrollment e
                where e.student.id = :studentId
                  and (
                       lower(e.section.course.name) like lower(concat('%', :keyword, '%'))
                    or lower(e.section.course.code) like lower(concat('%', :keyword, '%'))
                  )
            """)
    List<Enrollment> searchByCourseNameOrCode(
            @Param("studentId") Long studentId,
            @Param("keyword") String keyword);

}
