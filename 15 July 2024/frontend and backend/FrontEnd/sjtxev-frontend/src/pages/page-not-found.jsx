import { Helmet } from "react-helmet-async";

import { NotFoundView } from "../sections/error";

const NotFoundPage = () => {
  return (
    <>
      <Helmet>
        <title> 404 Page Not Found </title>
      </Helmet>

      <NotFoundView />
    </>
  );
};

export default NotFoundPage;