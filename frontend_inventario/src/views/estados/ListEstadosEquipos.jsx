import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { FaPlusCircle } from 'react-icons/fa';
import * as API from '../../service/estadosService';

import { Navbar } from '../../components/navbar/Navbar';
import { ItemList } from '../../components/listItem/ItemList';
import {Footer} from '../../components/footer/Footer';

import Swal from 'sweetalert2';
import 'sweetalert2/src/sweetalert2.scss';

export function ListEstadosEquipos(){

    const [estados, setEstados] = useState([]);

    const getEstados = async () => {
        /* modal  */
        Swal.fire({
            title: 'Cargando...',
            text: 'Espere un momento',
            showConfirmButton: false,
            timer: 1000          
        })
        Swal.showLoading();
        API.getAllEstados()
            .then(data => {setEstados(data)})
            .catch(error => console.log(error));
    }

    const handleDelete = (id) => {
        try {
            /* modal confirmation */
            Swal.fire({
                title: '¿Está seguro?',
                text: "¡No podrá revertir esto!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Si, eliminarlo!'
            }).then((result) => {
                if (result.value){
                    API.deleteEstado(id)
                        .then(data => {
                            if(!data){
                                Swal.fire({
                                    title:'Eliminado!',
                                    text:'La marca ha sido eliminada',
                                    icon:'success',
                                    timer:1300,
                                })
                                getEstados();
                            }else{
                                Swal.fire(
                                    'Error!',
                                    data.error,
                                    'error'
                                )
                                console.log(data)
                            }
                        })
                }
            })
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        getEstados();
    },[]);

    return(
        <>
            <Navbar />
            <div className="container position-relative">
                <div className='title my-4 text-center' >
                    <h1>Lista de Estados</h1>
                </div>
                <div className="container">
                    <div className="row">
                        <div className="col">
                            <table className="table">
                                <thead className='text-center'>
                                    <tr>
                                        <th scope="col">Id</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Estado</th>
                                        <th scope="col">Usuario</th>
                                        <th scope="col">Editar</th>
                                        <th scope="col">Eliminar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <>
                                        {
                                            estados.map(estado => (
                                                <ItemList key={estado.id} {...estado} handleDelete={handleDelete} />
                                            ))
                                        }
                                    </>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <Link to="/estados/crear" className="fs-1 text-black position-absolute bottom-0 end-0 my-5 mx-5"> <FaPlusCircle/> </Link>
        </>
        )
}