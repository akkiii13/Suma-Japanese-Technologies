import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSolid, faLocationDot } from "@fortawesome/free-solid-svg-icons";
import "./Footer.css";

const Footer = () => {
  return (
    <footer>
      <div className="container-fluid pt-4 footer-background-svg">
        <div className="container-xxl" style={{ maxWidth: 1500 + "px" }}>
          <div className="row pt-5 py-5 gy-4">
            <div className="col-xs-12 col-sm-6 col-md-6 col-lg-6 col-xl-3 col-xxl-3 mb-2 text-white">
              <h4 className="text-uppercase text-light mb-2">Get In Touch</h4>
              <div
                className="mt-1 mb-5"
                style={{
                  display: "flex",
                  justifyContent: "start",
                  alignItems: "start",
                }}
              >
                <div
                  style={{ border: "1px solid #7AB629", width: 40 + "px" }}
                ></div>
              </div>
              <p style={{ color: "#ffffff80" }}>
                <a
                style={{ textDecoration: "none", color: '#ffffff80' }}
                  href="https://goo.gl/maps/og5mYGQP7yhWH6oE8"
                  rel="noopener noreferrer"
                  target="_blank"
                >
                  <FontAwesomeIcon
                    icon={{faLocationDot}}
                    style={{ color: "#7AB629", marginRight: 7 + "px" }}
                  />
                  First floor, Shinde Complex, Plot11, Shinde Nagar, Pashan-NDA
                  Road, Bavdhan, Pune-411021, Maharashtra, India
                </a>
              </p>
              <p className="mt-5">
                <a
                  href="tel:8799997206"
                  className="nav-phone-number"
                  rel="noopener"
                >
                  <i
                    className="fa fa-phone-alt mr-3"
                    style={{ color: "#7AB629" }}
                  ></i>
                </a>
                <span className="user-select-none">&nbsp;</span>
                <span style={{ color: "#ffffff80" }}>
                  +91 8799997206, 07, 08, 09
                </span>
              </p>
              <p className="mt-0 mt-xl-5">
                <a
                  href="mailto:sales@sjtev.com"
                  className="text-decoration-none footer-useful-links"
                  rel="noopener"
                >
                  <i
                    className="fa fa-envelope mr-3"
                    style={{ color: "#7AB629" }}
                  ></i>
                  <span className="user-select-none">&nbsp;</span>
                  <span>info@sjtev.com</span>
                </a>
              </p>
            </div>
            <div className="col-xs-12 col-sm-6 col-md-6 col-lg-6 col-xl-2 col-xxl-2 mb-2">
              <h4 className="text-uppercase text-light">Location</h4>
              <div
                className="mt-1 mb-5"
                style={{
                  display: "flex",
                  justifyContent: "start",
                  alignItems: "start",
                }}
              >
                <div
                  style={{ border: "1px solid #7AB629", width: 40 + "px" }}
                ></div>
              </div>
              <p>
                <iframe
                  title="Office-Location"
                  src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3783.3626046570903!2d73.77939017589746!3d18.512509169419104!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3bc2bf44dfa0abf5%3A0x182cf7da144247ae!2sSJT%20Electric%20Vehicles!5e0!3m2!1sen!2sin!4v1689163790389!5m2!1sen!2sin"
                  style={{
                    border: 0,
                    maxWidth: 150 + "px",
                    maxHeight: 250 + "px",
                  }}
                  allowFullScreen=""
                  referrerPolicy="no-referrer-when-downgrade"
                ></iframe>
              </p>
            </div>
            <div className="col-xs-12 col-sm-6 col-md-6 col-lg-6 col-xl-4 col-xxl-4 mb-4">
              <h4 className="text-uppercase text-light">Recent Posts</h4>
              <div
                className="mt-1 mb-5"
                style={{
                  display: "flex",
                  justifyContent: "start",
                  alignItems: "start",
                }}
              >
                <div
                  style={{ border: "1px solid #7AB629", width: 40 + "px" }}
                ></div>
              </div>
              <div className="d-flex">
                <div
                  style={{
                    width: 100 + "px",
                    height: 110 + "px",
                    marginLeft: -10 + "px",
                  }}
                  className="rounded"
                >
                  <a
                    href="https://www.linkedin.com/company/sjt-electric-vehicles"
                    rel="noopener noreferrer"
                    style={{ width: "inherit", height: "inherit" }}
                    target="_blank"
                  >
                    <img
                      src="./images/website/sjt_website_013.png"
                      alt=""
                      style={{
                        width: "inherit",
                        height: "inherit",
                        objectFit: "contain",
                      }}
                    />
                  </a>
                </div>
                <div className="ms-3 mt-2">
                  <div className="d-flex">
                    <div>
                      <span style={{ color: "#777575", fontSize: 13 + "px" }}>
                        7 Sept, 2023
                      </span>
                    </div>
                    <div className="ms-5">
                      <span>
                        <a
                          href="https://www.linkedin.com/company/sjt-electric-vehicles"
                          rel="noopener noreferrer"
                          className="text-decoration-none"
                          target="_blank"
                          style={{ color: "#7AB629", fontSize: 13 + "px" }}
                        >
                          0 Comments
                        </a>
                      </span>
                    </div>
                  </div>
                  <div className="mt-2">
                    <a
                      href="https://www.linkedin.com/company/sjt-electric-vehicles"
                      rel="noopener noreferrer"
                      className="text-decoration-none"
                      target="_blank"
                    >
                      <span className="footer-blog-title">
                        SJT Electric Vehicles: Your Path to Sustainable
                        Retrofitted EVs
                      </span>
                    </a>
                  </div>
                </div>
              </div>
              <div className="d-flex">
                <div
                  style={{
                    width: 100 + "px",
                    height: 110 + "px",
                    marginLeft: -10 + "px",
                  }}
                  className="rounded"
                >
                  <a
                    href="https://www.linkedin.com/company/sjt-electric-vehicles"
                    rel="noopener noreferrer"
                    style={{ width: "inherit", height: "inherit" }}
                    target="_blank"
                  >
                    <img
                      src="./images/website/sjt_website_012.png"
                      alt=""
                      style={{
                        width: "inherit",
                        height: "inherit",
                        objectFit: "contain",
                      }}
                    />
                  </a>
                </div>
                <div className="ms-3 mt-2">
                  <div className="d-flex">
                    <div>
                      <span style={{ color: "#777575", fontSize: 13 + "px" }}>
                        24 Aug, 2023
                      </span>
                    </div>
                    <div className="ms-5">
                      <span>
                        <a
                          href="https://www.linkedin.com/company/sjt-electric-vehicles"
                          rel="noopener noreferrer"
                          className="text-decoration-none"
                          target="_blank"
                          style={{ color: "#7AB629", fontSize: 13 + "px" }}
                        >
                          0 Comments
                        </a>
                      </span>
                    </div>
                  </div>
                  <div className="mt-2">
                    <a
                      href="https://www.linkedin.com/company/sjt-electric-vehicles"
                      rel="noopener noreferrer"
                      className="text-decoration-none"
                      target="_blank"
                    >
                      <span className="footer-blog-title">
                        Elevating Sustainability: Impact of EV Retrofitting
                      </span>
                    </a>
                  </div>
                </div>
              </div>
            </div>
            <div className="col-xs-12 col-sm-6 col-md-6 col-lg-6 col-xl-3 col-xxl-3 text-white">
              <h4 className="text-uppercase text-light">Useful Links</h4>
              <div
                className="mt-1 mb-5"
                style={{
                  display: "flex",
                  justifyContent: "start",
                  alignItems: "start",
                }}
              >
                <div
                  style={{ border: "1px solid #7AB629", width: 40 + "px" }}
                ></div>
              </div>
              <div className="justify-content-start">
                <div className="row">
                  <div className="col-6 col-xs-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 xol-xxl-6">
                    <a className="mb-2 footer-useful-links" href="/">
                      <i className="fa fa-angle-right mr-2">Home</i>
                    </a>
                  </div>
                  <div className="col-6 col-xs-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 xol-xxl-6">
                    <a className="mb-2 footer-useful-links" href="faq.html">
                      <i className="fa fa-angle-right mr-2">FAQ</i>
                    </a>
                  </div>
                </div>
                <div className="row mt-sm-3">
                  <div className="col-6">
                    <a
                      className="mb-2 footer-useful-links"
                      href="products.html"
                    >
                      <i className="fa fa-angle-right mr-2">Products</i>
                    </a>
                  </div>
                  <div className="col-6">
                    <a className="mb-2 footer-useful-links" href="booking.html">
                      <i className="fa fa-angle-right mr-2">Get a Quote</i>
                    </a>
                  </div>
                </div>
                <div className="row mt-sm-3">
                  <div className="col-6">
                    <a className="mb-2 footer-useful-links" href="/#contactus">
                      <i className="fa fa-angle-right mr-2">Investors</i>
                    </a>
                  </div>
                  <div className="col-6">
                    <a className="mb-2 footer-useful-links" href="careers.html">
                      <i className="fa fa-angle-right mr-2">Careers</i>
                    </a>
                  </div>
                </div>
                <div className="row mt-sm-3">
                  <div className="col-6">
                    <a
                      className="mb-2 footer-useful-links"
                      href="privacy_policy.html"
                    >
                      <i className="fa fa-angle-right mr-2">Privacy Policy</i>
                    </a>
                  </div>
                  <div className="col-6">
                    <a className="mb-2 footer-useful-links" href="legal.html">
                      <i className="fa fa-angle-right mr-2">Legal</i>
                    </a>
                  </div>
                </div>
                <div className="row mt-sm-3">
                  <div className="col-6">
                    <a
                      className="mb-2 footer-useful-links"
                      href="press_release.html"
                    >
                      <i className="fa fa-angle-right mr-2">Press Release</i>
                    </a>
                  </div>
                  <div className="col-6">
                    <a className="mb-2 footer-useful-links" href="/#contactus">
                      <i className="fa fa-angle-right mr-2">Contact us</i>
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div
          className="row pt-3 pb-1 text-center"
          style={{ backgroundColor: "#000000", color: "#ffffff80" }}
        >
          <p>
            <span>Copyright ©2023</span>
            <span style={{ color: "#FFFFFF" }}>
              {" "}
              Suma Japanese Technologies
            </span>
            <span>. All rights reserved. T&C applied</span>
          </p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
