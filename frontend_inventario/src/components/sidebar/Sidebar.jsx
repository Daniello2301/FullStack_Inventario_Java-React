import { Link } from 'react-router-dom';

import './Sidebar.css';

export function Sidebar() {
    return (
        <div className="sidebar">
            <ul>
               <Link to={`/inventarios`} className="link" > Inventarios </Link>
               <Link to={`/usuarios`} className="link" > Usuarios </Link>
               <Link to={`/marcas`} className="link" > Marcas </Link>
               <Link to={`/tipos`} className="link" > Tipos de Equipos</Link>
               <Link to={`/estados`} className="link" > Estados de Equipos </Link>
            </ul>
        </div>
    )
}