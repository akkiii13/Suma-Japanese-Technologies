import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import Home from "./pages/Home/Home";
import LogIn from "./pages/LogIn/LogIn";
import SignUp from "./pages/SignUp/SignUp";
import ContactUs from "./pages/ContactUs/ContactUs";
import Booking from "./pages/Booking/Booking";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./Tailwind.css";
import { ToastContainer } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

function App() {
  return (
    <div>
      <BrowserRouter>
        <ToastContainer
          position="bottom-right"
          autoClose={5000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
          theme="colored"
        />
        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route exact path="/log-in" element={<LogIn />} />
          <Route exact path="/sign-up" element={<SignUp />} />
          {/* <Route exact path="/about-us" element={<AboutUs />} />
          <Route exact path="/product" element={<Product />} />
          <Route exact path="/faq" element={<Faq />} /> */}
          <Route exact path="/contact-us" element={<ContactUs />} />
          <Route exact path="/booking" element={<Booking />} />
          {/* <Route exact path="/career" element={<Career />} />
          <Route exact path="/privacy-policy" element={<PrivacyPolicy />} />
          <Route exact path="/legal" element={<Legal />} />
          <Route exact path="/press-release" element={<PressRelease />} /> */}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
