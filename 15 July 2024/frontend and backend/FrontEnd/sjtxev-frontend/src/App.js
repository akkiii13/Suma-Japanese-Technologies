import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import "./Tailwind.css";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Fragment } from "react";
import { AllRouting } from "./routes/AllRouting";
import DarkLightMode from "./theme/DarkLightMode/DarkLightMode";

function App() {
  return (
    <Fragment>
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
      <DarkLightMode>
        <AllRouting />
      </DarkLightMode>
    </Fragment>
  );
}

export default App;
