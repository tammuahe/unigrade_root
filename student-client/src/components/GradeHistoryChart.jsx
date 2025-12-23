import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  ResponsiveContainer,
} from "recharts";

function GradeHistoryChart({ data }) {
  if (!data || data.length === 0) return null;

  return (
    <div className="bg-white rounded-xl border p-6 mb-8">
      <h3 className="text-xl font-bold mb-4">Lịch sử điểm học tập</h3>

      <ResponsiveContainer width="100%" height={300}>
        <LineChart data={data}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="semester" />
          <YAxis domain={[0, 10]} />
          <Tooltip />
          <Line
            type="monotone"
            dataKey="cpa"
            stroke="#6366f1"
            strokeWidth={3}
            dot={{ r: 4 }}
          />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
}

export default GradeHistoryChart;
