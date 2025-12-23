package com.tlu.unigrade.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlu.unigrade.dto.chat.ChatDTO;
import com.tlu.unigrade.dto.course.CourseDTO;
import com.tlu.unigrade.dto.enrollment.EnrollmentDTO;
import com.tlu.unigrade.dto.student.StudentDTO;
import com.tlu.unigrade.service.ChatService;
import com.tlu.unigrade.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final ChatService chatService;

    @GetMapping("/test")
    public String test() {
        return "omfg it worked";
    }

    @GetMapping
    public ResponseEntity<StudentDTO> getStudent() {
        return ResponseEntity.ok(studentService.getStudent());
    }

    @GetMapping("/cpa")
    public BigDecimal getCpa() {
        return studentService.getCPA();
    }

    @GetMapping("/enrollment")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollments(@RequestParam(required = false) String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return ResponseEntity.ok(studentService.findAllEnrollments());
        } else {
            return ResponseEntity.ok(studentService.searchEnrollment(keyword));
        }
    }

    @GetMapping("/course")
    public ResponseEntity<List<CourseDTO>> getRequiredCourse() {
        return ResponseEntity.ok(studentService.findAllRequiredCourses());
    }

    @GetMapping("/completion")
    public BigDecimal getCompletion() {
        return studentService.getCompletionPercentage();
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatDTO> chat(@RequestBody String message) {
        return ResponseEntity.ok(chatService.chat(message));
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

}
