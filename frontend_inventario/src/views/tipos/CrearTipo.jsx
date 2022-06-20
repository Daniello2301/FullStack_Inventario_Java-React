import React from "react";
import { useState, useEffect} from "react";

import * as API from "../../service/TiposService";
import * as API_U  from "../../service/UsuariosService";
import { Navbar } from "../../components/navbar/Navbar";

import { Link } from "react-router-dom";
import { TiArrowBack } from "react-icons/ti";

import Swal from "sweetalert2";
import 'sweetalert2/src/sweetalert2.scss';

export function CrearTipo(){

    const [tipo, setTipo] = useState({
        nombre: "",
        estado: "",
        usuario: ""
    });

    const clearDataForm = () => {
        setTipo({
            nombre: "",
            estado: "",
            usuario: ""
        })
    }

    const { nombre, estado, usuario } = tipo;

    const [usuarios, setUsuarios] = useState([]);
    
    const getUsuarios = async () => {
        try {
            await API_U.getAllUsuarios()
                .then(data => {
                    setUsuarios(data)
                    console.log(data)
                })
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        getUsuarios();
    },[])

    const handleChange = e => {
        setTipo({
            ...tipo,
            [e.target.name]: e.target.value
        })
    }

    const data = {
        nombre: nombre,
        estado: estado,
        usuario:{
            id: usuario
        }
    }

    const handleSubmit = async e => {
        e.preventDefault();
        e.target.reset();
        clearDataForm();
        try {
            const response = await API.createTipo(data);
            Swal.fire({
                title: 'Tipo creado',
                text: 'El tipo se ha creado correctamente',
                icon: 'success',
                confirmButtonText: 'Ok'
            }).then(() => {
                window.location.href = "/tipos";
            })
            console.log(response);
        }
        catch (error) {
            console.log(error);
        }
    }

    return (
        <>
            <Navbar />
            <div className="container">
                <div className="row my-5 text-center">
                    <div className="col-md-12">
                        <h2>Crear Tipo</h2>
                    </div>
                </div>
                <div className="row m-5 ">
                    <div className="col w-50 d-flex justify-content-center align-items-center rounded-3 ">
                        <form className='p-5 bg-dark rounded-3 shadow-lg' onSubmit={handleSubmit}>
                            <div className="row">
                                <div className="col-md-6">
                                    <div className="form-group mb-3">
                                        <label className="form-label  text-white">Nombre</label> 
                                        <input type="text" class="form-control border-secondary" name="nombre"   value={nombre} onChange={e => handleChange(e)} placeholder="Enter name" required />
                                    </div>                           
                                </div>
                                <div className="col-md-6">
                                    <div className="form-group mb-3">
                                        <label for="estado" className="form-label  text-white">Estado</label>
                                        <select id="estado" className="form-select border-secondary" name="estado" value={estado} onChange={e => handleChange(e)} required>
                                            <option >--Select--</option>
                                            <option value="Activo" >Activo</option>
                                            <option value="Inactivo" >Inactivo</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-md-12">
                                    <div className="form-group mb-3">
                                        <label for="usuario" className="form-label  text-white">Usuario</label>
                                        <select id="usuario" className="form-select border-secondary" name="usuario" value={usuario} onChange={e => handleChange(e)} required>
                                            <option >--Select--</option>
                                            {
                                                usuarios.map(usuario => (
                                                    <option key={usuario.id} value={usuario.id}>{usuario.nombre}</option>
                                                ))
                                            }
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
            <Link to='/tipos' className="btn position-absolute bottom-0 end-0 fs-1 text-black mx-5 my-5" > <TiArrowBack /> </Link>
        </>
    )
}