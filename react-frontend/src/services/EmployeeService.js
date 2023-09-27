import axios from 'axios';

const EMPLOYEE_API_BASE_URL = "http://localhost:8080/api/v1";

class EmployeeService{
    getEmployee(){
        return axios.get(EMPLOYEE_API_BASE_URL+"/findAll")
    }

    createEmployee(employee){
        return axios.post(EMPLOYEE_API_BASE_URL+"/save",employee)
    }

    getEmployeeById(employeeId){
        return axios.get(EMPLOYEE_API_BASE_URL+"/getEmployee/"+employeeId)
    }

    updateEmployee(employee,employeeId){
        return axios.put(EMPLOYEE_API_BASE_URL+"/updateEmployee/"+employeeId,employee)
    }

    deleteEmployee(employeeId){
        return axios.delete(EMPLOYEE_API_BASE_URL+"/delete/"+employeeId)
    }

}

export default new EmployeeService()