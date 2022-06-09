import {Routes, Route } from 'react-router-dom';

import { ListInventarios } from './views/inventarios/ListInventarios';
import { ListMarcas } from './views/marcas/ListMarcas';
import { ListEstadosEquipos } from './views/estados/ListEstadosEquipos';
import { ListUsers } from './views/usuarios/ListUsers'
import { ListTiposEquipos } from './views/tipos/ListTiposEquipos';  
import { HomePage} from './views/HomePage'

export function App() {

  return (
    <> 
      <Routes>
        <Route path="/" element={ <HomePage/> } />
        <Route path="/usuarios" element={ <ListUsers/> } />
        <Route path="/tipos" element={ <ListTiposEquipos/> } />
        <Route path="/estados" element={ <ListEstadosEquipos/> } />
        <Route path="/marcas" element={ <ListMarcas/> } />
        <Route path="/inventarios" element={ <ListInventarios/> } />
      </Routes>
    </>
  )
}
