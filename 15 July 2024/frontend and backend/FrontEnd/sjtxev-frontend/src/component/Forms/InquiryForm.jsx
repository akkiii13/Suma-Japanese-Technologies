import { useEffect, useState } from "react";
import CssBaseline from "@mui/material/CssBaseline";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { toast } from "react-toastify";
import MobileNumberField from "../FormFields/MobileNumberField";
import { redirect } from "react-router-dom";
import { saveInquiry } from "../../services/InquiryService";
import { Send } from '@mui/icons-material';

export default function InquiryForm() {
  const [data, setData] = useState({
    fullName: "",
    mobileNumber: "",
    emailAddress: "",
    ownerAddress: "",
    ownerPincode: "",
    anyOtherInformationAboutInquiry: "",
  });

  const [error, setError] = useState({
    errors: {},
    isError: false,
  });

  useEffect(() => {});

  const handleChange = (event, property) => {
    if (event.target) {
      setData({ ...data, [property]: event.target.value });
    } else {
      setData({ ...data, [property]: event });
    }
    setError((prevError) => ({
      errors: { ...prevError.errors, [property]: false },
      isError: false,
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    console.log(data);

    if (error.isError) {
      setError({ ...error, isError: true });
    }

    saveInquiry(data)
      .then((response) => {
        console.log(response);
        toast.success(
          "Form submitted successfully. The booking id is : " +
            response.bookingId
        );
        redirect("/booking");
        setError({ ...error, isError: false });
        setData({
          fullName: "",
          mobileNumber: "",
          emailAddress: "",
          ownerAddress: "",
          ownerPincode: "",
          anyOtherInformationAboutInquiry: "",
        });
      })
      .catch((error) => {
        toast.error("Errors in form submission failed: " + error);
        console.log(error);
        setError({
          errors: error,
          isError: true,
        });
      });
  };

  return (
    <Container component="main" maxWidth="sm">
      <CssBaseline />
      <Box
        sx={{
          marginTop: 8,
          marginBottom: 8,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Typography variant="h3">Inquiry Form</Typography>
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          <TextField
            error={Boolean(error.errors?.response?.data?.fullName)}
            helperText={error.errors?.response?.data?.fullName}
            margin="normal"
            required
            fullWidth
            id="fullName"
            label="Full name"
            name="fullName"
            value={data.fullName}
            autoComplete="fullName"
            autoFocus
            onChange={(event) => handleChange(event, "fullName")}
          />
          <MobileNumberField
            value={data.mobileNumber}
            onChange={(mobile) =>
              handleChange({ target: { value: mobile } }, "mobileNumber")
            }
            error={Boolean(error.errors?.response?.data?.mobileNumber)}
            helperText={error.errors?.response?.data?.mobileNumber}
          />
          <TextField
            error={Boolean(error.errors?.response?.data?.emailAddress)}
            helperText={error.errors?.response?.data?.emailAddress}
            margin="normal"
            fullWidth
            id="emailAddress"
            label="Email address"
            name="emailAddress"
            value={data.emailAddress}
            onChange={(event) => handleChange(event, "emailAddress")}
          />
          <TextField
            error={Boolean(error.errors?.response?.data?.ownerAddress)}
            helperText={error.errors?.response?.data?.ownerAddress}
            margin="normal"
            fullWidth
            id="ownerAddress"
            label="Owner's address"
            name="ownerAddress"
            value={data.ownerAddress}
            onChange={(event) => handleChange(event, "ownerAddress")}
            multiline
          />
          <TextField
            error={Boolean(error.errors?.response?.data?.ownerPincode)}
            helperText={error.errors?.response?.data?.ownerPincode}
            margin="normal"
            fullWidth
            id="ownerPincode"
            label="Pincode"
            name="ownerPincode"
            value={data.ownerPincode}
            onChange={(event) => handleChange(event, "ownerPincode")}
            multiline
          />
          <TextField
            error={Boolean(error.errors?.response?.data?.anyOtherInformationAboutInquiry)}
            helperText={error.errors?.response?.data?.anyOtherInformationAboutInquiry}
            margin="normal"
            fullWidth
            id="anyOtherInformationAboutInquiry"
            label="Any other information"
            name="anyOtherInformationAboutInquiry"
            value={data.anyOtherInformationAboutInquiry}
            onChange={(event) => handleChange(event, "anyOtherInformationAboutInquiry")}
            multiline
            rows={4}
          />
          <Button
            type="submit"
            fullWidth
            variant="outlined"
            endIcon={<Send />}
            sx={{ mt: 3, mb: 2 }}
          >
            Submit Inquiry
          </Button>
        </Box>
      </Box>
    </Container>
  );
}
