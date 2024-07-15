import "./Header.css";

const navbarToggler = document.querySelector(".navbar-toggler");
const navbarTogglerImage = document.getElementById("navbar-toggler-image");
let isImage1Visible = true;

// navbarToggler.addEventListener("click", () => {
//   if (isImage1Visible) {
//     navbarTogglerImage.src = "../images/website/sjt_website_010.png";
//     navbarTogglerImage.width = "20";
//     navbarTogglerImage.height = "20";
//   } else {
//     navbarTogglerImage.src = "../images/website/sjt_website_009.png";
//     navbarTogglerImage.width = "25";
//     navbarTogglerImage.height = "25";
//   }

//   isImage1Visible = !isImage1Visible;
// });

// window.addEventListener("DOMContentLoaded", function () {
//   if (window.innerWidth >= 1200) {
//     const dropdownItems = document.querySelectorAll(".nav-item.dropdown");

//     dropdownItems.forEach((item) => {
//       item.addEventListener("mouseenter", () => {
//         item.querySelector(".dropdown-menu").classList.add("show");
//       });

//       item.addEventListener("mouseleave", () => {
//         item.querySelector(".dropdown-menu").classList.remove("show");
//       });

//       item.addEventListener("click", (event) => {
//         event.preventDefault();
//       });
//     });
//   }
// });

