import React, { Component } from 'react';
import { Link, Navigate } from "react-router-dom";
import EmployeeService from '../services/EmployeeService';

class CreateEmployeeComponent extends Component {

    constructor(props){
        super(props)

        this.state = {
            firstName:'',
            lastName:'',
            emailId:'',
            errorData:{},
            resgistrationSuccessful:false
        }

        this.changeFirstNameHandler=this.changeFirstNameHandler.bind(this)
        this.changeLastNameHandler=this.changeLastNameHandler.bind(this)
        this.changeEmailHandler=this.changeEmailHandler.bind(this)
        this.saveEmployee=this.saveEmployee.bind(this)
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

    saveEmployee=(e)=>{
        e.preventDefault();
        let employee = {firstName:this.state.firstName, lastName:this.state.lastName, emailId: this.state.emailId}
        console.log('employee => ' +JSON.stringify(employee))
        EmployeeService.createEmployee(employee).then(res =>{
            this.setState({resgistrationSuccessful:true})
        }).catch(error =>{
            this.setState({errorData: error.response.data})
        })
    }

    render() {
        if(this.state.resgistrationSuccessful){
            return <Navigate to="/"/>
        }
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
                                    <div className='error-message'>
                                        {this.state.errorData.firstName}
                                    </div>
                                    <div className='form-group'>
                                        <label> Last Name: </label>
                                        <input placeholder='Last Name' name='lastName' className='form-control'
                                            value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                                    </div>
                                    <div className='error-message'>
                                        {this.state.errorData.lastName}
                                    </div>
                                    <div className='form-group'>
                                        <label> Email Id: </label>
                                        <input placeholder='Email Address' name='emailId' className='form-control'
                                            value={this.state.emailId} onChange={this.changeEmailHandler}/>
                                    </div>
                                    <div className='error-message'>
                                        {this.state.errorData.emailId}
                                    </div>
                                    <Link className="btn btn-success" onClick={this.saveEmployee} style={{marginTop:"10px"}}>
                                        Save
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

export default CreateEmployeeComponent;