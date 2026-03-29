import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import NuevoEnvio from './pages/NuevoEnvio';
import DetalleEnvio from './pages/DetalleEnvio';
import EditarEnvio from './pages/EditarEnvio';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/"            element={<Home />} />
        <Route path="/nuevo"       element={<NuevoEnvio />} />
        <Route path="/detalle/:id" element={<DetalleEnvio />} />
        <Route path="/editar/:id"  element={<EditarEnvio />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
