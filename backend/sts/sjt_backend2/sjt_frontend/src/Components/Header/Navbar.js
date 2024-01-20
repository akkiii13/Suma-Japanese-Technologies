import { Link } from "react-router-dom";
import '../../CSS/INDEX.CSS';
import CompanyName from '../../Images/SJT_00_01.png';
import { Navbar, NavbarBrand, NavbarToggler, Collapse, Nav, NavbarItem, UncontrolledDropdown, DropdownToggle, DropdownMenu, DropdownItem, NavbarText, NavLink } from 'reactstrap';

const Navbar1 = () => {
    return (
        <nav>
            <div className="container-fluid">
                <div className="row" style= {{ backgroundColor: "#bae7fa83" }}>
                    <div className="col-6">
                        <a className="navbar-brand">
                            <img src={CompanyName} className="m-2" height="70px" width="200px" alt="" />
                        </a>
                    </div>
                    <div className="col-6 pe-5">
                        <ul className="nav justify-content-end mt-3 fs-5">
                            <li className="nav-item">
                                <Link className="nav-link active text-dark" aria-current="page" to={"/"}>Home</Link>
                            </li>
                            <li className="nav-item dropdown">
                                <a className="nav-link dropdown-toggle text-dark" href="#" id="navbarDropdown" role="button"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                    Navigate
                                </a>
                                <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a className="dropdown-item" href="AboutUs.html">About Us</a></li>
                                    <li><a className="dropdown-item" href="Product.html">Products</a></li>
                                    <li><a className="dropdown-item" href="services.html">Services</a></li>
                                    <li><a className="dropdown-item" href="gallery.html">Gallery</a></li>
                                    <li><a className="dropdown-item" href="FAQ.html">FAQ</a></li>
                                    <li><a className="dropdown-item" href="PressRelease.html">Press Release</a></li>
                                    <li><a className="dropdown-item" href="Investors.html">Investors</a></li>
                                    <li><a className="dropdown-item" href="Legal.html">Legal</a></li>
                                    <li><a className="dropdown-item" href="careers.html">Careers</a></li>
                                    <li><a className="dropdown-item" href="contactus.html">Contact Us</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    );
}

export { Navbar1 };
