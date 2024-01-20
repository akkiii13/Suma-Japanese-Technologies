import { myAxios } from "./helper";

export const ContactUsInquiryService = (formData) => {
    return myAxios
        .post("/contactus/save", formData)
        .then((response) => response.data);
};
