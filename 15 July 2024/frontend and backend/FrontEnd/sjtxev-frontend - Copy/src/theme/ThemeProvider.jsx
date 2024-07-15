import React from "react";
import {
  Experimental_CssVarsProvider as MaterialCssVarsProvider,
  experimental_extendTheme as extendMaterialTheme,
  THEME_ID,
} from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import { CssVarsProvider as JoyCssVarsProvider } from "@mui/joy/styles";
import PropTypes from 'prop-types';

// ----------------------------------------------------------------------

const materialTheme = extendMaterialTheme();

export default function ThemeProvider({ children }) {
  return (
    <MaterialCssVarsProvider
      theme={{ [THEME_ID]: materialTheme }}
    >
      <JoyCssVarsProvider>
        <CssBaseline enableColorScheme />
        {children}
      </JoyCssVarsProvider>
    </MaterialCssVarsProvider>
  );
}

ThemeProvider.propTypes = {
  children: PropTypes.node,
};
