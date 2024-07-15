window.onpaint = carModelsCarousel();


const allModels = [
  "alto",
  "swift_dzire",
  "celerio",
  "s_presso",
  "zen",
  "omni",
  "brezza",
  "swift",
  "ciaz",
  "eeco",
  "ritz",
  "wagonR",
  "baleno",
  "ignis",
  "a_star",
  "i10",
  "santro",
  "eON",
  "verna",
  "i20",
  "accent",
  "xcent",
  "kwid",
  "triber",
  "datsun_go",
  "datsun_redi_go",
  "sail",
  "sail_u_va",
  "spark",
  "beat",
  "linea",
  "avventura",
  "punto",
  "fiesta",
  "freestyle",
  "figo Aspire",
  "figo",
  "city",
  "wr_v",
  "jazz",
  "amaze",
  "brio",
  "kUV100",
  "verito",
  "go+",
  "fabia",
  "rapid",
  "zest",
  "bolt",
  "indigo",
  "tigor",
  "tiago",
  "indica",
  "etios",
  "etios Cross",
  "liva",
  "vento",
  "ameo",
  "polo",
];

function carModelsCarousel() {
  let allModelsDiv = document.getElementById("allModelsDiv");
  for (let i of allModels) {
    // Create the outer div element
    const outerDiv = document.createElement("div");
    outerDiv.classList.add("owl-item");

    // Create the inner div for the card
    const innerDiv = document.createElement("div");
    innerDiv.classList.add("card", "h-100", "position-relative", "home-service-card");

    // Create the image div
    const imageDiv = document.createElement("div");
    imageDiv.classList.add("home-service-card-image-div", "rounded-top");

    // Create the image element
    const image = document.createElement("img");
    image.src = "./images/products/" + i + "_ev_xev.jpg";
    image.classList.add("card-img-top", "img-fluid", "home-service-card-image");
    image.alt = "...";
    image.style.objectFit = "contain";

    // Append the image to the image div
    imageDiv.appendChild(image);

    // Create the card footer div
    const cardFooter = document.createElement("div");
    cardFooter.classList.add("card-footer", "bg-transparent", "border-success", "py-3");

    // Create the anchor element
    const anchor = document.createElement("a");
    anchor.href = "alto_ev_xev.html";
    anchor.classList.add("text-decoration-none", "home-service-card-footer", "fw-medium");
    anchor.innerText = "BOOK NOW";

    // Append the anchor to the card footer
    cardFooter.appendChild(anchor);

    // Append the image div and card footer to the inner div
    innerDiv.appendChild(imageDiv);
    innerDiv.appendChild(cardFooter);

    // Append the inner div to the outer div
    outerDiv.appendChild(innerDiv);

    allModelsDiv.appendChild(outerDiv);

  }
}

$(document).ready(function () {
  let home_service_owl_images = $("#home-service-owl-images");
  home_service_owl_images.owlCarousel({
    autoPlay: true,
    autoplayTimeout: 3000,
    autoplayHoverPause: true,

    slideSpeed: 3000,

    loop: true,

    goToFirst: true,

    goToFirstSpeed: 3000,

    mouseDrag: true,

    pagination: true,
    paginationNumbers: true,
    paginationSpeed: 3000,

    animateOut: "fadeOut",
    animateIn: "fadeIn",

    nav: false,
    navText: [
      "<i class='fa-solid fa-angle-left fa-2xl' style='color: #7ab629;'></i>",
      "<i class='fa fa-angle-right fa-2xl' style='color: #7AB629;'></i>",
    ],

    items: 12,
    responsive: {
      0: {
        items: 1,
      },
      576: {
        items: 2,
      },
      992: {
        items: 3,
      },
      1400: {
        items: 4,
      },
    },
  });

  $("#home-service-owl-images .owl-next").click(function () {
    home_service_owl_images.trigger("next.owl.carousel");
  });
  $("#home-service-owl-images .owl-prev").click(function () {
    home_service_owl_images.trigger("prev.owl.carousel");
  });
});
