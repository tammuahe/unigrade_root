import { useState, useEffect } from "react";
import { api } from "../../services/api";

export default function StudentProfile() {
  const [student, setStudent] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const STUDENT_STATUS_MAP = {
    ACTIVE: "Đang học",
    ON_LEAVE: "Tạm nghỉ",
    SUSPENDED: "Bảo lưu",
    GRADUATED: "Đã tốt nghiệp",
  };

  useEffect(() => {
    loadStudent();
  }, []);

  const loadStudent = async () => {
    try {
      setLoading(true);
      const data = await api.getStudent();
      setStudent(data);
      setError("");
    } catch (err) {
      setError("Không tải được thông tin sinh viên");
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return <div className="text-center py-8">Đang tải thông tin...</div>;
  }

  if (error) {
    return (
      <div className="text-center py-8">
        <p className="text-red-600 mb-4">{error}</p>
        <button
          onClick={loadStudent}
          className="px-4 py-2 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700"
        >
          Thử lại
        </button>
      </div>
    );
  }

  if (!student) {
    return <div className="text-center py-8">Không có thông tin sinh viên</div>;
  }

  const fullName = `${student.firstName ?? ""} ${
    student.lastName ?? ""
  }`.trim();

  return (
    <div className="max-w-4xl mx-auto">
      {/* Header */}
      <div className="flex items-center gap-6 mb-8">
        <div className="w-20 h-20 rounded-full bg-indigo-100 flex items-center justify-center text-3xl font-bold text-indigo-600">
          {student.firstName?.charAt(0) ?? "S"}
        </div>

        <div>
          <h2 className="text-3xl font-bold text-gray-900">
            {fullName || "Hồ sơ sinh viên"}
          </h2>
          <p className="text-gray-500 mt-1">Thông tin cá nhân</p>
        </div>
      </div>

      {/* Info cards */}
      <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
        <Info label="Mã sinh viên" value={student.id} />
        <Info label="Họ" value={student.firstName} />
        <Info label="Tên" value={student.lastName} />
        <Info label="Email" value={student.email} />
        <Info label="Ngày sinh" value={student.dateOfBirth} />
        <Info label="Ngành" value={student.program?.name} />
        <Info
          label="Trạng thái"
          value={STUDENT_STATUS_MAP[student.status] ?? student.status}
        />
      </div>
    </div>
  );
}

function Info({ label, value }) {
  return (
    <div className="bg-white rounded-xl border border-gray-200 p-5 shadow-sm hover:shadow-md transition">
      <p className="text-xs font-semibold text-gray-500 uppercase tracking-wider mb-2">
        {label}
      </p>
      <p className="text-lg font-medium text-gray-900">{value ?? "N/A"}</p>
    </div>
  );
}
