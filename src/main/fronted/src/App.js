import MyRoutes from './MyRoutes';
import './App.css';
import AuthProvider from "./AuthContext";

function App() {
  return (
      <div className="App">
          <AuthProvider>
          <MyRoutes />
          </AuthProvider>
      </div>
  );
}

export default App;
