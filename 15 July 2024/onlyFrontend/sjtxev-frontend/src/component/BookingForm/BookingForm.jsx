// import * as React from "react";
// import Avatar from "@mui/material/Avatar";
// import Button from "@mui/material/Button";
// import CssBaseline from "@mui/material/CssBaseline";
// import TextField from "@mui/material/TextField";
// import FormControlLabel from "@mui/material/FormControlLabel";
// import Checkbox from "@mui/material/Checkbox";
// import Link from "@mui/material/Link";
// import Grid from "@mui/material/Grid";
// import Box from "@mui/material/Box";
// import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
// import Typography from "@mui/material/Typography";
// import Container from "@mui/material/Container";
// import IconButton from "@mui/material/IconButton";
// import OutlinedInput from "@mui/material/OutlinedInput";
// import InputLabel from "@mui/material/InputLabel";
// import InputAdornment from "@mui/material/InputAdornment";
// import FormControl from "@mui/material/FormControl";
// import Visibility from "@mui/icons-material/Visibility";
// import VisibilityOff from "@mui/icons-material/VisibilityOff";

// export default function SignIn() {
//   const [showPassword, setShowPassword] = React.useState(false);

//   const handleClickShowPassword = () => setShowPassword((show) => !show);

//   const handleMouseDownPassword = (event) => {
//     event.preventDefault();
//   };

//   const handleSubmit = (event) => {
//     event.preventDefault();
//     const data = new FormData(event.currentTarget);
//     console.log({
//       email: data.get("email"),
//       password: data.get("password"),
//     });
//   };

//   return (
//     <Container component="main" maxWidth="xs">
//       <CssBaseline />
//       <Box
//         sx={{
//           marginTop: 8,
//           display: "flex",
//           flexDirection: "column",
//           alignItems: "center",
//         }}
//       >
//         <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
//           <LockOutlinedIcon />
//         </Avatar>
//         <Typography component="h1" variant="h5">
//           Sign in
//         </Typography>
//         <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
//           <TextField
//             margin="normal"
//             required
//             fullWidth
//             id="email"
//             label="Email Address"
//             name="email"
//             autoComplete="email"
//             autoFocus
//           />
//           <TextField
//             margin="normal"
//             required
//             fullWidth
//             name="password"
//             label="Password"
//             type="password"
//             id="password"
//             autoComplete="current-password"
//           />
//           <FormControlLabel
//             control={<Checkbox value="remember" color="primary" />}
//             label="Remember me"
//           />
//           <Button
//             type="submit"
//             fullWidth
//             variant="contained"
//             sx={{ mt: 3, mb: 2 }}
//           >
//             Sign In
//           </Button>
//           <Grid container>
//             <Grid item xs>
//               <Link href="#" variant="body2">
//                 Forgot password?
//               </Link>
//             </Grid>
//             <Grid item>
//               <Link href="#" variant="body2">
//                 {"Don't have an account? Sign Up"}
//               </Link>
//             </Grid>
//           </Grid>
//         </Box>
//       </Box>
//     </Container>
//   );
// }












































// import React, { useEffect, useState } from 'react';
// import {
//   Box,
//   FormControlLabel,
//   FormGroup,
//   FormHelperText,
//   Typography,
// } from '@mui/material';
// import { useForm, SubmitHandler, FormProvider } from 'react-hook-form';
// import { literal, object, string, z } from 'zod';
// import { zodResolver } from '@hookform/resolvers/zod';
// import { LoadingButton } from '@mui/lab';
// import Checkbox from '@mui/material/Checkbox';
// import FormInput from '../components/FormInput';

// const registerSchema = object({
//   name: string()
//     .nonempty('Name is required')
//     .max(32, 'Name must be less than 100 characters'),
//   email: string().nonempty('Email is required').email('Email is invalid'),
//   password: string()
//     .nonempty('Password is required')
//     .min(8, 'Password must be more than 8 characters')
//     .max(32, 'Password must be less than 32 characters'),
//   passwordConfirm: string().nonempty('Please confirm your password'),
//   terms: literal(true, {
//     invalid_type_error: 'Accept Terms is required',
//   }),
// }).refine((data) => data.password === data.passwordConfirm, {
//   path: ['passwordConfirm'],
//   message: 'Passwords do not match',
// });

// const RegisterPage2 = () => {
//   const [loading, setLoading] = useState(false);

//   const methods = useForm({
//     resolver: zodResolver(registerSchema),
//   });

//   const {
//     reset,
//     handleSubmit,
//     register,
//     formState: { isSubmitSuccessful, errors },
//   } = methods;

//   useEffect(() => {
//     if (isSubmitSuccessful) {
//       reset();
//     }
//     // eslint-disable-next-line react-hooks/exhaustive-deps
//   }, [isSubmitSuccessful]);

//   const onSubmitHandler = (values) => {
//     console.log(values);
//   };
//   console.log(errors);

//   return (
//     <Box sx={{ maxWidth: '30rem' }}>
//       <Typography variant='h4' component='h1' sx={{ mb: '2rem' }}>
//         Register
//       </Typography>
//       <FormProvider {...methods}>
//         <Box
//           component='form'
//           noValidate
//           autoComplete='off'
//           onSubmit={handleSubmit(onSubmitHandler)}
//         >
//           <FormInput
//             name='name'
//             required
//             fullWidth
//             label='Name'
//             sx={{ mb: 2 }}
//           />

//           <FormInput
//             name='email'
//             required
//             fullWidth
//             label='Email Address'
//             type='email'
//             sx={{ mb: 2 }}
//           />
//           <FormInput
//             name='password'
//             required
//             fullWidth
//             label='Password'
//             type='password'
//             sx={{ mb: 2 }}
//           />
//           <FormInput
//             name='passwordConfirm'
//             required
//             fullWidth
//             label='Confirm Password'
//             type='password'
//             sx={{ mb: 2 }}
//           />
//           <FormGroup>
//             <FormControlLabel
//               control={<Checkbox required />}
//               {...register('terms')}
//               label={
//                 <Typography color={errors['terms'] ? 'error' : 'inherit'}>
//                   Accept Terms and Conditions
//                 </Typography>
//               }
//             />
//             <FormHelperText error={!!errors['terms']}>
//               {errors['terms'] ? errors['terms'].message : ''}
//             </FormHelperText>
//           </FormGroup>

//           <LoadingButton
//             variant='contained'
//             fullWidth
//             type='submit'
//             loading={loading}
//             sx={{ py: '0.8rem', mt: '1rem' }}
//           >
//             Register
//           </LoadingButton>
//         </Box>
//       </FormProvider>
//     </Box>
//   );
// };

// export default RegisterPage2;
