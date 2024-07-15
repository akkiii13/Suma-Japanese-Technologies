import { myAxios } from "./helper";

export const saveInquiry = (formData) => {
  console.log(formData);
  return myAxios
    .post("/inquiries/save", formData)
    .then((response) => response.data);
};

export const GetAllInquiries = () => {
  return myAxios
    .get(`/inquiries/getAllInquiries`)
    .then((response) => response.data);
};