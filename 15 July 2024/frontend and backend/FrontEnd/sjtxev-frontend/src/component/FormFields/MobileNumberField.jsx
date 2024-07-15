import React from "react";
import {
  InputAdornment,
  MenuItem,
  Select,
  TextField,
  Typography,
} from "@mui/material";
import {
  defaultCountries,
  FlagImage,
  parseCountry,
  usePhoneInput,
} from "react-international-phone";
import { PhoneNumberUtil } from "google-libphonenumber";

const phoneUtil = PhoneNumberUtil.getInstance();

const isPhoneValid = (phone) => {
  try {
    return phoneUtil.isValidNumber(phoneUtil.parseAndKeepRawInput(phone));
  } catch (error) {
    return false;
  }
};

const MobileNumberField = ({ value, onChange, isError, helperText }) => {
  const { phone, handlePhoneValueChange, inputRef, country, setCountry } =
    usePhoneInput({
      defaultCountry: "in",
      value,
      countries: defaultCountries,
      onChange: (data) => {
        onChange(data.phone);
      },
    });

  const isValid = isPhoneValid(phone);

  return (
    <TextField
      error={!isValid || isError}
      helperText={isValid ? `` : `Please enter a valid mobile number`}
      required
      fullWidth
      margin="normal"
      variant="outlined"
      label="Mobile number"
      color="primary"
      placeholder="Mobile number"
      id="mobileNumber"
      name="mobileNumber"
      value={phone}
      onChange={handlePhoneValueChange}
      type="tel"
      inputRef={inputRef}
      InputProps={{
        startAdornment: (
          <InputAdornment
            position="start"
            style={{ marginRight: "2px", marginLeft: "-8px" }}
          >
            <Select
              MenuProps={{
                style: {
                  height: "300px",
                  width: "360px",
                  top: "10px",
                  left: "-34px",
                },
                transformOrigin: {
                  vertical: "top",
                  horizontal: "left",
                },
              }}
              sx={{
                width: "max-content",
                fieldset: {
                  display: "none",
                },
                '&.Mui-focused:has(div[aria-expanded="false"])': {
                  fieldset: {
                    display: "block",
                  },
                },
                ".MuiSelect-select": {
                  padding: "8px",
                  paddingRight: "24px !important",
                },
                svg: {
                  right: 0,
                },
              }}
              value={country.iso2}
              onChange={(e) => setCountry(e.target.value)}
              renderValue={() => (
                <FlagImage
                  iso2={country.iso2}
                  style={{ display: "flex", width: "20px", height: "20px" }}
                />
              )}
            >
              {defaultCountries.map((c) => {
                const countryOption = parseCountry(c);
                return (
                  <MenuItem key={countryOption.iso2} value={countryOption.iso2}>
                    <FlagImage
                      iso2={countryOption.iso2}
                      style={{
                        marginRight: "8px",
                        width: "20px",
                        height: "20px",
                      }}
                    />
                    <Typography marginRight="8px">
                      {countryOption.name}
                    </Typography>
                    <Typography color="gray">
                      +{countryOption.dialCode}
                    </Typography>
                  </MenuItem>
                );
              })}
            </Select>
          </InputAdornment>
        ),
      }}
    />
  );
};

export default MobileNumberField;
