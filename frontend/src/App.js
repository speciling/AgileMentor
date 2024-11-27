import React from 'react';
// eslint-disable-next-line import/no-extraneous-dependencies
import { BrowserRouter } from 'react-router-dom';
import { DndProvider } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';
import AppRoutes from './routes';
import { ProjectProvider } from './provider/projectContext';

function App() {
  return (
    <ProjectProvider>
      <DndProvider backend={HTML5Backend}>
        <BrowserRouter>
          <div className="App">
            <AppRoutes />
          </div>
        </BrowserRouter>
      </DndProvider>
    </ProjectProvider>
  );
}

export default App;
