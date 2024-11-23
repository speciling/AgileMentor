import React from 'react';
// eslint-disable-next-line import/no-extraneous-dependencies
import { BrowserRouter as Router } from 'react-router-dom';
import AppRoutes from './routes';
import { ProjectProvider } from './provider/projectContext';

function App() {
  return (
    // eslint-disable-next-line react/jsx-no-undef
    <ProjectProvider>
      <Router>
        <div className="App">
          <AppRoutes />
        </div>
      </Router>
    </ProjectProvider>
  );
}

export default App;
