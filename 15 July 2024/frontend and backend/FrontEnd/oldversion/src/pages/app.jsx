import { Helmet } from 'react-helmet-async';

// import { AppView } from 'src/sections/overview/view';
import { AppView } from '../sections/overview/view';

// ----------------------------------------------------------------------

export default function AppPage() {
  return (
    <>
      <Helmet>
        <title> Dashboard | Minimal UI </title>
      </Helmet>

      <AppView />
    </>
  );
}
