import {Link} from 'react-router-dom';

export function Navbar(){
    return(
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <div className="container-fluid">

                <Link to="/" className="navbar-brand">Inicio</Link>

                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarScroll">
                <ul className="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll">
                    <li className="nav-item">
                        <Link to="/inventarios" className="nav-link">Inventarios</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/usuarios" className="nav-link">Usuarios</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/estados" className="nav-link">Marcas</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/tipos" className="nav-link">Tipos de Equipos</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/estados" className="nav-link">Estados de Equipos</Link>
                    </li>
                    </ul>
                </div>
            </div>
        </nav>
    )
}