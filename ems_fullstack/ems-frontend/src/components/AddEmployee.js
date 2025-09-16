import React, { useState } from 'react';
import EmployeeService from '../services/EmployeeService';
import { useNavigate } from 'react-router-dom';

export default function AddEmployee() {
  const [emp, setEmp] = useState({ firstName:'', lastName:'', email:''});
  const navigate = useNavigate();

  function change(e){ setEmp({...emp, [e.target.name]: e.target.value}); }
  function save(e){
    e.preventDefault();
    EmployeeService.addEmployee(emp).then(() => navigate('/')).catch(err => console.error(err));
  }

  return (
    <form onSubmit={save}>
      <h3>Add Employee</h3>
      <input name="firstName" placeholder="First name" value={emp.firstName} onChange={change} required/><br/>
      <input name="lastName" placeholder="Last name" value={emp.lastName} onChange={change} required/><br/>
      <input name="email" placeholder="Email" value={emp.email} onChange={change} required/><br/>
      <button type="submit">Save</button>
    </form>
  );
}
