import React, { Component } from 'react';
import swal from 'sweetalert';
import axios from 'axios';

class PaymentComponent extends Component {

    constructor(props){
        super(props)

        this.state = {
            amount:0
        }

        this.changeAmountHandler=this.changeAmountHandler.bind(this)
        this.handlePayment=this.handlePayment.bind(this)
    }

    changeAmountHandler=(event)=>{
        this.setState({amount: event.target.value})
    }

    handlePayment = async() => {
        // Make an API request to your Spring Boot backend to create a Razorpay order
        if(this.state.amount == "" || this.state.amount == null){
            swal("Failed !!", "Amount is required !!", "error");
            return;
        }
        let payment={amount: this.state.amount}
        console.log(payment);
        axios.post('http://localhost:8080/payment/create-order',payment
                            ).then(response =>{
                                console.log("resp :",response);
                                const order =  response.data;
                        
                                console.log("order :",order);
                                const options = {
                                  key: 'rzp_test_4VYzrAhktF88j5',
                                  amount: order.amount,
                                  currency: order.currency,
                                  order_id: order.orderId,
                                  name: 'Riddhi',
                                  description: 'Payment for Service',
                                  image:"https://images.unsplash.com/photo-1562690868-60bbe7293e94?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cm9zZSUyMGZsb3dlcnxlbnwwfHwwfHx8MA%3D%3D&w=1000&q=80",
                                  handler: function (response) {
                                    // Handle successful payment here
                                    console.log(response.razorpay_payment_id);
                                    console.log(response.razorpay_order_id);
                                    console.log(response.razorpay_signature);
                                    let updatePaidStatus={orderId: response.razorpay_order_id
                                        ,paymentId: response.razorpay_payment_id
                                        ,status: "PAID" };
                                    axios.post('http://localhost:8080/payment/update-status',updatePaidStatus)
                                    swal("Good job!", "Congrats !! Payment successful", "success");
                                  },
                                  prefill: {
                                    name: '',
                                    email: '',
                                    contact: '',
                                  },
                                  theme: {
                                    color: '#F37254',
                                  },
                                };
                                const rzp = new window.Razorpay(options);
                                rzp.on('payment.failed', function (response) {
                                // Handle payment failure here
                                console.error(response.error.metadata);
                                let updateFailStatus={orderId: response.error.metadata.order_id,
                                    paymentId: response.error.metadata.payment_id,
                                    status: "FAILED"};
                                axios.post('http://localhost:8080/payment/update-status',updateFailStatus);
                            });
                        
                            rzp.open();
                            });

       
    };

    render() {
        return (
            <div>
                <div className='container'>
                    <div className='row'>
                        <div className='card col-md-6 offset-md-3'>
                            <h3 className='text-center'>Payment Form</h3>
                            <div className='card-body'>
                                <form>
                                    <input type='text' className='form-control my-2' name='amount' placeholder='Enter amount here'
                                        onChange={this.changeAmountHandler}/>

                                    <div className='container text-center mt-3'>
                                        <button type="button" onClick={this.handlePayment} className='btn btn-success'>PAY</button>
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

export default PaymentComponent;