const brandsModels = {
  "Maruti Suzuki": ["alto", "swift-dzire", "celerio", "s-presso", "zen", "omni", "brezza", "swift", "ciaz", "eeco", "ritz", "wagon-r", "baleno", "ignis", "a-star"],
  "Hyundai": ["i10", "santro", "eon", "verna", "i20", "accent", "xcent"],
  "Renault": ["kwid", "triber"],
  "Datsun": ["datsun-go", "datsun-redi-go"],
  "Chevrolet": ["sail", "sail-u-va", "spark", "beat"],
  "Fiat": ["linea", "avventura", "punto"],
  "Ford": ["fiesta", "freestyle", "figo-aspire", "figo"],
  "Honda": ["city", "wr-v", "jazz", "amaze", "brio"],
  "Mahindra": ["kuv100", "verito"],
  "Nissan Datsun": ["go+"],
  "Skoda": ["fabia", "rapid"],
  "Tata": ["zest", "bolt", "indigo", "tigor", "tiago", "indica"],
  "Toyota": ["etios", "etios-cross", "liva"],
  "Volkswagen": ["vento", "ameo", "polo"],
}

const allModels = [
  "alto",
  "swift-dzire",
  "celerio",
  "s-presso",
  "zen",
  "omni",
  "brezza",
  "swift",
  "ciaz",
  "eeco",
  "ritz",
  "wagon-r",
  "baleno",
  "ignis",
  "a-star",
  "i10",
  "santro",
  "eON",
  "verna",
  "i20",
  "accent",
  "xcent",
  "kwid",
  "triber",
  "datsun-go",
  "datsun-redi-go",
  "sail",
  "sail-u-va",
  "spark",
  "beat",
  "linea",
  "avventura",
  "punto",
  "fiesta",
  "freestyle",
  "figo-aspire",
  "figo",
  "city",
  "wr-v",
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
  "etios-cross",
  "liva",
  "vento",
  "ameo",
  "polo",
];

function whenSelected(selectedElement) {
  this.clearModels();
  let selectedValue = selectedElement.value;
  let button = document.getElementById("toggleButton");
  button.textContent = "Show All Models";
  loadModels(brandsModels[selectedValue]);
}

function whenClicked(clickedImageValue) {
  let button = document.getElementById("toggleButton");
  let selectElement = document.getElementById("carModels");
  selectElement.selectedIndex = 0;
    if (button.textContent === "Show All Models") {
      loadModels(allModels);
    } else {
      clearModels();
    }
    button.textContent = button.textContent === "Show All Models" ? "Hide All Models" : "Show All Models";
}

function loadModels(modelsArray) {

  let allModelsDiv = document.getElementById("allModelsDiv");

  allModelsDiv.innerHTML = "";

  const iterableArray = [...modelsArray];

  let rowDiv = document.createElement("div");
  rowDiv.classList.add("row", "g-5");

  for (let i of iterableArray) {
    let outerDiv = document.createElement("div");
    outerDiv.classList.add("col-xxl-4", "col-xl-4", "col-lg-6", "col-md-6", "col-sm-12", "col-xs-12");
    let innerDiv = document.createElement("div");
    innerDiv.classList.add("card", "border-0");
    let anchor = document.createElement("a");
    anchor.href = `${i}_ev_xev.html`;
    anchor.classList.add("text-decoration-none");
    let imageDiv = document.createElement("div");
    imageDiv.classList.add("home-service-card-image-div");
    let image = document.createElement("img");
    image.src = `./images/products/${i}_ev_xev.jpg`;
    image.classList.add("img-fluid", "home-service-card-image");
    image.alt = `${i}_ev_xev`;
    imageDiv.appendChild(image);
    let footerDiv = document.createElement("div");
    footerDiv.classList.add("py-3", "text-center");
    footerDiv.style.backgroundColor = "#EFEFEF";
    let footerText = document.createElement("span");
    footerText.classList.add("text-decoration-none", "home-service-card-footer", "fw-medium");
    footerText.innerText = "Get a Quote";
    footerDiv.appendChild(footerText);
    anchor.appendChild(imageDiv);
    anchor.appendChild(footerDiv);
    innerDiv.appendChild(anchor);
    outerDiv.appendChild(innerDiv);
    rowDiv.appendChild(outerDiv);
  }
  allModelsDiv.appendChild(rowDiv);
}

function clearModels() {
  let allModelsDiv = document.getElementById("allModelsDiv");
  allModelsDiv.innerHTML = "";
}
