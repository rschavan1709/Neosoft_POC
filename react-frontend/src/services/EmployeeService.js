import axios from 'axios';

const EMPLOYEE_API_BASE_URL = "http://localhost:8080/api/v1";

class EmployeeService{
    getEmployee(){
        return axios.get(EMPLOYEE_API_BASE_URL+"/findAll")
    }

    createEmployee(employee){
        return axios.post(EMPLOYEE_API_BASE_URL+"/save",employee)
    }
}

export default new EmployeeService()