import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';

import { useState } from 'react';
import * as API from '../../service/usuariosService';
import { Link} from 'react-router-dom';
import { TiArrowBack } from 'react-icons/ti';
import { Navbar } from '../../components/navbar/Navbar';
import { Footer } from '../../components/footer/Footer';

import Swal from 'sweetalert2';
import 'sweetalert2/src/sweetalert2.scss';

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
        Swal.fire({
            title: 'Usaurio creado',
            text: 'El usuario se ha creado correctamente',
            icon: 'success',
            confirmButtonText: 'Ok'
        }).then(() => {
            window.location.href = "/usuarios";
        })
        console.log(newUsuario);
    }

    return (
        <>
            <Navbar />
            <div className="container" >
                <div className="row my-2 text-center">
                    <div className="col-md-12 ">
                        <h2> Crear Inventario </h2>
                    </div>
                </div>
                <div className="row m-5 ">
                    <div className="col w-50 d-flex justify-content-center align-items-center rounded-3 ">
                        <form className='p-5 bg-dark rounded-3 shadow-lg'  onSubmit={submitCreateUser}>
                            <div className="row">
                                <div className="col-md-6">
                                    <div className="form-group mb-3">
                                        <label className="form-label  text-white">Nombre</label> 
                                        <input type="text" class="form-control border-secondary" name="nombre"   value={nombre} onChange={e => onInputChange(e)} placeholder="Enter name" required />
                                    </div>                           
                                </div>
                                <div className="col-md-6">
                                    <div className="form-group mb-3">
                                        <label className="form-label  text-white">Email</label>
                                        <input type="email" class="form-control border-secondary" name="email" value={email} onChange={e => onInputChange(e)}  placeholder="Enter email" required/>
                                    </div>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-6">
                                    <div className="form-group mb-3">
                                        <label className="form-label  text-white">Password</label>
                                    <input type="password" class="form-control border-secondary" name="contrasena" value={contrasena} onChange={e => onInputChange(e)}  placeholder="Enter contrasena" required />
                                    </div>
                                </div>
                                <div className="col-md-6">
                                    <div className="form-group mb-3">
                                        <label for="estado" className="form-label  text-white">Estado</label>
                                        <select id="estado" className="form-select border-secondary" name="estado" value={estado} onChange={e => onInputChange(e)} required>
                                            <option >--Select--</option>
                                            <option value="Activo" >Activo</option>
                                            <option value="Inactivo" >Inactivo</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12">
                                    <button type="submit" className="btn btn-primary w-100">Send</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <Footer />
            <Link to='/usuarios' className="btn position-absolute top-0 end-0 fs-1 text-black mx-5 my-5" > <TiArrowBack /> </Link>
            
        </>
    )
}