const API_BASE_URL = 'http://localhost:8080';

const getAuthHeader = () => {
  const credentials = localStorage.getItem('credentials');
  return credentials ? { 'Authorization': `Basic ${credentials}` } : {};
};

export const api = {
  test: async () => {
    const response = await fetch(`${API_BASE_URL}/student/test`, {
      method: 'GET',
      credentials: 'include',
      headers: {
        ...getAuthHeader(),
        'Content-Type': 'application/json'
      }
    });
    if (!response.ok) throw new Error('Test failed');
    return response.text();
  },

  getCompletion: async () => {
    const response = await fetch(`${API_BASE_URL}/student/completion`, {
      method: 'GET',
      credentials: 'include',
      headers: {
        ...getAuthHeader(),
        'Content-Type': 'application/json'
      }
    });
    if (!response.ok) throw new Error('Failed to fetch completion');
    return response.json();
  },
  getCpa: async () => {
    const response = await fetch(`${API_BASE_URL}/student/cpa`, {
      method: 'GET',
      credentials: 'include',
      headers: {
        ...getAuthHeader(),
        'Content-Type': 'application/json'
      }
    });
    if (!response.ok) throw new Error('Failed to fetch cpa');
    return response.json();
  },

  getStudent: async () => {
    const response = await fetch(`${API_BASE_URL}/student`, {
      method: 'GET',
      credentials: 'include',
      headers: {
        ...getAuthHeader(),
        'Content-Type': 'application/json'
      }
    });
    if (!response.ok) throw new Error('Failed to fetch student');
    return response.json();
  },

  getEnrollments: async (keyword = '') => {
    const url = keyword 
      ? `${API_BASE_URL}/student/enrollment?keyword=${encodeURIComponent(keyword)}`
      : `${API_BASE_URL}/student/enrollment`;
    
    const response = await fetch(url, {
      method: 'GET',
      credentials: 'include',
      headers: {
        ...getAuthHeader(),
        'Content-Type': 'application/json'
      }
    });
    if (!response.ok) throw new Error('Failed to fetch enrollments');
    return response.json();
  },

  getRequiredCourses: async () => {
    const response = await fetch(`${API_BASE_URL}/student/course`, {
      method: 'GET',
      credentials: 'include',
      headers: {
        ...getAuthHeader(),
        'Content-Type': 'application/json'
      }
    });
    if (!response.ok) throw new Error('Failed to fetch courses');
    return response.json();
  }
};