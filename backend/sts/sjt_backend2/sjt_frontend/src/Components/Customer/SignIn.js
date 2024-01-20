import { useState } from "react";
import axiosHelp from '../../Axios/AxiosHelp';
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from 'react-toastify';

const SignIn = () => {

    const onSubmit = () => {
        
    }

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
        <section class="wrapper py-5 pb-5">
            <div class="container py-5 pb-3">
                <div class="col-sm-8 offset-sm-2 col-lg-6 offset-lg-3 col-xl-4 offset-xl-4 text-center">
                    <form class="rounded bg-white shadow p-5" onSubmit={(e) => onSubmit(e)}>
                        <h3 class="text-dark fw-bolder fs-4 mb-2">SIGN IN</h3>
                        <div class="fw-normal text-muted mb-4">
                            New Here? <a href="Register.html" class="text-primary fw-bold text-decoration-none">Create an
                                Account</a>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="phone" class="form-control" id="floatingPhone" placeholder="Enter a Phone Number"
                                pattern="[789][0-9]{9}" />
                            <label for="floatingPhone">Phone Number</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" />
                            <label for="floatingInput">Email address</label>
                        </div>
                        <div class="form-floating">
                            <input type="password" class="form-control" id="floatingPassword" placeholder="Password" />
                            <label for="floatingPassword">Password</label>
                        </div>
                        <div class="mt-2 text-end">
                            <a href="forget_password.html" class="text-primary fw-bold text-decoration-none">Forget
                                Password?</a>
                        </div>
                        <button type="submit" formaction="profile.html" class="btn btn-primary submit_btn w-100 my-4 ">Continue</button>
                        <ToastContainer position="bottom-left" autoClose={5000} hideProgressBar={false} newestOnTop={false} closeOnClick rtl={false} pauseOnFocusLoss draggable pauseOnHover theme="colored" />
                    </form>
                </div>
            </div>
        </section>
    )
}

export default SignIn;