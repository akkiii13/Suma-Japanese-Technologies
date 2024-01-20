import { Link } from "react-router-dom";
import '../../CSS/INDEX.CSS';

const Navtab = () => {
    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col">
                    <ul className="nav bg-dark">
                        <li className="nav-item ps-4">
                            <a className="nav-link text-light" aria-current="page" href="#">MY ACCOUNT</a>
                        </li>
                        <li className="nav-item text-light ps-3">
                            <i className="fa-solid fa-cart-shopping fa-xl mt-4 ps-2"></i>
                        </li>
                        <ul className="nav bg-dark position-absolute top-0 end-0">
                            <li className="nav-item pe-3">
                                <Link className="nav-link text-light" to={"/signin"}>SIGN IN</Link>
                            </li>
                            <li className="nav-item pe-4">
                                <Link className="nav-link text-light" to={"/signupform"}>REGISTER</Link>
                            </li>
                        </ul>
                    </ul>
                </div>
            </div>
        </div>
    );
}

export { Navtab };
