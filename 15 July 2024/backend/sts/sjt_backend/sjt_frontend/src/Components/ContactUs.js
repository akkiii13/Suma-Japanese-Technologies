import React, { useState, useRef } from "react";
import axiosHelp from '../Axios/AxiosHelp';
import emailjs from '@emailjs/browser';
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";

const EmailjsContactForm = () => {
  const form = useRef();

  let navigate = useNavigate();

  const [contactUs, setContactUs] = useState({
    user_name: "",
    user_mobile_number: "",
    user_email: "",
    user_subject: "",
    message: ""
  });

  const { user_name, user_mobile_number, user_email, user_subject, message } = contactUs;

  const onInputChangeForCustomer = (e) => {
    setContactUs({ ...contactUs, [e.target.name]: e.target.value });
  }

  const sendEmail = (e) => {
    e.preventDefault();
    axiosHelp.post("/contactus/save", contactUs);

    emailjs.sendForm('service_0djtnkb', 'template_4cremhk', form.current, 'k6I_6A5Kza7FMPSbK')
      .then(() => {
        successNotify("Email sent successfully.")
      }, () => {
        errorNotify("Please enter valid email address.")
      });

    navigate("/contactus");
    console.log(contactUs);
  };

  const successNotify = (message) => toast.success(message, {
    position: "bottom-left",
    autoClose: 5000,
    hideProgressBar: false,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true,
    progress: undefined,
    theme: "colored",
  });

  const errorNotify = (message) => toast.error(message, {
    position: "bottom-left",
    autoClose: 5000,
    hideProgressBar: false,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true,
    progress: undefined,
    theme: "colored",
  });

  return (
    <div className="container-fluid py-5">
      <div className="container pt-12 pb-3">
        <h1 className="display-4 text-uppercase text-center mb-5">Contact Us</h1>
        <div className="row">
          <div className="col-lg-12 mb-2">
            <div className="contact-form bg-light mb-4" style={{ padding: "30px", height: "490px" }}>
              <form ref={form} onSubmit={(e) => sendEmail(e)}>
                <div className="row mb-3">
                  <div className="col-4 form-group">
                    <input className="form-control p-3" type="text" id="user_name" name="user_name" placeholder="Full Name" value={user_name} onChange={(e) => onInputChangeForCustomer(e)}
                      required />
                  </div>
                  <div className="col-4 form-group">
                    <input type="phone" className="form-control p-3" placeholder="Mobile No" onChange={(e) => onInputChangeForCustomer(e)} value={user_mobile_number}
                      id="user_mobile_number" name="user_mobile_number" pattern="[789][0-9]{9}" required="required" />
                  </div>
                  <div className="col-4 form-group">
                    <input className="form-control p-3" type="email" id="user_email" name="user_email" placeholder="Email" onChange={(e) => onInputChangeForCustomer(e)} value={user_email}
                      required />
                  </div>
                </div>
                <div className="form-group mb-3 ">
                  <input className="form-control p-3" placeholder="Subject" list="subjectNames" name="user_subject" onChange={(e) => onInputChangeForCustomer(e)} value={user_subject}
                    id="user_subject" autoComplete="off" />
                  <datalist id="subjectNames">
                    <option value="General Enquiry" name="General Enquiry">General Enquiry</option>
                    <option value="Booking Enquiry" name="Booking Enquiry">Booking Enquiry</option>
                    <option value="Service Enquiry" name="Service Enquiry">Service Enquiry</option>
                    <option value="Supplier Opportunity" name="Supplier Opportunity">Supplier Opportunity</option>
                    <option value="Investment Opportunity" name="Investment Opportunity">Investment Opportunity</option>
                  </datalist>
                </div>
                <div className="form-group mb-3">
                  <textarea className="form-control py-2 px-4" rows="5" id="message" name="message" placeholder="Message" onChange={(e) => onInputChangeForCustomer(e)} value={message}
                    required="required"></textarea>
                </div>
                <div>
                  <button className="btn btn-primary py-32px-4" type="submit">Send Message</button>
                  <ToastContainer position="bottom-left" autoClose={5000} hideProgressBar={false} newestOnTop={false} closeOnClick rtl={false} pauseOnFocusLoss draggable pauseOnHover theme="colored" />
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EmailjsContactForm;