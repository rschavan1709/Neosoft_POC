import React, { Component } from 'react';
import {useParams,Link} from "react-router-dom";
import EmployeeService from '../services/EmployeeService';

class ViewEmployeeComponent extends Component {
    constructor(props){
        super(props)

        this.state = {
            id: this.props.id,
            employee: {}
        }
    }

    componentDidMount(){
        EmployeeService.getEmployeeById(this.state.id).then(res =>{
            this.setState({employee: res.data})
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className='card col-md-6 offset-md-3'>
                    <h3 className='text-center'>View Employee Details</h3>
                    <div className='card-body'>
                        
                            <label> Employee First Name: </label>
                             {this.state.employee.firstName} 
                            <br></br>
                            <label> Employee Last Name: </label>
                             {this.state.employee.lastName} 
                            <br></br>
                            <label> Employee Email ID: </label>
                             {this.state.employee.emailId} 
                        
                    </div>
                </div>
                <div className='text-center'>
                    <Link to="/employees" className="btn btn-primary" style={{marginTop:"10px"}}>
                        Back
                    </Link>
                </div>
            </div>
        );
    }
}

function ViewEmployeeWrapper(){
    const{id}=useParams()
    return <ViewEmployeeComponent id={id}/>
}

export default ViewEmployeeWrapper;