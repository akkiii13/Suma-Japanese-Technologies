import React from "react";
import { useMemo } from 'react';
import {
  Experimental_CssVarsProvider as MaterialCssVarsProvider,
  experimental_extendTheme as extendMaterialTheme,
  THEME_ID,
  createTheme,
} from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import { CssVarsProvider as JoyCssVarsProvider } from "@mui/joy/styles";
import PropTypes from 'prop-types';

import { palette } from '../palette';
import { shadows } from '../shadows';
import { overrides } from '../overrides';
import { typography } from '../typography';
import { customShadows } from '../custom-shadows';

const materialTheme = extendMaterialTheme();

export default function DarkLightMode({ children }) {

  const memoizedValue = useMemo(
    () => ({
      palette: palette(),
      typography,
      shadows: shadows(),
      customShadows: customShadows(),
      shape: { borderRadius: 8 },
    }),
    []
  );

  const theme = createTheme(memoizedValue);

  theme.components = overrides(theme);

  return (
    <MaterialCssVarsProvider
      theme={{ [THEME_ID]: materialTheme }}
      // theme={theme}
    >
      <JoyCssVarsProvider>
        <CssBaseline enableColorScheme />
          {children}
      </JoyCssVarsProvider>
    </MaterialCssVarsProvider>
  );
}

DarkLightMode.propTypes = {
  children: PropTypes.node,
};