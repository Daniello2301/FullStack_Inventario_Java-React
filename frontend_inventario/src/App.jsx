import {Routes, Route } from 'react-router-dom';

import { ListInventarios } from './views/inventarios/ListInventarios';
import { CrearInventario } from './views/inventarios/CrearInventario';
import { ListMarcas } from './views/marcas/ListMarcas';
import { CrearMarca } from './views/marcas/CrearMarca';
import { ListEstadosEquipos } from './views/estados/ListEstadosEquipos';
import { CrearEstado } from './views/estados/CrearEstado';
import { ListUsers } from './views/usuarios/ListUsers';
import { CrearUsuario } from './views/usuarios/CrearUsuario';
import { ListTiposEquipos } from './views/tipos/ListTiposEquipos'; 
import { CrearTipo } from './views/tipos/CrearTipo';
import { HomePage} from './views/HomePage'

export function App() {

  return (
    <> 
      <Routes>
        <Route path="/" element={ <HomePage/> } />
        
        <Route path="/usuarios" element={ <ListUsers/> } />
        <Route path="/usuarios/crear" element={ <CrearUsuario/> } />
        <Route path="/tipos" element={ <ListTiposEquipos/> } />
        <Route path="/tipos/crear" element={ <CrearTipo/> } />
        <Route path="/estados" element={ <ListEstadosEquipos/> } />
        <Route path="/estados/crear" element={ <CrearTipo/> } />
        <Route path="/marcas" element={ <ListMarcas/> } />
        <Route path="/marcas/crear" element={ <CrearMarca/> } />
        <Route path="/inventarios" element={ <ListInventarios/> } />
        <Route path="/inventarios/crear" element={ <CrearInventario/> } />
      </Routes>
    </>
  )
}
