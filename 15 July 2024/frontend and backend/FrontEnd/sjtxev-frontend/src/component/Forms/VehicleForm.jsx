import { useEffect, useState } from "react";
import CssBaseline from "@mui/material/CssBaseline";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import FormHelperText from "@mui/material/FormHelperText";
import Select from "@mui/material/Select";
import Autocomplete from "@mui/material/Autocomplete";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import dayjs from "dayjs";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { MobileDatePicker } from "@mui/x-date-pickers/MobileDatePicker";
import { toast } from "react-toastify";
import { redirect } from "react-router-dom";
import { saveInquiry } from "../../services/InquiryService";
import { Send } from "@mui/icons-material";

export default function VehicleForm() {
  const [data, setData] = useState({
    brand: "",
    model: "",
    modelYear: "",
    transmission: "",
    fuel: [],
    carColour: "",
    registrationNumber: "",
    registrationDate: dayjs(),
    chassisNumber: "",
    motorNumber: "",
    expectedConversionDate: dayjs(),
    rtoLocation: "",
    cityOfCarLocation: "",
    pincodeOfCarLocation: "",
    anyOtherInformation: "",
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

    const fuelString = data.fuel.join(",");
    const newData = { ...data, fuel: fuelString };

    console.log(newData);

    if (error.isError) {
      setError({ ...error, isError: true });
    }

    saveInquiry(newData)
      .then((response) => {
        console.log(response);
        toast.success(
          "Form submitted successfully. The booking id is : " +
            response.bookingId
        );
        redirect("/booking");
        setError({ ...error, isError: false });
        setData({
          brand: "",
          model: "",
          modelYear: "",
          transmission: "",
          fuel: [],
          carColour: "",
          registrationNumber: "",
          registrationDate: dayjs(""),
          chassisNumber: "",
          motorNumber: "",
          expectedConversionDate: dayjs(""),
          rtoLocation: "",
          cityOfCarLocation: "",
          pincodeOfCarLocation: "",
          anyOtherInformationAboutVehicle: "",
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
        <Typography variant="h3">Vehicle Details</Typography>
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          <TextField
            autoFocus
            error={Boolean(error.errors?.response?.data?.brand)}
            helperText={error.errors?.response?.data?.brand}
            margin="normal"
            fullWidth
            id="brand"
            label="Brand"
            name="brand"
            value={data.brand}
            onChange={(event) => handleChange(event, "brand")}
            multiline
          />
          <TextField
            error={Boolean(error.errors?.response?.data?.model)}
            helperText={error.errors?.response?.data?.model}
            margin="normal"
            fullWidth
            id="model"
            label="Model"
            name="model"
            value={data.model}
            onChange={(event) => handleChange(event, "model")}
            multiline
          />
          <TextField
            error={Boolean(error.errors?.response?.data?.modelYear)}
            helperText={error.errors?.response?.data?.modelYear}
            margin="normal"
            fullWidth
            id="modelYear"
            label="Model Year"
            name="modelYear"
            value={data.modelYear}
            onChange={(event) => handleChange(event, "modelYear")}
            multiline
          />
          <FormControl
            fullWidth
            margin="normal"
            error={Boolean(error.errors?.response?.data?.transmission)}
          >
            <InputLabel id="transmissionLabel">Transmission</InputLabel>
            <Select
              labelId="transmissionLabel"
              id="transmission"
              name="transmission"
              value={data.transmission}
              label="Transmission"
              onChange={(event) => handleChange(event, "transmission")}
            >
              <MenuItem value={"Manual"}>Manual</MenuItem>
              <MenuItem value={"Automatic"}>Automatic</MenuItem>
            </Select>
            <FormHelperText error>
              {error.errors?.response?.data?.transmission}
            </FormHelperText>
          </FormControl>
          <Autocomplete
            multiple
            id="tags-outlined"
            options={fuelTypes}
            getOptionLabel={(option) => option}
            onChange={(event, value) => handleChange(value, "fuel")}
            filterSelectedOptions
            renderInput={(params) => (
              <TextField
                margin="normal"
                fullWidth
                {...params}
                label="Fuel type"
              />
            )}
          />
          <TextField
            error={Boolean(error.errors?.response?.data?.carColour)}
            helperText={error.errors?.response?.data?.carColour}
            margin="normal"
            fullWidth
            id="carColour"
            label="Car colour"
            name="carColour"
            value={data.carColour}
            onChange={(event) => handleChange(event, "carColour")}
          />
          <TextField
            error={Boolean(error.errors?.response?.data?.registrationNumber)}
            helperText={error.errors?.response?.data?.registrationNumber}
            margin="normal"
            fullWidth
            id="registrationNumber"
            label="Registration number"
            name="registrationNumber"
            value={data.registrationNumber}
            onChange={(event) => handleChange(event, "registrationNumber")}
          />
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <MobileDatePicker
              sx={{ width: "100%", mt: 2, mb: 1 }}
              label="Registration date"
              variant="outlined"
              defaultValue={dayjs("2022-04-17")}
              value={data.registrationDate}
              onChange={(event) => handleChange(event, "registrationDate")}
            />
          </LocalizationProvider>
          <TextField
            error={Boolean(error.errors?.response?.data?.chassisNumber)}
            helperText={error.errors?.response?.data?.chassisNumber}
            margin="normal"
            fullWidth
            id="chassisNumber"
            label="Chassis number"
            name="chassisNumber"
            value={data.chassisNumber}
            onChange={(event) => handleChange(event, "chassisNumber")}
          />
          <TextField
            error={Boolean(error.errors?.response?.data?.motorNumber)}
            helperText={error.errors?.response?.data?.motorNumber}
            margin="normal"
            fullWidth
            id="motorNumber"
            label="Motor number"
            name="motorNumber"
            value={data.motorNumber}
            onChange={(event) => handleChange(event, "motorNumber")}
          />
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <MobileDatePicker
              sx={{ width: "100%", mt: 2, mb: 1 }}
              label="Expected conversion date"
              variant="outlined"
              defaultValue={dayjs("2022-04-17")}
              value={data.expectedConversionDate}
              onChange={(event) =>
                handleChange(event, "expectedConversionDate")
              }
            />
          </LocalizationProvider>
          <TextField
            error={Boolean(error.errors?.response?.data?.rtoLocation)}
            helperText={error.errors?.response?.data?.rtoLocation}
            margin="normal"
            fullWidth
            id="rtoLocation"
            label="RTO Location"
            name="rtoLocation"
            value={data.rtoLocation}
            onChange={(event) => handleChange(event, "rtoLocation")}
          />
          <TextField
            error={Boolean(error.errors?.response?.data?.pincodeOfCarLocation)}
            helperText={error.errors?.response?.data?.pincodeOfCarLocation}
            margin="normal"
            fullWidth
            id="pincodeOfCarLocation"
            label="Pincode of car location"
            name="pincodeOfCarLocation"
            value={data.pincodeOfCarLocation}
            onChange={(event) => handleChange(event, "pincodeOfCarLocation")}
          />
          <TextField
            error={Boolean(error.errors?.response?.data?.cityOfCarLocation)}
            helperText={error.errors?.response?.data?.cityOfCarLocation}
            margin="normal"
            fullWidth
            id="cityOfCarLocation"
            label="City of car location"
            name="cityOfCarLocation"
            value={data.cityOfCarLocation}
            onChange={(event) => handleChange(event, "cityOfCarLocation")}
          />
          <TextField
            error={Boolean(
              error.errors?.response?.data?.anyOtherInformationAboutVehicle
            )}
            helperText={
              error.errors?.response?.data?.anyOtherInformationAboutVehicle
            }
            margin="normal"
            fullWidth
            id="anyOtherInformationAboutVehicle"
            label="Any other information"
            name="anyOtherInformationAboutVehicle"
            value={data.anyOtherInformationAboutVehicle}
            onChange={(event) =>
              handleChange(event, "anyOtherInformationAboutVehicle")
            }
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
            Submit Vehicle Details
          </Button>
        </Box>
      </Box>
    </Container>
  );
}

const fuelTypes = ["Petrol", "Diesel", "CNG"];
