import '../CSS/INDEX.CSS';
import { GoogleMapLocation } from "./GoogleMapLocation";
import { HomePageCarouselFirst, HomePageCarouselSecond, HomePageCarouselThird, HomePageCarouselFourth, HomePageCarouselFifth, HomePageCarouselSixth, HomePageCarouselSeventh, HomePageCarouselEighth } from './CarouselItems';

const Home = () => {
    return (
        <>
            {/* carousel slide */}
            <section className="p-5 bg-light">
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-lg-7 mb-2 ">
                            <div id="carouselExampleCaptions" className="carousel slide" data-bs-ride="carousel">
                                <div className="carousel-indicators">
                                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0"
                                        className="active" aria-current="true" aria-label="Slide 1"></button>
                                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                                        aria-label="Slide 2"></button>
                                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                                        aria-label="Slide 3"></button>
                                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3"
                                        aria-label="Slide 4"></button>
                                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="4"
                                        aria-label="Slide 5"></button>
                                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="5"
                                        aria-label="Slide 6"></button>
                                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="6"
                                        aria-label="Slide 7"></button>
                                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="7"
                                        aria-label="Slide 8"></button>
                                </div>

                                <div className="carousel-inner">
                                    <div className="carousel-item active">
                                        <img src={HomePageCarouselFirst} className="d-block w-100" alt="General Overview" />
                                    </div>
                                    <div className="carousel-item">
                                        <img src={HomePageCarouselSecond} className="d-block w-100" alt="Approved Cars" />
                                    </div>
                                    <div className="carousel-item">
                                        <img src={HomePageCarouselThird} className="d-block w-100" alt="Suzuki Alto" />
                                    </div>
                                    <div className="carousel-item">
                                        <img src={HomePageCarouselFourth} className="d-block w-100" alt="Suzuki Alto" />
                                    </div>
                                    <div className="carousel-item">
                                        <video className="img-fluid" autoPlay loop muted>
                                            <source src={HomePageCarouselFifth} type="video/mp4" />
                                        </video>
                                    </div>
                                    <div className="carousel-item">
                                        <img src={HomePageCarouselSixth} className="d-block w-100" alt="Swift Dzire" />
                                    </div>
                                    <div className="carousel-item">
                                        <img src={HomePageCarouselSeventh} className="d-block w-100" alt="Swift Dzire" />
                                    </div>
                                    <div className="carousel-item">
                                        <video className="img-fluid" autoPlay loop muted>
                                            <source src={HomePageCarouselEighth} type="video/mp4" />
                                        </video>
                                    </div>
                                </div>
                                <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
                                    data-bs-slide="prev">
                                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span className="visually-hidden">Previous</span>
                                </button>
                                <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
                                    data-bs-slide="next">
                                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span className="visually-hidden">Next</span>
                                </button>
                            </div>
                        </div>

                        <div className="col-lg-5 mb-2">
                            <h2 className="text-center">Electric Vehicle</h2>
                            <p>An electric vehicle is a vehicle that uses one or more electric motors for
                                propulsion. It can be powered by a collector system, with electricity from extravehicular
                                sources, or it can be powered autonomously by a battery (sometimes charged by solar panels,
                                or by converting fuel to electricity using fuel cells or a generator ).</p>
                            <p>The electric vehicle is a vehicle that runs on electricity alone. Such a vehicle does not
                                contain an internal combustion engine like the other conventional vehicles. Instead, it employs
                                an
                                electric motor to run the wheels. These vehicles are becoming very popular nowadays. They
                                are considered to be a promising solution for the future transportation.</p>
                            <p>Electric Battery: Battery stores the electricity required to run the vehicle. The battery
                                supplies electric current to the motor. And thus, the vehicle runs. The higher the capacity
                                of the battery the higher is the range of the electric vehicle. Most modern electric vehicles
                                use Lithium-ion type batteries. These batteries have higher energy density. It means they are
                                capable of storing more energy.</p>
                        </div>
                    </div>
                </div>
            </section>
            {/* end carousel slide */}

            {/* Multiple Images */}
            <div className="container">
                <div className="row">
                    <div className="col-lg-10 col-12 text-center mx-auto">
                        <h2 className="mb-5 mt-5">Select Your Dream Electric Car</h2>
                    </div>
                    <div className="wrapper1">
                        <i id="left" className="fa-solid fa-angle-left" />
                        <div className="carousel1">
                            <img src="images/Slide1.JPG" alt="img" draggable="false" />
                            <img src="images/Slide2.JPG" alt="img" draggable="false" />
                            <img src="images/Slide3.JPG" alt="img" draggable="false" />
                            <img src="images/Slide4.JPG" alt="img" draggable="false" />
                            <img src="images/Slide5.JPG" alt="img" draggable="false" />
                            <img src="images/Slide6.JPG" alt="img" draggable="false" />
                            <img src="images/Slide7.JPG" alt="img" draggable="false" />
                            <img src="images/Slide8.JPG" alt="img" draggable="false" />
                            <img src="images/Slide9.JPG" alt="img" draggable="false" />
                            <img src="images/Slide10.JPG" alt="img" draggable="false" />
                            <img src="images/Slide11.JPG" alt="img" draggable="false" />
                            <img src="images/Slide12.JPG" alt="img" draggable="false" />
                        </div>
                        <i id="right" className="fa-solid fa-angle-right" />
                    </div>
                </div>
            </div>

            {/* Multiple Images ends */}

        </>
    )
}

export default Home;