import React, { useEffect, useState } from 'react';
import EmployeeService from '../services/EmployeeService';
import { Link, useNavigate } from 'react-router-dom';

export default function EmployeeList() {
  const [employees, setEmployees] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    load();
  }, []);

  function load() {
    EmployeeService.getEmployees().then(res => setEmployees(res.data)).catch(err => console.error(err));
  }

  function remove(id) {
    if (!window.confirm('Delete this employee?')) return;
    EmployeeService.deleteEmployee(id).then(() => load());
  }

  return (
    <div>
      <button onClick={() => navigate('/add')}>Add Employee</button>
      <table border="1" cellPadding="8" style={{marginTop:10, width:'100%'}}>
        <thead>
          <tr><th>ID</th><th>First</th><th>Last</th><th>Email</th><th>Actions</th></tr>
        </thead>
        <tbody>
          {employees.map(emp => (
            <tr key={emp.id}>
              <td>{emp.id}</td>
              <td>{emp.firstName}</td>
              <td>{emp.lastName}</td>
              <td>{emp.email}</td>
              <td>
                <button onClick={() => navigate(`/edit/${emp.id}`)}>Edit</button>
                <button onClick={() => remove(emp.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
