import React, { Component } from 'react';
import swal from 'sweetalert';
import axios from 'axios';

class RefundComponent extends Component {

    constructor(props){
        super(props)

        this.state = {
            paymentId:""
        }

        this.changePaymentIdHandler=this.changePaymentIdHandler.bind(this)
        this.refundPayment=this.refundPayment.bind(this)
    }

    changePaymentIdHandler=(event)=>{
        this.setState({paymentId: event.target.value})
    }

    refundPayment=()=>{
        if(this.state.paymentId == "" || this.state.paymentId == null){
            swal("Failed !!", "Payment Id is required !!", "error");
            return;
        }
        let refund={paymentId: this.state.paymentId}
        console.log(refund);
        axios.post('http://localhost:8080/payment/refund',refund
                            ).then(response =>{
                                swal("Good job!", response.data, "success");
                            }).catch(error => {
                                swal("Failed !!", "Refund Failed !!", "error");
                            })
    }

    render() {
        return (
            <div>
                <div className='container'>
                    <div className='row'>
                        <div className='card col-md-6 offset-md-3'>
                            <h3 className='text-center'>Refund Form</h3>
                            <div className='card-body'>
                                <form>
                                    <input type='text' className='form-control my-2' name='paymentId' placeholder='Enter Payment Id here'
                                        onChange={this.changePaymentIdHandler}/>

                                    <div className='container text-center mt-3'>
                                        <button type="button" onClick={this.refundPayment} className='btn btn-success'>REFUND</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default RefundComponent;