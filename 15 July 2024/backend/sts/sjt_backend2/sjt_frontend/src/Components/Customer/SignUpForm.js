import { useState } from "react";
import axiosHelp from '../../Axios/AxiosHelp';
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from 'react-toastify';

const SignUpForm = () => {

    let navigate = useNavigate();

    const [customer, setCustomer] = useState({
        firstName: "",
        lastName: "",
        mobileNumber: "",
        email: "",
        password: "",
        cpassword: ""
    });

    const { firstName, lastName, mobileNumber, email, password, cpassword } = customer;

    const onInputChangeForCustomer = (e) => {
        setCustomer({ ...customer, [e.target.name]: e.target.value });
    }

    const checkPasswordAndCPassword = () => {
        return (customer.password === customer.cpassword) ? true : false;
    }

    const notify = (message) => toast.error(message, {
        position: "bottom-left",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "colored",
    });

    const resetData = () => {
        setCustomer({
            firstName: "",
            lastName: "",
            mobileNumber: "",
            email: "",
            password: "",
            cpassword: ""
        })
    }

    const onSubmit = async (e) => {
        e.preventDefault();


        if (customer.firstName.trim().length === 0 || customer.firstName.trim().length === "undefined") {
            notify("Please enter FIRST NAME. FIRST NAME cannot be blank or empty.");
            return;
        }
        if (!(customer.firstName.trim().match((`^[a-zA-Z]+$`)))) {
            notify("Please enter only characters in FIRST NAME field.");
            return;
        }


        if (customer.lastName.trim().length === 0 || customer.lastName.trim().length === "undefined") {
            notify("Please enter LAST NAME. LAST NAME cannot be blank or empty.");
            return;
        }
        if (!customer.lastName.trim().match((`^[a-zA-Z]+$`))) {
            notify("Please enter only characters in LAST NAME field.");
            return;
        }


        if (customer.mobileNumber.trim().length === 0 || customer.mobileNumber.trim().length === "undefined") {
            notify("Please enter MOBILE NUMBER. MOBILE NUMBER cannot be blank or empty.");
            return;
        }
        if (!(customer.mobileNumber.match((`^[6789][0-9]{9}$`)))) {
            notify("Please enter valid MOBILE NUMBER without country code.");
            return;
        }


        // const emailPattern= ("?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\]");
        if (customer.email.trim().length === 0 || customer.email.trim().length === "undefined") {
            notify("Please enter EMAIL. EMAIL cannot be blank or empty.");
            return;
        }
        // if(!(customer.email.trim()
        // .match(emailPattern)
        // .match(`^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$`)
        // .match("([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\"\(\[\]!#-[^-~ \t]|(\\[\t -~]))+\")@([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\[[\t -Z^-~]*])")
        // )) {
        //     notify("Please enter valid EMAIL field.");
        //     return;
        // }


        if (customer.password.trim().length === 0 || customer.password.trim().length === "undefined") {
            notify("Please enter PASSWORD. PASSWORD cannot be blank or empty.");
            return;
        }
        if (!(customer.password.trim().match("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})"))) {
            notify("PASSWORD must contain at least 1 lowercase alphabete, 1 uppercase alphabete, 1 numberic character, 1 special character and should be 8 characters long.");
            return;
        }
        if (!checkPasswordAndCPassword()) {
            notify("Password and Confirm Password does not match");
            return;
        }

        let oldOrnewCustomer = await axiosHelp.get("/customer/oldOrNewCustomer", customer.mobileNumber, customer.email);
        if (!(oldOrnewCustomer)) {
            notify("This email or mobile number is already present.");
            return;
        }
        await axiosHelp.post("/customer/save", customer);
        resetData();
        navigate("/signin");
    }

    return (
        <>
            <section className="wrapper">
                <div className="container  py-5 pb-3">
                    <div className="col-sm-8 offset-sm-2 col-lg-6 offset-lg-3 col-xl-4 offset-xl-4 text-center">
                        <form className="rounded bg-white shadow p-5" onSubmit={(e) => onSubmit(e)}>
                            <h3 className="text-dark fw-bolder fs-4 mb-2">Create an Account</h3>

                            <div className="fw-normal text-muted mb-2">
                                Already have an account? <Link className="text-primary fw-bold text-decoration-none" to="/signin" >Sign in here</Link>
                            </div>

                            <div className="form-floating mb-3">
                                <input type="text" className="form-control" id="firstName" placeholder="name@example.com" name="firstName" value={firstName} onChange={(e) => onInputChangeForCustomer(e)}></input>
                                <label htmlFor="firstName">First Name</label>
                            </div>

                            <div className="form-floating mb-3">
                                <input type="text" className="form-control" id="lastName" placeholder="name@example.com" name="lastName" value={lastName} onChange={(e) => onInputChangeForCustomer(e)}></input>
                                <label htmlFor="lastName">Last Name</label>
                            </div>

                            <div className="form-floating mb-3">
                                <input type="phone" className="form-control" id="mobileNumber" placeholder="name@example.com" name="mobileNumber" value={mobileNumber} onChange={(e) => onInputChangeForCustomer(e)}></input>
                                <label htmlFor="mobileNumber">Phone Number</label>
                            </div>

                            <div className="form-floating mb-3">
                                <input type="email" className="form-control" id="email" placeholder="name@example.com" name="email" value={email} onChange={(e) => onInputChangeForCustomer(e)}></input>
                                <label htmlFor="email">Email Address</label>
                            </div>

                            <div className="form-floating mb-3">
                                <input type="password" className="form-control" id="password" placeholder="Password" name="password" value={password} onChange={(e) => onInputChangeForCustomer(e)}></input>
                                <label htmlFor="password">Password</label>
                                <span className="password-info mt-2">
                                    Use 8 or more characters with a mix of letters, number & symbol.
                                </span>
                            </div>

                            <div className="form-floating mb-3">
                                <input type="password" className="form-control" id="cpassword" placeholder="confirm Password" name="cpassword" value={cpassword} onChange={(e) => onInputChangeForCustomer(e)}></input>
                                <i className="bi bi-eye-slash" id="togglePassword"></i>
                                <label htmlFor="cpassword"> Confirm Password</label>
                            </div>

                            <div className="form-check d-flex align-item-center">
                                <input className="form-check-input" type="checkbox" id="gridCheck"></input>
                                <label className="form-check-label ms-2" htmlFor="gridCheck">
                                    I agree <a href="#">Terms and Conditions</a>.
                                </label>
                            </div>

                            <button type="submit" className="btn btn-primary submit_btn w-100 my-4">Continue</button>
                            <ToastContainer position="bottom-left" autoClose={5000} hideProgressBar={false} newestOnTop={false} closeOnClick rtl={false} pauseOnFocusLoss draggable pauseOnHover theme="colored" />

                        </form>
                    </div>
                </div>
            </section>
        </>
    );
}

export default SignUpForm;