const Header = () => {
  return (
    <div>
      <div>
        <div
          className="container-fluid d-none d-xl-block d-xxl-block"
          style={{ height: 50 + "px", backgroundColor: "#000000" }}
        >
          <div className="position-relative">
            <div className="row">
              <div className="col">
                <div className="text-xl-end text-xxl-end me-3">
                  <div>
                    <a
                      href="https://goo.gl/maps/og5mYGQP7yhWH6oE8"
                      target="_blank"
                      rel="noopener noreferrer"
                    >
                      <i
                        className="fa-solid fa-location-dot fa-sm"
                        style={{ color: "#7AB629", marginRight: 7 + "px" }}
                      ></i>
                    </a>
                    <span className="header-top-details-font">
                      Bavdhan, Pune-411021, India
                    </span>
                    <img
                      src="./images/website/sjt_website_002.png"
                      alt=""
                      height="20"
                      width="5"
                      className="user-select-none"
                      style={{ opacity: 0.6, marginLeft: 30 + "px" }}
                    />
                    <div
                      style={{ borderLeft: "4px solid #7AB629" }}
                      className="user-select-none"
                    ></div>
                  </div>
                </div>
              </div>
              <div className="col">
                <div className="text-xl-start text-xxl-start">
                  <div>
                    <i
                      className="fa-regular fa-clock fa-sm"
                      style={{ color: "#7AB629", marginRight: 7 + "px" }}
                    ></i>
                    <span className="header-top-details-font">
                      Mon-Sat: 9am to 6pm
                    </span>
                  </div>
                </div>
              </div>
              <div className="col">
                <div className="text-xl-end text-xxl-end">
                  <div>
                    <i
                      className="fa-regular fa-envelope fa-sm"
                      style={{ color: "#7AB629", marginRight: 7 + "px" }}
                    ></i>
                    <a
                      href="mailto:sales@sjtev.com"
                      rel="noopener noreferrer"
                      className="text-decoration-none header-top-details-font navbar-email"
                    >
                      sales@sjtev.com
                    </a>
                  </div>
                </div>
              </div>
              <div
                className="col"
                style={{ backgroundColor: "#7AB629", height: 50 + "px" }}
              >
                <div className="text-xl-start text-xxl-start">
                  <div
                    style={{ marginTop: 10 + "px", marginLeft: 20 + "px" }}
                    className="user-select-none"
                  >
                    <span
                      className="text-white"
                      style={{ fontSize: 15 + "px", fontWeight: 400 }}
                    >
                      Follow On:
                    </span>
                    &nbsp;
                    <a
                      href="https://www.youtube.com/@SJTElectricVehicles"
                      target="_blank"
                      rel="noopener noreferrer"
                      className="text-decoration-none"
                    >
                      <img
                        src="./images/website/sjt_website_003.png"
                        alt=""
                        height="30"
                        width="30"
                      />
                    </a>
                    <a
                      href="https://www.linkedin.com/company/sjt-electric-vehicles"
                      target="_blank"
                      rel="noopener noreferrer"
                      className="text-decoration-none"
                    >
                      <img
                        src="./images/website/sjt_website_004.png"
                        alt=""
                        height="30"
                        width="30"
                      />
                    </a>
                    <a
                      href="https://www.facebook.com/SjtElectricVehicles"
                      target="_blank"
                      rel="noopener noreferrer"
                      className="text-decoration-none"
                    >
                      <img
                        src="./images/website/sjt_website_005.png"
                        alt=""
                        height="30"
                        width="30"
                      />
                    </a>
                    <a
                      href="https://www.instagram.com/sjtelectricvehicles/"
                      target="_blank"
                      rel="noopener noreferrer"
                      className="text-decoration-none"
                    >
                      <img
                        src="./images/website/sjt_website_006.png"
                        alt=""
                        height="30"
                        width="30"
                      />
                    </a>
                    <a
                      href="https://twitter.com/SJT_EV"
                      target="_blank"
                      rel="noopener noreferrer"
                      className="text-decoration-none"
                    >
                      <img
                        src="./images/website/sjt_website_007.png"
                        alt=""
                        height="30"
                        width="30"
                      />
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <header className="shadow">
        <nav
          className="navbar navbar-expand-xl bg-body-tertiary"
          style={{ height: 100 + "px" }}
        >
          <div className="container" style={{ maxWidth: 1550 + "px" }}>
            <a
              className="navbar-brand align-items-center justify-content-center"
              href="/"
            >
              <img
                src="./images/website/sjt_website_008.png"
                alt="SJT Electric Vehicles"
                width="180"
                className="d-inline-block align-text-top"
              />
            </a>
            <button
              className="navbar-toggler border-0"
              type="button"
              data-bs-toggle="collapse"
              style={{ color: "#000000" }}
              data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span>
                <img
                  id="navbar-toggler-image"
                  src="./images/website/sjt_website_009.png"
                  alt=""
                  width="30"
                  height="30"
                />
              </span>
            </button>
            <div
              className="collapse navbar-collapse"
              id="navbarSupportedContent"
            >
              <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
                <li className="nav-item me-4">
                  <a className="nav-link" href="/">
                    Home
                  </a>
                </li>
                <li className="nav-item me-4">
                  <a className="nav-link" href="/#aboutus">
                    About Us
                  </a>
                </li>
                <li className="nav-item me-4">
                  <a className="nav-link" href="products.html">
                    Products
                  </a>
                </li>
                <li className="nav-item me-4">
                  <a className="nav-link" href="faq.html">
                    FAQ
                  </a>
                </li>
                <li className="nav-item me-4">
                  <a className="nav-link" href="/#contactus">
                    Contact Us
                  </a>
                </li>
              </ul>
              <div className="d-flex">
                <div
                  className="me-0 me-sm-0 me-md-0 me-lg-0 me-xl-4 me-xxl-4 d-none d-xl-block d-xxl-block"
                  style={{ marginTop: 10 + "px" }}
                >
                  <a
                    href="booking.html"
                    role="button"
                    className="text-decoration-none text-white btn border border-0 rounded-pill"
                    id="header-get-a-quote-button"
                  >
                    <span>Get a Quote</span>
                  </a>
                </div>
                <div className="d-none d-xl-block d-xxl-block user-select-none">
                  <img
                    src="./images/website/sjt_website_011.png"
                    alt=""
                    height="50"
                    width="50"
                  />
                </div>
                <span className="user-select-none">&nbsp;&nbsp;</span>
                <div className="d-none d-xl-block d-xxl-block">
                  <span>Free Appointment</span>
                  <br />
                  <a
                    href="tel:8799997206"
                    className="nav-phone-number"
                    rel="noopener noreferrer"
                  >
                    +91 8799997206
                  </a>
                </div>
              </div>
            </div>
          </div>
        </nav>
      </header>
    </div>
  );
};

export default Header;
