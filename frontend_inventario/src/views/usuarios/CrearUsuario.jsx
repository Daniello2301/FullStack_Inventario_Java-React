import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';

import { useState } from 'react';
import * as API from '../../service/usuariosService';

import { Navbar } from '../../components/navbar/Navbar';
import { Footer } from '../../components/footer/Footer';

import './CrearUsuario.css';

export function CrearUsuario() {

    const [usuario, setUsuario] = useState({
        nombre: '',
        email: '',
        contrasena: '',
        estado:'',
    });

    const { nombre, email, contrasena, estado } = usuario;
    const onInputChange = (e) => {
        setUsuario({
            ...usuario,
            [e.target.name]: e.target.value
        });
    }

    const submitCreateUser = async (e) => {
        e.preventDefault();
        e.target.reset();
        const newUsuario = await API.createUsuario(usuario);
        console.log(newUsuario);
        alert("Usuario creado correctamente!!!");
    }

    return (
        <>
            <div className="main-content" >
                <Navbar />
                <div className="form_content">
                    <div className="form_main">
                        <form onSubmit={submitCreateUser}>
                            <div className="form-group mb-3">
                                <label className="form-label">Nombre</label> 
                                <input type="text" class="form-control" name="nombre"   value={nombre} onChange={e => onInputChange(e)} placeholder="Enter name" required=""/>
                            </div>                           
                            <div className="form-group mb-3">
                                <label className="form-label">Email</label>
                                <input type="email" class="form-control" name="email" value={email} onChange={e => onInputChange(e)}  placeholder="Enter email" required=""/>
                            </div>
                            <div className="form-group mb-3">
                                <label className="form-label">Password</label>
                               <input type="password" class="form-control" name="contrasena" value={contrasena} onChange={e => onInputChange(e)}  placeholder="Enter contrasena" required=""/>
                            </div>
                            <div className="form-group mb-3">
                                <label for="estado" className="form-label">Estado</label>
                                <select id="estado" className="form-select" name="estado" value={estado} onChange={e => onInputChange(e)} required="" >
                                    <option >--Select--</option>
                                    <option value="Activo" >Activo</option>
                                    <option value="Inactivo" >Inactivo</option>
                                </select>
                            </div>
                            <button type="submit" className="btn btn-primary w-100">Send</button>
                        </form>
                    </div>
                </div>
                <Footer />
            </div>
        </>
    )
}