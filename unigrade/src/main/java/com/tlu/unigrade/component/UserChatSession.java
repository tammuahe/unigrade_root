package com.tlu.unigrade.component;

import java.util.List;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.google.genai.Chat;
import com.google.genai.Client;
import com.tlu.unigrade.dto.enrollment.EnrollmentDTO;
import com.tlu.unigrade.dto.student.StudentDTO;
import com.tlu.unigrade.service.StudentService;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
@Getter
public class UserChatSession {
    private final Client client;
    private Chat chat;
    private final StudentService studentService;

    private String enrollmentDtoListToString(List<EnrollmentDTO> enrollments) {
        StringBuilder sb = new StringBuilder();

        for (EnrollmentDTO e : enrollments) {
            String semester = e.getSemester().getYear() + " - " + e.getSemester().getSemesterNumber();
            String courseName = e.getCourse().getName();
            String instructor = e.getLecturer().getName();
            String continuous = e.getGrade() != null && e.getGrade().getContinuousGrade() != null
                    ? e.getGrade().getContinuousGrade().toString()
                    : "N/A";
            String midterm = e.getGrade() != null && e.getGrade().getMidtermGrade() != null
                    ? e.getGrade().getMidtermGrade().toString()
                    : "N/A";
            String finalGrade = e.getGrade() != null && e.getGrade().getFinalGrade() != null
                    ? e.getGrade().getFinalGrade().toString()
                    : "N/A";
            int credit = e.getCourse().getCredit();
            String status = e.getStatus() != null ? e.getStatus().name() : "N/A";

            sb.append(String.format(
                    "Semester: %s, Course: %s, Continuous: %s, Midterm: %s, Final: %s, Credit: %d, Status: %s%n, instructor: %s\n",
                    semester, courseName, continuous, midterm, finalGrade, credit, status, instructor));
        }

        return sb.toString();
    }

    private String studentDtoToString(StudentDTO student) {
        if (student == null)
            return "No student data";

        String programName = student.getProgram() != null ? student.getProgram().getName() : "N/A";
        String status = student.getStatus() != null ? student.getStatus().name() : "N/A";

        return String.format(
                "ID: %d%nFirst Name: %s%nLast Name: %s%nEmail: %s%nDate of Birth: %s%nProgram: %s%nStatus: %s%n",
                student.getId(),
                student.getFirstName() != null ? student.getFirstName() : "N/A",
                student.getLastName() != null ? student.getLastName() : "N/A",
                student.getEmail() != null ? student.getEmail() : "N/A",
                student.getDateOfBirth() != null ? student.getDateOfBirth().toString() : "N/A",
                programName,
                status);
    }

    public void init() {
        if (chat != null) return;
        StringBuilder sb = new StringBuilder();
        chat = client.chats.create("gemini-2.5-flash");
        String enrollmentContext = enrollmentDtoListToString(studentService.findAllEnrollments());
        String studentContext = studentDtoToString(studentService.getStudent());
        sb.append("Bạn là một trợ lý trả lời các câu hỏi về điểm của sinh viên. Thông tin sinh viên: \n");
        sb.append(studentContext);
        sb.append("\n Thông tin về điểm của sinh viên:\n");
        sb.append(enrollmentContext);
        sb.append(
                "\nLuôn sử dụng tiếng Việt. Nếu câu trả lời không thể suy ra trực tiếp từ dữ liệu trên, nói với sinh viên điều đó. Cuộc trò chuyện bắt đầu ngay bây giờ.");
        chat.sendMessage(sb.toString());
    }
}
