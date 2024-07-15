// const brandsModels = {
//     "Maruti Suzuki": ["Alto", "Swift Dzire", "Celerio", "S-presso", "Zen", "Omni", "Brezza", "Swift", "Ciaz", "Eeco", "Ritz", "WagonR", "Baleno", "Ignis", "A-star"],
//     "Hyundai": ["i10", "Santro", "EON", "Verna", "i20", "Accent", "Xcent"],
//     "Renault": ["Kwid", "Triber"],
//     "Datsun": ["Datsun - Go", "Datsun - Redi-Go"],
//     "Chevrolet": ["Sail", "Sail U-VA", "Spark", "Beat"],
//     "Fiat": ["Linea", "Avventura", "Punto"],
//     "Ford": ["Fiesta", "Freestyle", "Figo Aspire", "Figo"],
//     "Honda": ["City", "WR-V", "Jazz", "Amaze", "Brio"],
//     "Mahindra": ["KUV100", "Verito"],
//     "Nissan Datsun": [" Go+"],
//     "Skoda": ["Fabia", "Rapid"],
//     "Tata": ["Zest", "Bolt", "Indigo", "Tigor", "Tiago", "Indica"],
//     "Toyota": ["Etios", "Etios Cross", "Liva"],
//     "Volkswagen": ["Vento", "Ameo", "Polo"],
// }
// const modelYearForSwiftDzire = [2023, 2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013, 2012];
// const modelYearForOthers = [2023, 2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013, 2012, 2011];
// function makeSubmenu(value) {
//     let selectModel = document.getElementById("selectModel");
//     if (value.length < 1) selectModel.innerHTML = "<option></option>";
//     else {
//         let modelOptions = "";
//         for (let model in brandsModels[value]) {
//             modelOptions += "<option>" + brandsModels[value][model] + "</option>";
//         }
//         selectModel.innerHTML = modelOptions;
//     }
// }
// function selectingYears(value) {
//     let modelYear = document.getElementById("modelYear");
//     let yearOptions = "";
//     if (value == "") {
//         yearOptions += "<option value='' selected='selected' disabled>Choose Model first</option>";
//     }
//     else if (value === "Swift Dzire") {
//         for (let year in modelYearForSwiftDzire) {
//             yearOptions += `<option value=${modelYearForSwiftDzire[year]}>` + modelYearForSwiftDzire[year] + "</option>";
//         }
//     } else {
//         for (let year in modelYearForOthers) {
//             yearOptions += `<option value=${modelYearForOthers[year]}>` + modelYearForOthers[year] + "</option>";
//         }
//     }
//     modelYear.innerHTML = yearOptions;
// }

const brandsModels = {
    "Maruti Suzuki": ["Alto", "Swift Dzire", "Celerio", "S-presso", "Zen", "Omni"],
    "Hyundai": ["i10", "Santro", "EON"],
    "Renault": ["Kwid"],
    "Datsun": ["Datsun - Go", "Datsun - Redi-Go"],
}
const modelYearForSwiftDzire = [2023, 2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013, 2012];
const modelYearForOthers = [2023, 2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013, 2012, 2011];
function makeSubmenu(value) {
    let selectModel = document.getElementById("selectModel");
    if (value.length < 1) selectModel.innerHTML = "<option></option>";
    else {
        let modelOptions = "";
        for (let model in brandsModels[value]) {
            modelOptions += "<option>" + brandsModels[value][model] + "</option>";
        }
        selectModel.innerHTML = modelOptions;
    }
}
function selectingYears(value) {
    let modelYear = document.getElementById("modelYear");
    let yearOptions = "";
    if (value == "") {
        yearOptions += "<option value='' selected='selected' disabled>Choose Model first</option>";
    }
    else if (value === "Swift Dzire") {
        for (let year in modelYearForSwiftDzire) {
            yearOptions += `<option value=${modelYearForSwiftDzire[year]}>` + modelYearForSwiftDzire[year] + "</option>";
        }
    } else {
        for (let year in modelYearForOthers) {
            yearOptions += `<option value=${modelYearForOthers[year]}>` + modelYearForOthers[year] + "</option>";
        }
    }
    modelYear.innerHTML = yearOptions;
}

function sendEmail() {
    const fullName = document.getElementById("fullName").value;
    Email.send({
        Host: "smtp.elasticemail.com",
        Username: "sumajapanesebavdhan@gmail.com",
        Password: "07C0E298C6CBB12784A9745D1A2D06C3091F",
        To: 'info@sjtev.com',
        From: "sumajapanesebavdhan@gmail.com",
        Subject: "New Booking Form",
        Body: "Full Name: " + fullName
            + "<br> Mobile No: " + document.getElementById("mobileNumberPrefix").value
            + " " + document.getElementById("mobileNumber").value
            + "<br> Email: " + document.getElementById("email").value
            + "<br> Address: " + document.getElementById("ownersAddress").value
            + "<br> Pincode: " + document.getElementById("ownersPincode").value
            + "<br> Brand: " + document.getElementById("selectBrand").value
            + "<br> Model: " + document.getElementById("selectModel").value
            + "<br> Model Year: " + document.getElementById("modelYear").value
            + "<br> Transmission: Manual"
            + "<br> Fuel: " + document.getElementById("fuel").value
            + "<br> Car Colour: " + document.getElementById("carColour").value
            + "<br> Regisration Number: " + document.getElementById("regisrationNumber").value
            + "<br> Chassis Number: " + document.getElementById("chassisNumber").value
            + "<br> Motor Number: " + document.getElementById("motorNumber").value
            + "<br> Conversion Date: " + document.getElementById("conversionDate").value
            + "<br> Car Location Town: " + document.getElementById("carLocationTown").value
            + "<br> Car Location Pincode: " + document.getElementById("carLocationPincode").value
            + "<br> Any other information: " + document.getElementById("otherMessage").value
            + "<br> Thank you. Our sales team will contact you soon."
    }).then(
        message => alert(`Thank you ${fullName}.\nOur sales team will contact you soon.`),
        console.log(`Thank you ${fullName}.\nOur sales team will contact you soon.`),
    );
}