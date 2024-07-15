import './App.css';

import { useScrollToTop } from './hooks/use-scroll-to-top';

// import Router from 'src/routes/sections';
// import ThemeProvider from 'src/theme';
import ThemeProvider from './theme';
import Router from './routes/sections';


function App() {
  useScrollToTop();

  return (
    <ThemeProvider>
      <Router />
    </ThemeProvider>
  );
}

export default App;
