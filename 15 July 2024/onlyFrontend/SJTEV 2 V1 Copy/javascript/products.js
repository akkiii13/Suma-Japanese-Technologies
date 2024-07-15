// const brandsModels = {
//     "Chevrolet" : ["Sail", "Sail U-VA", "Spark", "Beat"],
//     "Fiat" : ["Linea", "Avventura", "Punto"],
//     "Ford" : ["Fiesta", "Freestyle", "Figo Aspire", "Figo"],
//     "Honda" : ["City", "WR-V", "Jazz", "Amaze", "Brio"],
//     "Hyundai" : ["Verna", "i20", "Accent", "Xcent"],
//     "Mahindra" : ["KUV100", "Verito"],
//     "Maruti Suzuki" : ["Brezza", "Swift", "Ciaz", "Eeco", "Ritz", "WagonR", "Baleno", "Ignis", "A-star"],
//     "Nissan Datsun" : [" Go+"],
//     "Renault" : ["Triber"],
//     "Skoda" : ["Fabia", "Rapid"],
//     "Tata" : ["Zest", "Bolt", "Indigo", "Tigor", "Tiago", "Indica"],
//     "Toyota" : ["Etios", "Etios Cross", "Liva"],
//     "Volkswagen" : ["Vento", "Ameo", "Polo"],
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