import {Routes, Route } from 'react-router-dom';

import { ListUsers } from './views/users/ListUsers'
import { ListTiposEquipos } from './views/tipos/ListTiposEquipos';  
import { HomePage} from './views/HomePage'

export function App() {

  return (
    <> 
      <Routes>
        <Route path="/" element={ <HomePage/> } />
        <Route path="/users" element={ <ListUsers/> } />
        <Route path="/tipos" element={ <ListTiposEquipos/> } />
      </Routes>
    </>
  )
}
