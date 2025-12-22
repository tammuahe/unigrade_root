import { useState, useEffect } from "react";
import { api } from "../../services/api";

export default function CourseList() {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [filter, setFilter] = useState("");

  useEffect(() => {
    loadCourses();
  }, []);

  const loadCourses = async () => {
    try {
      setLoading(true);
      const data = await api.getRequiredCourses();
      setCourses(data);
      setError("");
    } catch (err) {
      setError("Không tải được danh sách môn học");
    } finally {
      setLoading(false);
    }
  };

  const filteredCourses = courses.filter((course) =>
    `${course.code} ${course.name}`
      .toLowerCase()
      .includes(filter.toLowerCase())
  );

  if (loading) {
    return <div className="text-center py-10">Đang tải danh sách môn học...</div>;
  }

  if (error) {
    return (
      <div className="text-center py-10">
        <p className="text-red-600 mb-4">{error}</p>
        <button
          onClick={loadCourses}
          className="px-4 py-2 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700"
        >
          Thử lại
        </button>
      </div>
    );
  }

  return (
    <div className="max-w-7xl mx-auto">
      {/* Header */}
      <div className="mb-6">
        <h2 className="text-3xl font-bold text-gray-900">
          Môn học bắt buộc
        </h2>
        <p className="text-gray-500 mt-1">
          Các môn học sinh viên phải hoàn thành để tốt nghiệp
        </p>
      </div>

      {courses.length === 0 ? (
        <div className="bg-white rounded-xl border border-gray-200 p-10 text-center text-gray-500">
          Không có môn học bắt buộc
        </div>
      ) : (
        <div className="overflow-x-auto bg-white rounded-xl border border-gray-200 shadow-sm">
          {/* Filter */}
          <div className="mb-4 flex justify-end">
            <input
              type="text"
              value={filter}
              onChange={(e) => setFilter(e.target.value)}
              placeholder="Lọc theo mã hoặc tên môn học..."
              className="w-64 px-4 py-2 m-2 border border-gray-300 rounded-lg
                         focus:outline-none focus:ring-2 focus:ring-indigo-500"
            />
          </div>

          {/* Table */}
          <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-500 uppercase">
                  ID
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-500 uppercase">
                  Mã môn
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-500 uppercase">
                  Tên môn học
                </th>
                <th className="px-6 py-3 text-left text-xs font-semibold text-gray-500 uppercase">
                  Số tín chỉ
                </th>
              </tr>
            </thead>

            <tbody className="divide-y divide-gray-200">
              {filteredCourses.map((course) => (
                <tr key={course.id} className="hover:bg-gray-50 transition">
                  <td className="px-6 py-4 text-sm font-medium text-gray-900">
                    {course.id}
                  </td>
                  <td className="px-6 py-4 text-sm text-gray-700">
                    {course.code}
                  </td>
                  <td className="px-6 py-4 text-sm font-semibold text-gray-900">
                    {course.name}
                  </td>
                  <td className="px-6 py-4 text-sm text-gray-700">
                    {course.credit}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
