import { useState, useEffect } from "react"

import { ItemList } from "../../components/listItem/ItemList";
import { Link } from "react-router-dom";
import { FaPlusCircle } from "react-icons/fa";

import * as API from "../../service/TiposService"
import { Navbar } from "../../components/navbar/Navbar"
import { Footer } from "../../components/footer/Footer";


import Swal from "sweetalert2" 
import 'sweetalert2/src/sweetalert2.scss'
export function ListTiposEquipos(){

    const [tipos, setTipos] = useState([]);

    const getTipos = async () => {
        try {
            /* Swal modal */
            Swal.fire({
                title: 'Cargando...',
                text: 'Espere un momento',
                showConfirmButton: false,
                timer: 1000
            })
            Swal.showLoading()
            await API.getAllTipos()
                .then(data => {
                    setTipos(data)
                    console.log(data)
                })
        } catch (error) {
            console.log(error);
        }
    }

    const handleDelete = async (id) => {
        try {
            /* modal confirmation */
            Swal.fire({
                title: '¿Está seguro?',
                text: "¡No podrá revertir esto!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '¡Sí, eliminar!'
            }).then((result) => {
                if (result.value) {
                    API.deleteTipo(id)
                        .then(data => {
                            if(!data){
                                Swal.fire({
                                    title:'Eliminado!',
                                    text:'La marca ha sido eliminada',
                                    icon:'success',
                                    timer:1300,
                                })
                                getTipos();
                            }else{
                                Swal.fire(
                                    'Error!',
                                    data.error,
                                    'error'
                                )
                                console.log(data)
                            }
                        })
                        .catch(error => { 
                            message = error.message;
                            Swal.fire({
                                title: 'Error',
                                text: message,
                                icon: 'error',
                                confirmButtonText: 'Ok'
                            })
                        })
                }
            })
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        getTipos();
    },[]);

    return(
        <>
            <Navbar />
            <div className="container position-realtive">
                <div className='title text-center my-4'>
                    <h1>Lista de Tipos</h1>
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
                                            tipos.map(tipo => (
                                                <ItemList key={tipo.id} {...tipo}  handleDelete={handleDelete} />
                                            ))
                                        }
                                    </>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <Link to="/tipos/crear" className="fs-1 text-black position-absolute bottom-0 end-0 my-5 mx-5"> <FaPlusCircle /></Link> 
            </div>
        </>
        )
}