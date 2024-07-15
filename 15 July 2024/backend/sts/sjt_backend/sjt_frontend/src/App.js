import 'react-toastify/dist/ReactToastify.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './Components/Home';
import SignUpForm from './Components/Customer/SignUpForm';
import { Navbar1 } from './Components/Header/Navbar';
import { Footer } from './Components/Footer/Footer';
import { Navtab } from './Components/Header/Navtab';
import ContactUs from './Components/ContactUs';
import SignIn from './Components/Customer/SignIn';

function App() {
  return (
    <>
      <BrowserRouter>
        <Navtab />
        <Navbar1 />
        <Routes>
          <Route exact path='/' element={<Home />} />
          <Route exact path='/signupform' element={<SignUpForm />} />
          <Route exact path='/contactus' element={<ContactUs />} />
          <Route exact path='/signin' element={<SignIn />}/>
        </Routes>
        <Footer />
      </BrowserRouter>
    </>
  );
}

export default App;
