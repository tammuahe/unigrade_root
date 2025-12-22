import { useEffect, useState } from "react";
import { api } from "../../services/api";
import GradeHistoryChart from "./GradeHistoryChart";

export default function EnrollmentList() {
  const [enrollments, setEnrollments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [keyword, setKeyword] = useState("");
  const [cpa, setCpa] = useState(0);
  const [completion, setCompletion] = useState(0);

  const STATUS_MAP = {
    IN_PROGRESS: "Đang học",
    COMPLETED: "Hoàn thành",
    RETAKE_REQUIRED: "Phải học lại",
    CANCELLED: "Đã huỷ",
  };

  useEffect(() => {
    loadEnrollments();
  }, []);

  const loadEnrollments = async () => {
    try {
      setLoading(true);
      const data = await Promise.all([
        api.getEnrollments(),
        api.getCpa(),
        api.getCompletion(),
      ]);
      setCpa(data[1]);
      setEnrollments(data[0]);
      setCompletion(data[2]);
      setError("");
    } catch (err) {
      setError("Không tải được kết quả học tập " + err);
    } finally {
      setLoading(false);
    }
  };

  const semesterMap = {};

  enrollments.forEach((e) => {
    if (!e.grade?.finalGrade || !e.course?.credit || !e.semester) return;

    const key = `${e.semester.semesterNumber}_${e.semester.year}`;

    if (!semesterMap[key]) {
      semesterMap[key] = {
        semester: `${e.semester.semesterNumber.replace("_", " ")} ${
          e.semester.year
        }`,
        totalScore: 0,
        totalCredits: 0,
      };
    }

    semesterMap[key].totalScore += e.grade.finalGrade * e.course.credit;

    semesterMap[key].totalCredits += e.course.credit;
  });

  const gradeHistory = Object.values(semesterMap)
    .map((s) => ({
      semester: s.semester,
      cpa: Number((s.totalScore / s.totalCredits).toFixed(2)),
      year: Number(s.semester.split(" ").pop()),
      semesterNumber: s.semester.split(" ")[0].toUpperCase(),
    }))
    .sort((a, b) => {
      if (a.year !== b.year) return a.year - b.year;
      return semesterOrder[a.semesterNumber] - semesterOrder[b.semesterNumber];
    });

  const handleSearch = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      const data = await api.getEnrollments(keyword);
      setEnrollments(data);
      setError("");
    } catch (err) {
      setError("Tìm kiếm thất bại");
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return <div className="text-center py-10">Đang tải kết quả học tập...</div>;
  }

  if (error) {
    return (
      <div className="text-center py-10">
        <p className="text-red-600 mb-4">{error}</p>
        <button
          onClick={loadEnrollments}
          className="px-4 py-2 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700"
        >
          Thử lại
        </button>
      </div>
    );
  }

  return (
    <div className="max-w-7xl mx-auto">
      {/* CPA Card */}
      <div className="mb-8">
        <div
          className="bg-linear-to-r from-indigo-600 to-purple-600
               rounded-2xl p-6 text-white shadow-lg"
        >
          <p className="text-sm uppercase tracking-wide opacity-90">
            Điểm trung bình tích lũy
          </p>

          <div className="flex items-end gap-3 mt-2">
            <p className="text-5xl font-bold">
              {cpa !== null ? cpa.toFixed(2) : "--"}
            </p>
            <span className="text-lg opacity-90">/ 10.00</span>
          </div>

          <p className="text-sm opacity-80 mt-2">Tính đến thời điểm hiện tại</p>
        </div>
      </div>

      {/* Program Completion */}
      <div className="mb-8">
        <div className="bg-white rounded-2xl p-6 border border-gray-200 shadow-sm">
          <div className="flex justify-between items-center mb-3">
            <p className="text-sm font-semibold text-gray-700">
              Tiến độ hoàn thành chương trình
            </p>
            <p className="text-sm font-bold text-indigo-600">
              {completion.toFixed(1)}%
            </p>
          </div>

          {/* Progress bar */}
          <div className="w-full h-3 bg-gray-200 rounded-full overflow-hidden">
            <div
              className="h-full bg-linear-to-r from-indigo-500 to-purple-500
                   transition-all duration-700"
              style={{ width: `${completion}%` }}
            />
          </div>

          <p className="text-xs text-gray-500 mt-2">
            Dựa trên số tín chỉ đã hoàn thành
          </p>
        </div>
      </div>

      <GradeHistoryChart data={gradeHistory} />

      {/* Header + Search */}
      <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4 mb-8">
        <div>
          <h2 className="text-3xl font-bold text-gray-900">Kết quả học tập</h2>
          <p className="text-gray-500 mt-1">
            Danh sách các môn học bạn đã đăng ký
          </p>
        </div>

        <form onSubmit={handleSearch} className="flex gap-2">
          <input
            type="text"
            value={keyword}
            onChange={(e) => setKeyword(e.target.value)}
            placeholder="Tìm theo tên hoặc mã môn học..."
            className="w-64 px-4 py-2 border border-gray-300 rounded-lg
                       focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />

          <button
            type="submit"
            className="px-4 py-2 bg-indigo-600 text-white rounded-lg
                       hover:bg-indigo-700 transition"
          >
            Tìm kiếm
          </button>

          {keyword && (
            <button
              type="button"
              onClick={() => {
                setKeyword("");
                loadEnrollments();
              }}
              className="px-4 py-2 bg-gray-100 text-gray-700 rounded-lg
                         hover:bg-gray-200 transition"
            >
              Xoá
            </button>
          )}
        </form>
      </div>

      {/* Empty state */}
      {enrollments.length === 0 ? (
        <div className="bg-white rounded-xl border border-gray-200 p-10 text-center text-gray-500">
          Không có kết quả học tập
        </div>
      ) : (
        /* Cards */
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {enrollments.map((enrollment) => (
            <div
              key={enrollment.id}
              className="bg-white rounded-xl border border-gray-200 p-6
                         shadow-sm hover:shadow-lg transition"
            >
              {/* Header */}
              <div className="flex justify-between items-start mb-4">
                <div>
                  <h3 className="text-lg font-bold text-gray-900">
                    {enrollment.course?.name}
                  </h3>
                  <p className="text-sm text-gray-500">
                    Mã môn: {enrollment.course?.code}
                  </p>
                </div>
                <span
                  className="px-3 py-1 rounded-full text-xs font-semibold
                 bg-indigo-100 text-indigo-700"
                >
                  {STATUS_MAP[enrollment.status] ?? enrollment.status}
                </span>
              </div>

              {/* Body */}
              <div className="space-y-4 text-sm">
                {/* Semester */}
                <div>
                  <p className="text-xs uppercase tracking-wide text-gray-500 font-semibold">
                    Học kỳ
                  </p>
                  <p className="font-medium text-gray-900">
                    {enrollment.semester?.semesterNumber.replace("_", " ")} –{" "}
                    {enrollment.semester?.year}
                  </p>
                </div>

                {/* Lecturer */}
                <div>
                  <p className="text-xs uppercase tracking-wide text-gray-500 font-semibold">
                    Giảng viên
                  </p>
                  <p className="font-medium text-gray-900">
                    {enrollment.lecturer?.name ?? "Chưa cập nhật"}
                  </p>
                </div>

                {/* Grade */}
                <div>
                  <p className="text-xs uppercase tracking-wide text-gray-500 font-semibold">
                    Điểm số
                  </p>

                  {enrollment.grade ? (
                    <div className="grid grid-cols-3 gap-2 mt-2 text-center">
                      <div className="bg-gray-50 rounded-lg p-2">
                        <p className="text-xs text-gray-500">Quá trình</p>
                        <p className="font-bold">
                          {enrollment.grade.continuousGrade}
                        </p>
                      </div>
                      <div className="bg-gray-50 rounded-lg p-2">
                        <p className="text-xs text-gray-500">Giữa kỳ</p>
                        <p className="font-bold">
                          {enrollment.grade.midtermGrade}
                        </p>
                      </div>
                      <div className="bg-gray-50 rounded-lg p-2">
                        <p className="text-xs text-gray-500">Cuối kỳ</p>
                        <p className="font-bold">
                          {enrollment.grade.finalGrade}
                        </p>
                      </div>
                    </div>
                  ) : (
                    <p className="italic text-gray-500 mt-1">Chưa có điểm</p>
                  )}
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
