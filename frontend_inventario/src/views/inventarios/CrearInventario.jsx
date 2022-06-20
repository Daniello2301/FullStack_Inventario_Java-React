import React from 'react';
import { useState, useEffect } from 'react';
import { Link} from 'react-router-dom';
import { TiArrowBack } from 'react-icons/ti';
import * as API from '../../service/inventariosService';
import * as APIU from '../../service/usuariosService';
import * as APIE from '../../service/estadosService';
import * as APIT from '../../service/TiposService';
import * as APIM from '../../service/marcasService'
import Swal from 'sweetalert2';
import 'sweetalert2/src/sweetalert2.scss';
import { Navbar } from "../../components/navbar/Navbar";

export function CrearInventario(){


    const [usuarios, setUsuarios] = useState([]);
    const [estados, setEstados] = useState([]);
    const [tipos, setTipos] = useState([]);
    const [marcas, setMarcas] = useState([]);

    const getUsuarios = () => {
        try{
            APIU.getAllUsuarios()
            .then(data => { setUsuarios(data) 
                console.log(data)})
            .catch(error => console.log(error));
        }catch(error){
            console.log(error);
        }
    }

    const getEstados = () => {
        try{
            APIE.getAllEstados()
            .then(data => { setEstados(data)
                console.log(data)})
            .catch(error => console.log(error));
        }catch(error){
            console.log(error);
        }
    }

    const getTipos = () => {
        try{
            APIT.getAllTipos()
            .then(data => { setTipos(data)
                console.log(data)})
            .catch(error => console.log(error));
        }catch(error){
            console.log(error);
        }
    }

    const getMarcas = () => {
        try{
            APIM.getAllMarcas()
            .then(data => { setMarcas(data)
                console.log(data)})
            .catch(error => console.log(error));
        }catch(error){
            console.log(error);
        }
    }

    useEffect(() => {
        getUsuarios();
        getEstados();
        getTipos();
        getMarcas();
    },[]);

    const [inventario, setInventario] = useState({
        serial: '', modelo: '', descrpcion: '', imagen:'', precio:'', estado: '', fechaCompra:'', usuario:'',  marca: '', tipoEquipo: '', estadoEquipo: ''
    })

    const { serial, modelo, descripcion, imagen, precio, estado, fechaCompra, usuario, marca, tipoEquipo, estadoEquipo } = inventario;

    const handleChange = (e) => {
        setInventario({
            ...inventario,
            [e.target.name]: e.target.value
        })
    }

    const clearDataForm = () => {
        setInventario({
            serial: '', modelo: '', descripcion: '', imagen:'', precio:'', estado: '', fechaCompra:'', usuario:'',  marca: '', tipoEquipo: '', estadoEquipo: ''
        })
    }

    const data = {
        serial, modelo, descripcion, imagen, precio, estado, fechaCompra, 
        usuario: {
            id: usuario
        }, 
        marca:{
            id: marca
        }, 
        tipoEquipo:{
            id: tipoEquipo
        }, 
        estadoEquipo:{
            id: estadoEquipo
        }
    }
    const handleSubmit = async(e) => {
        e.preventDefault();
        console.log(data);
        e.target.reset();
        clearDataForm();
        try {
            Swal.fire({
                title: 'Equipo creado',
                text: 'El equipo se ha creado correctamente',
                icon: 'success',
                confirmButtonText: 'Ok'
            })
            const response = await API.createEquipo(data);
            console.log(response);
        } catch (error) {
            /* swal alert if errorr */
            Swal.fire({
                title: 'Error',
                text: error.message ,
                icon: 'error',
                confirmButtonText: 'Ok'
            })
            console.log(error);
        }
    }

    return (
        <>
            <Navbar />
            <div className="container">
                <div className="row my-3 text-center">
                    <div className="col">
                        <h1>Crear Inventario</h1>
                    </div>
                </div>
                <div className={'row p-3 mt-2 bg-dark bg-opacity-25 rounded-3 shadow'}>
                    <div className={'col'}>
                        <form onSubmit={handleSubmit} >
                            <div className="row my-2">
                                <div className="col col-sm-12 col-md-4">
                                    <div className={'form-group'}>
                                        <label htmlFor={'serial'} className="fw-bold" >Serial</label>
                                        <input type="text" className="form-control border-secondary" id="serial" name="serial" value={serial} onChange={e => handleChange(e)} required/>
                                    </div>
                                </div>
                                <div className="col col-sm-12 col-md-4">
                                    <div className={'form-group'}>
                                        <label htmlFor={'modelo'} className="fw-bold" >Modelo</label>
                                        <input type="text" className={'form-control border-secondary'} id={'modelo'} name="modelo" value={modelo} onChange={handleChange} required />
                                    </div>
                                </div>
                                <div className="col col-sm-12 col-md-4">
                                    <div className={'form-group'}>
                                        <label htmlFor={'descripcion'} className="fw-bold" >Descripcion</label>
                                        <input type="text" className={'form-control border-secondary'} id={'descripcion'} name="descripcion" value={descripcion} onChange={handleChange} required />
                                    </div>
                                </div>
                            </div>
                            <div className="row my-2">
                                <div className="col col-sm-12 col-md-8">
                                    <div className={'form-group'}>
                                        <label htmlFor={'imagen'} className="fw-bold" >Imagen</label>
                                        <input type="text" className={'form-control border-secondary'} id={'imagen'} name={'imagen'} value={imagen} onChange={handleChange} required />
                                    </div>
                                </div>
                                <div className="col col-sm-12 col-md-4">
                                    <div className={'form-group'}>
                                        <label htmlFor={'precio'} className="fw-bold" >Precio</label>
                                        <input type="number" className={'form-control border-secondary'} id={'precio'} name={'precio'} value={precio} onChange={handleChange} required />
                                    </div>
                                </div>
                            </div>
                            <div className="row my-2" >
                                <div className="col col-sm-12 col-md-4">
                                    <div className={'form-group'}>
                                        <label htmlFor={'estado'} className="fw-bold" >Estado</label>
                                        <select className={'form-control border-secondary'} id={'estado'} name={'estado'} value={estado} onChange={handleChange} required>
                                            <option defaultValue={''} >Seleccione un estado</option>
                                            <option value={'activo'}>Activo</option>
                                            <option value={'inactivo'}>Inactivo</option>
                                        </select>
                                    </div>
                                </div>
                                <div className="col col-sm-12 col-md-4">
                                    <div className={'form-group'}  >
                                        <label className="fw-bold" htmlFor={'fechaCompra border-secondary'}>Fecha de Compra</label>
                                        <input type="date" className={'form-control border-secondary'} id={'fechaCompra'} name={'fechaCompra'} value={fechaCompra} onChange={handleChange} required />
                                    </div>
                                </div>
                                <div className="col col-sm-12 col-md-4">
                                    <div className={'form-group'}>
                                        <label className="fw-bold" htmlFor={'usuario'}>Usuario</label>
                                        <select className={'form-control border-secondary'} id={'usuario'} name={'usuario'} value={usuario} onChange={handleChange} required>
                                            <option defaultValue={''} >Seleccione un usuario</option>
                                            {
                                                usuarios.map(usuario => (
                                                    <option key={usuario.id} value={usuario.id}>{usuario.nombre}</option>
                                                ))
                                            }
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div className="row my-2">
                                <div className="col col-sm-12 col-md-4">
                                    <div className={'form-group'}>
                                        <label  htmlFor={'marca'} className="fw-bold">Marca</label>
                                        <select className={'form-control border-secondary'} id={'marca'} name={'marca'} value={marca} onChange={handleChange} required>
                                            <option defaultValue={''} >Seleccione una marca</option>
                                            {
                                                marcas.map(marca => (
                                                    <option key={marca.id} value={marca.id}>{marca.nombre}</option>
                                                ))
                                            }
                                        </select>
                                    </div>                                    
                                </div>
                                <div className="col col-sm-12 col-md-4">
                                    <div className={'form-group'}>
                                        <label className="fw-bold" htmlFor={'tipoEquipo'}>Tipo de Equipo</label>
                                        <select className={'form-control border-secondary'} id={'tipoEquipo'} name={'tipoEquipo'} value={tipoEquipo} onChange={handleChange} required>
                                            <option defaultValue={''} >Seleccione un tipo de equipo</option>
                                            {
                                                tipos.map(tipo => (
                                                    <option key={tipo.id} value={tipo.id}>{tipo.nombre}</option>
                                                ))
                                            }
                                        </select>
                                    </div>
                                </div>
                                <div className="col col-sm-12 col-md-4">
                                    <div className={'form-group'}>
                                        <label className="fw-bold" htmlFor={'estadoEquipo'}>Estado del Equipo</label>
                                        <select className={'form-control border-secondary'} id={'estadoEquipo'} name={'estadoEquipo'} value={estadoEquipo} onChange={handleChange} required>
                                            <option defaultValue={''} >Seleccione un estado del equipo</option>
                                            {
                                                estados.map(estado => (
                                                    <option key={estado.id} value={estado.id}>{estado.nombre}</option>
                                                ))
                                            }
                                        </select>

                                    </div>
                                </div>
                            </div>
                            <div className="row my-3">
                                <div className="col col-sm-12 col-md-6 text-center">
                                    <button type="submit" className={'btn btn-primary w-50'}>Crear</button>
                                </div>
                                <div className="col col-sm-12 col-md-6 text-center">
                                    <Link to={'/inventarios'} className={'btn btn-secondary w-50'}>Cancelar</Link>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <Link to='/inventarios' className="btn position-absolute bottom-0 end-0 fs-1 text-black mx-5 my-5" > <TiArrowBack /> </Link>
            </div>
        </>
    )
}