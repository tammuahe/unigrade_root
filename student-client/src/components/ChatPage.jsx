import { useState, useRef, useEffect } from "react";
import { api } from "../../services/api";
import Markdown from "react-markdown";

export default function ChatPage({
  messages,
  setMessages,
  loading,
  setLoading,
  error,
  setError,
}) {
  const [input, setInput] = useState("");
  const messagesEndRef = useRef(null);

  // Scroll to bottom on new message
  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!input.trim()) return;
    const userMessage = { role: "user", content: input.trim() };
    setMessages((prev) => [...prev, userMessage]);
    setInput("");
    setLoading(true);
    setError("");

    try {
      const chatResponse = await api.chat(userMessage.content);
      setMessages((prev) => [...prev, chatResponse]);
    } catch (err) {
      setError("Lỗi khi gửi tin nhắn: " + (err.message || err.toString()));
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-3xl mx-auto p-6 flex flex-col h-screen bg-gray-50">
      <h1 className="text-3xl font-bold mb-6 text-indigo-700">
        Chat với trợ lý
      </h1>

      <div
        className="flex-1 overflow-y-auto bg-white rounded-2xl shadow-md p-6 mb-6 flex flex-col gap-4"
        style={{ minHeight: 0 }}
      >
        {messages.length === 0 && (
          <p className="text-gray-400 italic text-center mt-10 select-none">
            Bắt đầu trò chuyện...
          </p>
        )}

        {messages.map((msg, idx) => (
          <div
            key={idx}
            className={`max-w-[70%] px-5 py-3 rounded-2xl shadow-sm wrap-break-word
              ${
                msg.role === "user"
                  ? "bg-indigo-100 text-indigo-900 self-end rounded-br-none"
                  : "bg-purple-100 text-purple-900 self-start rounded-bl-none"
              }`}
          >
            <Markdown>{msg.content}</Markdown>
          </div>
        ))}

        <div ref={messagesEndRef} />
      </div>

      {error && (
        <p className="text-red-600 mb-4 text-center font-semibold select-none">
          {error}
        </p>
      )}

      <form onSubmit={handleSubmit} className="flex gap-3">
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Nhập tin nhắn..."
          className="grow border border-gray-300 rounded-xl px-5 py-3
                     focus:outline-none focus:ring-2 focus:ring-indigo-500
                     transition disabled:bg-gray-100 disabled:cursor-not-allowed"
          disabled={loading}
          autoComplete="off"
        />
        <button
          type="submit"
          disabled={loading}
          className="px-6 py-3 rounded-xl bg-linear-to-r from-indigo-600 to-purple-600
                     text-white font-semibold hover:from-indigo-700 hover:to-purple-700
                     transition disabled:opacity-60 disabled:cursor-not-allowed"
        >
          {loading ? "Đang gửi..." : "Gửi"}
        </button>
      </form>
    </div>
  );
}
