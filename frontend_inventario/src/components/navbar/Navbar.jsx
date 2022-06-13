import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';

import {Link} from 'react-router-dom';

export function Navbar(){
    return(

            <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-5">
                <div className="container-fluid">

                    <Link to="/" className="navbar-brand">Inicio</Link>

                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarScroll">
                    <ul className="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Inventario
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><Link to="/inventarios" className="dropdown-item">Listar Inventario</Link></li>                            
                                <li><hr class="dropdown-divider"/></li>
                                <li><Link to="/inventarios/crear" className="dropdown-item">Crear Inventario</Link></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Usuario
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><Link to="/usuarios" className="dropdown-item">Listar usuarios</Link></li>                            
                                <li><hr class="dropdown-divider"/></li>
                                <li><Link to="/usuarios/crear" className="dropdown-item">Crear Usuario</Link></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Marcas
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><Link to="/marcas" className="dropdown-item">Listar marcas</Link></li>                            
                                <li><hr class="dropdown-divider"/></li>
                                <li><Link to="/marcas/crear" className="dropdown-item">Crear marca</Link></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                               Tipos de Equipos
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><Link to="/tipos" className="dropdown-item">Listar Tipos de Equipo</Link></li>                            
                                <li><hr class="dropdown-divider"/></li>
                                <li><Link to="/tipos/crear" className="dropdown-item">Crear Tipo de Equipo</Link></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Estados de Equipo
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><Link to="/estados" className="dropdown-item">Listar Estados de Equipo</Link></li>                            
                                <li><hr class="dropdown-divider"/></li>
                                <li><Link to="/estados/crear" className="dropdown-item">Crear Estado de Equipo</Link></li>
                            </ul>
                        </li>
                        </ul>
                    </div>
                </div>
            </nav>
    )
}