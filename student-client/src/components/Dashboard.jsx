import { useState } from 'react';
import StudentProfile from './StudentProfile.jsx';
import EnrollmentList from './EnrollmentList.jsx';
import CourseList from './CourseList.jsx';
import ChatPage from './ChatPage.jsx';

export default function Dashboard({ onLogout }) {
  const [activeTab, setActiveTab] = useState('profile');

  // Chat state lifted up here
  const [chatMessages, setChatMessages] = useState([]);
  const [chatLoading, setChatLoading] = useState(false);
  const [chatError, setChatError] = useState("");

  const tabs = [
    { id: 'profile', label: 'Thông tin', icon: '👤' },
    { id: 'enrollments', label: 'Kết quả học tập', icon: '📚' },
    { id: 'courses', label: 'Chương trình đào tạo', icon: '📝' },
    { id: 'chat', label: 'Chat', icon: '💬' },
  ];

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Nav and tab buttons here (same as before) */}

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="flex space-x-1 bg-white p-1 rounded-lg shadow mb-6">
          {tabs.map((tab) => (
            <button
              key={tab.id}
              onClick={() => setActiveTab(tab.id)}
              className={`flex-1 py-2 px-4 rounded-md text-sm font-medium transition ${
                activeTab === tab.id
                  ? 'bg-indigo-600 text-white'
                  : 'text-gray-600 hover:bg-gray-100'
              }`}
            >
              <span className="mr-2">{tab.icon}</span>
              {tab.label}
            </button>
          ))}
        </div>

        <div className="bg-white rounded-lg shadow p-6">
          {activeTab === 'profile' && <StudentProfile />}
          {activeTab === 'enrollments' && <EnrollmentList />}
          {activeTab === 'courses' && <CourseList />}
          {activeTab === 'chat' && (
            <ChatPage
              messages={chatMessages}
              setMessages={setChatMessages}
              loading={chatLoading}
              setLoading={setChatLoading}
              error={chatError}
              setError={setChatError}
            />
          )}
        </div>
      </div>
    </div>
  );
}
