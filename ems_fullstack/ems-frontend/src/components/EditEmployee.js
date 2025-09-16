import React, { useEffect, useState } from 'react';
import EmployeeService from '../services/EmployeeService';
import { useNavigate, useParams } from 'react-router-dom';

export default function EditEmployee() {
  const { id } = useParams();
  const [emp, setEmp] = useState({ firstName:'', lastName:'', email:''});
  const navigate = useNavigate();

  useEffect(() => {
    EmployeeService.getEmployeeById(id).then(res => setEmp(res.data)).catch(err => console.error(err));
  }, [id]);

  function change(e){ setEmp({...emp, [e.target.name]: e.target.value}); }
  function update(e){
    e.preventDefault();
    EmployeeService.updateEmployee(id, emp).then(() => navigate('/')).catch(err => console.error(err));
  }

  return (
    <form onSubmit={update}>
      <h3>Edit Employee</h3>
      <input name="firstName" placeholder="First name" value={emp.firstName} onChange={change} required/><br/>
      <input name="lastName" placeholder="Last name" value={emp.lastName} onChange={change} required/><br/>
      <input name="email" placeholder="Email" value={emp.email} onChange={change} required/><br/>
      <button type="submit">Update</button>
    </form>
  );
}
