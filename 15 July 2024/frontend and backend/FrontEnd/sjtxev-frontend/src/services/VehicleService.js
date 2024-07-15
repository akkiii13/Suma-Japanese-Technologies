import { myAxios } from "./helper";

export const SaveInquiry = (formData) => {
  return myAxios
    .post(`/vehicles/save/${inquiryId}`, formData)
    .then((response) => response.data);
};

export const GetAllInquiries = () => {
  return myAxios
    .get(`/vehicles/get/getAllVehicles`)
    .then((response) => response.data);
};
