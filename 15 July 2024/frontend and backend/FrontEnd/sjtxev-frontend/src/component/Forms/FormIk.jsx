import {
  Grid,
  Paper,
  TextField,
  Button,
  Container,
  CssBaseline,
} from "@mui/material";
import { Formik, Field, Form, ErrorMessage } from "formik";
import * as Yup from "yup";

export default function SignUp() {
  const initialValues = {
    username: "",
    email: "",
    gender: "",
    phoneNumber: "",
    password: "",
    confirmPassword: "",
    termsAndConditions: false,
    rememberMe: false,
  };
  const onSubmit = (values, props) => {
    console.log(values);
    setTimeout(() => {
      props.resetForm();
      props.setSubmitting(false);
    }, 2000);
    console.log(props);
  };
  const validationSchema = Yup.object().shape({
    username: Yup.string().required("Username is required"),
  });

  return (
    <Container
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <Paper
        elevation={24}
        component="main"
        maxWidth="md"
        sx={{
          m: { xs: 2, sm: 3, md: 4, lg: 5, xl: 6 },
          paddingTop: 5,
          paddingBottom: 5,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Formik
          initialValues={initialValues}
          onSubmit={onSubmit}
          validationSchema={validationSchema}
        >
          {(props) => (
            <Form>
              <Field
                as={TextField}
                margin="normal"
                fullWidth
                name="username"
                label="Username"
                placeholder="Enter your username"
                helperText={<ErrorMessage name="username" />}
              />
              {/* {console.log(props)} */}
              <Button
                type="submit"
                color="primary"
                varient="contained"
                margin="normal"
                fullWidth
                disabled={props.isSubmitting}
              >
                {props.isSubmitting ? "Loading" : "Sign up"}
              </Button>
            </Form>
          )}
        </Formik>
      </Paper>
    </Container>
  );
}
