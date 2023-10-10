import logo from './logo.svg';
import { BrowserRouter as Router, Routes, Route} from "react-router-dom";
import PaymentComponent from './components/PaymentComponent'
import './App.css';

function App() {
    return (
        <div>
          <Router>
                <div className="container">
                  <Routes>
                    <Route path="/" element={<PaymentComponent/>} />
                  </Routes>
                </div>
          </Router>
        </div>
      );
  
}

export default App;
