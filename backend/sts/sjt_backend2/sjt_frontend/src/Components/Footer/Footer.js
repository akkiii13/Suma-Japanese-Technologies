import { Link } from "react-router-dom";

const Footer = () => {
    return (
        <>
            <div className="container-fluid mt-4">
                <div className="row pt-5 bg-dark py-5 px-sm-3 px-md-5 ">
                    <div className="col-lg-3 col-md-6 mb-2 text-white">
                        <h4 className="text-uppercase text-light mb-4">Get In Touch</h4>
                        <p className="mb-2 text-white"><i className="fa fa-map-marker-alt mr-3"></i> First floor, Shinde
                            Complex, Plot11, Shinde Nagar, Pashan-NDA Road, Bavdhan, Pune-411021, Maharashtra, India</p>
                        <p className="mb-2"><i className="fa fa-phone-alt text-white mr-3"></i> +91 8799997205, 06, 07, 08, 09 </p>

                        <p><i className="fa fa-envelope text-white mr-3"></i> sales@SumaJapanese.com</p>
                        <h6 className="text-uppercase text-white py-2">Follow Us</h6>
                        <div className="d-flex justify-content-start">
                            <a className="btn btn-lg btn-dark btn-lg-square mr-2"
                                href="#">
                                <i className="fa-brands fa-youtube"></i></a>
                            <a className="btn btn-lg btn-dark btn-lg-square mr-2" href="#"><i className="fab fa-facebook-f"></i></a>
                            <a className="btn btn-lg btn-dark btn-lg-square mr-2"
                            href="#">
                                <i className="fab fa-linkedin-in"></i></a>
                            <a className="btn btn-lg btn-dark btn-lg-square"
                                href="#"><i className="fab fa-instagram"></i></a>
                        </div>
                    </div>

                    <div className="col-lg-3 col-md-6 mb-2">
                        <h4 className="text-uppercase text-light mb-4">Location</h4>
                        <p><iframe
                                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3783.362604551625!2d73.77977105094651!3d18.512509174189532!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3bc2be5ab98789cd%3A0xf7292bc3c7c36e4c!2sShinde%20Complex%2C%20Dr.%20Homi%20Bhabha%20Rd%2C%20Shindenagar%2C%20Bavdhan%2C%20Pune%2C%20Maharashtra%20411021!5e0!3m2!1sen!2sin!4v1678264424009!5m2!1sen!2sin"
                                width="250" height="250" style={{border: "0"}} allowFullScreen="" loading="lazy"
                                referrerPolicy="no-referrer-when-downgrade" /></p>
                        {/* <GoogleMapLocation /> */}
                        
                    </div>

                    <div className="col-lg-3 col-md-6 mb-2">
                        <h4 className="text-uppercase text-light mb-4">Useful Links</h4>
                        <div className="d-flex flex-column justify-content-start text-white">
                            <a className="text-white mb-2" href="privacypolicy.html"><i className="fa fa-angle-right mr-2"></i> Privacy
                                Policy</a>
                            <a className="text-white mb-2" href="HOME.html"><i className="fa fa-angle-right mr-2"></i> Home</a>
                            <a className="text-white mb-2" href="Product.html"><i className="fa fa-angle-right mr-2"></i> Product Us</a>
                            <a className="text-white" href="FAQ.html"><i className="fa fa-angle-right mr-2"></i> FAQ</a>
                            <Link className="text-white mb-2" to={"/contactus   "}><i className="fa fa-angle-right mr-2"></i> Contact
                                Us</Link>
                            
                        </div>
                    </div>

                    <div className="col-lg-3 col-md-6 mb-2 text-white">
                        <h4 className="text-uppercase text-light mb-4">News Release</h4>
                        <p className="mb-4">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Illo laudantium voluptates
                            dolores voluptatem quae quasi sapiente repellat ex. Fuga voluptatibus laudantium sequi cum maxime
                            praesentium laboriosam reprehenderit voluptatem aliquam repellendus!</p>
                        <div className="w-100 mb-3">
                            <div className="input-group">
                                <input type="text" className="form-control bg-light border-dark" style={{padding: "5px"}}
                                    placeholder="Your Email" />
                                <div className="input-group-append">
                                    <button className="btn btn-primary text-uppercase px-3">Sign Up</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="row p-3 text-center" style={{backgroundColor: "#bae7fa83"}}>
                    <div className="col">
                        <h6> &copy; 2023 Suma Japanese Technologies. All Rights Reserved.</h6>
                    </div>
                </div>
            </div>
        </>
    );
}

export { Footer };