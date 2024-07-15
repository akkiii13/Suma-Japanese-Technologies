import React, { useState, useEffect } from "react";
import DarkMode from "@mui/icons-material/DarkMode";
import LightMode from "@mui/icons-material/LightMode";
import { IconButton } from "@mui/joy";
import { useColorScheme as useMaterialColorScheme } from "@mui/material/styles";
import { useColorScheme as useJoyColorScheme } from "@mui/joy/styles";

export const ToggleDarkLightMode = () => {
  const { mode, setMode } = useMaterialColorScheme();
  const { setMode: setJoyMode } = useJoyColorScheme();
  const [mounted, setMounted] = useState(false);

  useEffect(() => {
    setMounted(true);
  }, []);

  if (!mounted) {
    return null;
  }

  return (
    <IconButton
      onClick={() => {
        setMode(mode === "dark" ? "light" : "dark");
        setJoyMode(mode === "dark" ? "light" : "dark");
      }}
    >
      {mode === "dark" ? <LightMode /> : <DarkMode />}
    </IconButton>
  );
};
