import axios from 'axios';
const API_URL = 'http://localhost:8080/api/employees';

class EmployeeService {
  getEmployees() { return axios.get(API_URL); }
  getEmployeeById(id) { return axios.get(`${API_URL}/${id}`); }
  addEmployee(emp) { return axios.post(API_URL, emp); }
  updateEmployee(id, emp) { return axios.put(`${API_URL}/${id}`, emp); }
  deleteEmployee(id) { return axios.delete(`${API_URL}/${id}`); }
}

export default new EmployeeService();
