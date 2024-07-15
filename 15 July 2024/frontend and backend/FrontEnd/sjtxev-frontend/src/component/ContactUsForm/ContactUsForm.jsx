import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Typography from "@mui/material/Typography";
import FormHelperText from "@mui/material/FormHelperText";
import Select from "@mui/material/Select";
import { useEffect, useState } from "react";
import { SaveInquiry } from "../../services/CustomerContactService";
import { redirect } from "react-router-dom";
import { toast } from "react-toastify";
import { QuotationPdf } from "../QuotationPdf/QuotationPdf";
import { PDFDownloadLink } from "@react-pdf/renderer";
import { PDFViewer } from "@react-pdf/renderer";
import MobileNumberField from "../FormFields/MobileNumberField";

export default function ContactUsForm() {
  const [data, setData] = useState({
    fullName: "",
    mobileNumber: "",
    emailAddress: "",
    subject: "",
    message: "",
  });

  const [error, setError] = useState({
    errors: {},
    isError: false,
  });

  useEffect(() => {});

  const handleChange = (event, property) => {
    setData({ ...data, [property]: event.target.value });
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

    console.log(data);
    SaveInquiry(data)
      .then((response) => {
        console.log(response);
        toast.success(
          "Form submitted successfully. The inquiry id is : " +
            response.contactUsId
        );
        redirect("/contact-us");
        setError({ ...error, isError: false });
        setData({
          fullName: "",
          mobileNumber: "",
          emailAddress: "",
          subject: "",
          message: "",
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
    <div>
      {/* <PDFViewer>
        <QuotationPdf />
      </PDFViewer>
      <PDFDownloadLink document={<QuotationPdf />} fileName="Quotation.pdf">
        {({ blob, url, loading, error }) =>
          loading ? "Loading document..." : "Download now!"
        }
      </PDFDownloadLink> */}
      <Container component="main" maxWidth="sm">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Typography variant="h3">Contact Us Form</Typography>
          <Box
            component="form"
            onSubmit={handleSubmit}
            noValidate
            sx={{ mt: 1 }}
          >
            <TextField
              error={error.errors?.response?.data?.fullName ? true : false}
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
              error={error.errors?.response?.data?.mobileNumber ? true : false}
              helperText={error.errors?.response?.data?.mobileNumber}
            />
            <TextField
              error={error.errors?.response?.data?.emailAddress ? true : false}
              helperText={error.errors?.response?.data?.emailAddress}
              margin="normal"
              fullWidth
              id="emailAddress"
              label="Email address"
              name="emailAddress"
              value={data.emailAddress}
              onChange={(event) => handleChange(event, "emailAddress")}
            />
            <FormControl
              fullWidth
              margin="normal"
              required
              error={error.errors?.response?.data?.subject ? true : false}
            >
              <InputLabel id="subjectLabel">Subject</InputLabel>
              <Select
                labelId="subjectLabel"
                id="subject"
                name="subject"
                value={data.subject}
                label="Subject"
                onChange={(event) => handleChange(event, "subject")}
              >
                <MenuItem value={"General Inquiry"}>General Inquiry</MenuItem>
                <MenuItem value={"Get Quotation"}>Get Quotation</MenuItem>
                <MenuItem value={"Service Inquiry"}>Service Inquiry</MenuItem>
                <MenuItem value={"Supplier Opportunity"}>
                  Supplier Opportunity
                </MenuItem>
                <MenuItem value={"Investment Opportunity"}>
                  Investment Opportunity
                </MenuItem>
              </Select>
              <FormHelperText error>
                {error.errors?.response?.data?.subject}
              </FormHelperText>
            </FormControl>
            <TextField
              error={error.errors?.response?.data?.message ? true : false}
              helperText={error.errors?.response?.data?.message}
              margin="normal"
              fullWidth
              required
              id="message"
              name="message"
              label="Message"
              value={data.message}
              onChange={(event) => handleChange(event, "message")}
              multiline
              rows={4}
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Submit Form
            </Button>
          </Box>
        </Box>
      </Container>
    </div>
  );
}
