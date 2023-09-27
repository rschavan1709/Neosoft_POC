import React, { Component } from 'react';
import { Link,useParams,useNavigate } from "react-router-dom";
import EmployeeService from '../services/EmployeeService';

class UpdateEmployeeComponent extends Component {
    constructor(props){
        super(props)

        this.state = {
            id: this.props.id,
            firstName:'',
            lastName:'',
            emailId:''
        }

        this.changeFirstNameHandler=this.changeFirstNameHandler.bind(this)
        this.changeLastNameHandler=this.changeLastNameHandler.bind(this)
        this.changeEmailHandler=this.changeEmailHandler.bind(this)
        this.updateEmployee=this.updateEmployee.bind(this)
    }

    componentDidMount(){
        EmployeeService.getEmployeeById(this.state.id).then((res)=>{
            let employee=res.data
            this.setState({firstName: employee.firstName,
            lastName: employee.lastName,
            emailId:employee.emailId})
        })
    }

    changeFirstNameHandler=(event)=>{
        this.setState({firstName:event.target.value})
    }

    changeLastNameHandler=(event)=>{
        this.setState({lastName:event.target.value})
    }

    changeEmailHandler=(event)=>{
        this.setState({emailId:event.target.value})
    }

    updateEmployee=(e)=>{
        e.preventDefault();
        let updatedEmployee = {firstName:this.state.firstName, lastName:this.state.lastName, emailId: this.state.emailId}
        console.log('employee => ' +JSON.stringify(updatedEmployee))
        EmployeeService.updateEmployee(updatedEmployee,this.state.id).then((res)=>{
            this.props.navigate('/employees');
        })
        
    }

    render() {
        return (
            <div>
                <div className='container'>
                    <div className='row'>
                        <div className='card col-md-6 offset-md-3 offset-md-3'>
                            <h3 className='text-center'>Add Employee</h3>
                            <div className='card-body'>
                                <form>
                                    <div className='form-group'>
                                        <label> First Name: </label>
                                        <input placeholder='First Name' name='firstName' className='form-control'
                                            value={this.state.firstName} onChange={this.changeFirstNameHandler}/>
                                    </div>
                                    <div className='form-group'>
                                        <label> Last Name: </label>
                                        <input placeholder='Last Name' name='lastName' className='form-control'
                                            value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                                    </div>
                                    <div className='form-group'>
                                        <label> Email Id: </label>
                                        <input placeholder='Email Address' name='emailId' className='form-control'
                                            value={this.state.emailId} onChange={this.changeEmailHandler}/>
                                    </div>
                                    <Link className="btn btn-success" onClick={this.updateEmployee} style={{marginTop:"10px"}}>
                                        Update
                                    </Link>
                                    <Link to="/employees" className="btn btn-danger" style={{marginTop:"10px",marginLeft:"10px"}}>
                                        Cancel
                                    </Link>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

function UpdateEmployeeWrapper(){
    const{id}=useParams()
    const navigate=useNavigate()
    return <UpdateEmployeeComponent id={id} navigate={navigate}/>
}

export default UpdateEmployeeWrapper;