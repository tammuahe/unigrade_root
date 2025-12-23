import React, { useState, useEffect } from "react";

const API_BASE = "http://localhost:8080/admin";

const BASIC_AUTH_USERNAME = "admin";
const BASIC_AUTH_PASSWORD = "admin123";

const entities = ["students", "grades", "enrollments", "courses"];

const createBasicAuthHeader = () => {
  const credentials = `${BASIC_AUTH_USERNAME}:${BASIC_AUTH_PASSWORD}`;
  return `Basic ${btoa(credentials)}`;
};

function AdminApp() {
  const [entityType, setEntityType] = useState("students");
  const [items, setItems] = useState([]);
  const [selectedId, setSelectedId] = useState(null);
  const [selectedItem, setSelectedItem] = useState(null);
  const [loading, setLoading] = useState(false);
  const [newItem, setNewItem] = useState(null);
  const [message, setMessage] = useState("");

  const authHeaders = {
    Authorization: createBasicAuthHeader(),
    "Content-Type": "application/json",
  };

  useEffect(() => {
    fetchList();
    setSelectedId(null);
    setSelectedItem(null);
    setNewItem(null);
    setMessage("");
  }, [entityType]);

  async function fetchList() {
    setLoading(true);
    try {
      const res = await fetch(`${API_BASE}/${entityType}`, {
        headers: { Authorization: authHeaders.Authorization },
      });
      if (!res.ok) throw new Error("Failed to fetch list");
      const data = await res.json();
      setItems(data);
    } catch (e) {
      setMessage("Failed to fetch list");
    }
    setLoading(false);
  }

  async function fetchItem(id) {
    if (!id) return;
    setLoading(true);
    try {
      const res = await fetch(`${API_BASE}/${entityType}/${id}`, {
        headers: { Authorization: authHeaders.Authorization },
      });
      if (!res.ok) throw new Error("Failed to fetch item");
      const data = await res.json();
      setSelectedItem(data);
      setNewItem(null);
    } catch (e) {
      setMessage("Failed to fetch item");
    }
    setLoading(false);
  }

  function onSelectId(e) {
    const id = e.target.value;
    setSelectedId(id);
    if (id) fetchItem(id);
    else {
      setSelectedItem(null);
      setNewItem(null);
    }
    setMessage("");
  }

  function onChangeField(field, value) {
    if (selectedItem) setSelectedItem({ ...selectedItem, [field]: value });
    if (newItem) setNewItem({ ...newItem, [field]: value });
  }

  function createEmptyItem() {
    switch (entityType) {
      case "students":
        return {
          firstName: "",
          lastName: "",
          email: "",
          dateOfBirth: "",
          program: { id: 0, name: "" },
          status: null,
        };
      case "grades":
        return {
          continuousGrade: null,
          midtermGrade: null,
          finalGrade: null,
        };
      case "enrollments":
        return {
          course: { id: 0, code: "", name: "", credit: 0 },
          semester: { id: 0, year: 0, semesterNumber: null },
          lecturer: { id: 0, name: "", department: null },
          grade: null,
          status: null,
        };
      case "courses":
        return {
          code: "",
          name: "",
          credit: 0,
        };
      default:
        return {};
    }
  }

  async function createItem() {
    if (!newItem) return;
    setLoading(true);
    try {
      const res = await fetch(`${API_BASE}/${entityType}`, {
        method: "POST",
        headers: authHeaders,
        body: JSON.stringify(newItem),
      });
      if (!res.ok) throw new Error("Create failed");
      setMessage("Created successfully");
      setNewItem(null);
      fetchList();
    } catch (e) {
      setMessage(e.message);
    }
    setLoading(false);
  }

  async function updateItem() {
    if (!selectedItem) return;
    setLoading(true);
    try {
      const res = await fetch(`${API_BASE}/${entityType}/${selectedItem.id}`, {
        method: "PUT",
        headers: authHeaders,
        body: JSON.stringify(selectedItem),
      });
      if (!res.ok) throw new Error("Update failed");
      setMessage("Updated successfully");
      fetchList();
    } catch (e) {
      setMessage(e.message);
    }
    setLoading(false);
  }

  async function deleteItem() {
    if (!selectedItem) return;
    if (!window.confirm("Are you sure to delete?")) return;
    setLoading(true);
    try {
      const res = await fetch(`${API_BASE}/${entityType}/${selectedItem.id}`, {
        method: "DELETE",
        headers: { Authorization: authHeaders.Authorization },
      });
      if (!res.ok) throw new Error("Delete failed");
      setMessage("Deleted successfully");
      setSelectedId(null);
      setSelectedItem(null);
      fetchList();
    } catch (e) {
      setMessage(e.message);
    }
    setLoading(false);
  }

  function renderFields(item, isNew = false) {
    if (!item) return null;

    const commonInput = (label, field, type = "text") => (
      <div style={{ marginBottom: 8 }}>
        <label>
          {label}:{" "}
          <input
            type={type}
            value={item[field] ?? ""}
            onChange={(e) => onChangeField(field, e.target.value)}
          />
        </label>
      </div>
    );

    switch (entityType) {
      case "students":
        return (
          <>
            {commonInput("First Name", "firstName")}
            {commonInput("Last Name", "lastName")}
            {commonInput("Email", "email", "email")}
            {commonInput("Date of Birth", "dateOfBirth", "date")}
            <div style={{ marginBottom: 8 }}>
              <label>
                Program ID:{" "}
                <input
                  type="number"
                  value={item.program?.id || ""}
                  onChange={(e) =>
                    onChangeField("program", { id: Number(e.target.value), name: "" })
                  }
                />
              </label>
            </div>
            <div style={{ marginBottom: 8 }}>
              <label>
                Status:{" "}
                <input
                  type="text"
                  value={item.status ?? ""}
                  onChange={(e) => onChangeField("status", e.target.value)}
                  placeholder="Enum value"
                />
              </label>
            </div>
          </>
        );
      case "grades":
        return (
          <>
            {commonInput("Continuous Grade", "continuousGrade", "number")}
            {commonInput("Midterm Grade", "midtermGrade", "number")}
            {commonInput("Final Grade", "finalGrade", "number")}
          </>
        );
      case "enrollments":
        return (
          <>
            <div style={{ marginBottom: 8 }}>
              <label>
                Course ID:{" "}
                <input
                  type="number"
                  value={item.course?.id || ""}
                  onChange={(e) =>
                    onChangeField("course", { ...item.course, id: Number(e.target.value) })
                  }
                />
              </label>
            </div>
            <div style={{ marginBottom: 8 }}>
              <label>
                Semester ID:{" "}
                <input
                  type="number"
                  value={item.semester?.id || ""}
                  onChange={(e) =>
                    onChangeField("semester", { ...item.semester, id: Number(e.target.value) })
                  }
                />
              </label>
            </div>
            <div style={{ marginBottom: 8 }}>
              <label>
                Lecturer ID:{" "}
                <input
                  type="number"
                  value={item.lecturer?.id || ""}
                  onChange={(e) =>
                    onChangeField("lecturer", { ...item.lecturer, id: Number(e.target.value) })
                  }
                />
              </label>
            </div>
            <div style={{ marginBottom: 8 }}>
              <label>
                Status:{" "}
                <input
                  type="text"
                  value={item.status ?? ""}
                  onChange={(e) => onChangeField("status", e.target.value)}
                  placeholder="Enum value"
                />
              </label>
            </div>
          </>
        );
      case "courses":
        return (
          <>
            {commonInput("Code", "code")}
            {commonInput("Name", "name")}
            {commonInput("Credit", "credit", "number")}
          </>
        );
      default:
        return null;
    }
  }

  return (
    <div style={{ padding: 20, fontFamily: "Arial, sans-serif" }}>
      <h1>Admin Management</h1>

      <div>
        <label>
          Select Entity:{" "}
          <select
            value={entityType}
            onChange={(e) => {
              setEntityType(e.target.value);
              setMessage("");
            }}
          >
            {entities.map((en) => (
              <option key={en} value={en}>
                {en.charAt(0).toUpperCase() + en.slice(1)}
              </option>
            ))}
          </select>
        </label>
      </div>

      <h2>{entityType.charAt(0).toUpperCase() + entityType.slice(1)} List</h2>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <select
          size={10}
          style={{ width: 300 }}
          onChange={onSelectId}
          value={selectedId || ""}
        >
          <option value="">-- Select an ID --</option>
          {items.map((item) => (
            <option key={item.id} value={item.id}>
              {item.id}{" "}
              {entityType === "students" && item.firstName
                ? `${item.firstName} ${item.lastName}`
                : ""}
              {entityType === "courses" && item.name ? item.name : ""}
              {(entityType === "grades" || entityType === "enrollments") && item.id}
            </option>
          ))}
        </select>
      )}

      <div style={{ marginTop: 20 }}>
        <h2>
          {selectedId ? "Edit" : "Create New"} {entityType.slice(0, -1)}
        </h2>

        {selectedId && selectedItem && (
          <>
            {renderFields(selectedItem)}
            <button
              onClick={updateItem}
              disabled={loading}
              style={{ marginRight: 10 }}
            >
              Update
            </button>
            <button
              onClick={deleteItem}
              disabled={loading}
              style={{ marginRight: 10 }}
            >
              Delete
            </button>
          </>
        )}

        {!selectedId && (
          <>
            {newItem === null && (
              <button
                onClick={() => setNewItem(createEmptyItem())}
                disabled={loading}
                style={{ marginBottom: 10 }}
              >
                New {entityType.slice(0, -1)}
              </button>
            )}
            {newItem && (
              <>
                {renderFields(newItem, true)}
                <button
                  onClick={createItem}
                  disabled={loading}
                  style={{ marginRight: 10 }}
                >
                  Create
                </button>
                <button onClick={() => setNewItem(null)}>Cancel</button>
              </>
            )}
          </>
        )}
      </div>

      {message && <p style={{ color: "red", marginTop: 20 }}>{message}</p>}
    </div>
  );
}

export default AdminApp;
