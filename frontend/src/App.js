import './App.css';
// eslint-disable-next-line import/no-unresolved
import Header from '@components/features/Header';
// eslint-disable-next-line import/no-unresolved
import DashboardPage from '@pages/Dashboard';
// eslint-disable-next-line import/no-unresolved

function App() {
  return (
    <div className="App">
      <Header />
      <DashboardPage />
    </div>
  );
}

export default App;
