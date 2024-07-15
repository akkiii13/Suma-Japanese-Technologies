import "bootstrap/dist/css/bootstrap.min.css";
import "react-toastify/dist/ReactToastify.css";
import "./App.css";

import { ToastContainer } from "react-toastify";

import { useScrollToTop } from './hooks/use-scroll-to-top';
import ThemeProvider from './theme/ThemeProvider';
import Router from './routes/sections';

// ----------------------------------------------------------------------

function App() {
  useScrollToTop();

  return (
    <ThemeProvider>
      <ToastContainer
        position="bottom-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="colored"
      />
      <Router />
    </ThemeProvider>
  );
}

export default App;
