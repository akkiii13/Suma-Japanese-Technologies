import { myAxios } from "./helper";

const bookingForm = (formData) => {
    return myAxios.post('/booking', formData);
}