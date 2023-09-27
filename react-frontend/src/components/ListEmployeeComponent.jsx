import React, { Component } from 'react';
import EmployeeService from '../services/EmployeeService';
import { Link } from "react-router-dom";

class ListEmployeeComponent extends Component {
    constructor(props){
        super(props)

        this.state = {
            employees:[]
        }
        this.deleteEmployee=this.deleteEmployee.bind(this)
    }

    componentDidMount(){
        EmployeeService.getEmployee().then((res) => {
            this.setState({employees: res.data})
        })
    }

    deleteEmployee(id){
        EmployeeService.deleteEmployee(id).then(res =>{
            this.setState({employees: this.state.employees.filter(employee => employee.id !== id)})
        })
    }
    
    render() {
        return (
            <div>
                <h2 className='text-center'>Employee List</h2>
                <div>
                    <Link to="/add-employee" className="btn btn-primary">
                        Add Employee
                    </Link>
                </div>
                <div className='row'>
                    <table className='table table-striped table-bordered' style={{marginTop:"10px"}}>
                        <thead>
                            <tr>
                                <th>Employee First Name</th>
                                <th>Employee Last Name</th>
                                <th>Employee Email Id</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.employees.map(
                                    employee =>
                                    <tr key={employee.id}>
                                        <td>{employee.firstName}</td>
                                        <td>{employee.lastName}</td>
                                        <td>{employee.emailId}</td>
                                        <td>
                                            <Link to={`/update-employee/${employee.id}`} className='btn btn-info'>
                                                Update
                                            </Link>
                                            <Link onClick={() =>this.deleteEmployee(employee.id)} style={{marginLeft:"10px"}} className='btn btn-danger'>
                                                Delete
                                            </Link>
                                        </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default ListEmployeeComponent;