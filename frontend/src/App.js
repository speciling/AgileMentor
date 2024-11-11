import './App.css';
// eslint-disable-next-line import/no-unresolved
import Header from '@components/features/Header';
// eslint-disable-next-line import/no-unresolved
import SideBar from '@components/features/SideBar';

function App() {
  return (
    <div className="App">
      <Header />
      <SideBar />
    </div>
  );
}

export default App;
