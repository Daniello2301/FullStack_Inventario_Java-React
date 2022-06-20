import React from "react";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { FaEdit, FaTrash } from "react-icons/fa";
import { FaPlusCircle } from "react-icons/fa";
import { Navbar } from "../../components/navbar/Navbar"
import { UserItem } from '../../components/userItem.jsx/UserItem';
import * as API from '../../service/usuariosService';
import Swal from "sweetalert2";
import 'sweetalert2/src/sweetalert2.scss';

export function ListUsers(){

    const [usuarios, setUsuarios] = useState([]);

    const getAllUsuarios = async () => {
        try {
            Swal.fire({
                title: 'Cargando...',
                text: 'Espere un momento',
                showConfirmButton: false,
                timer: 1000,
            })
            Swal.showLoading();
            API.getAllUsuarios()
            .then(data => {setUsuarios(data)
            console.log(data)
            })
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        getAllUsuarios();
    }, []);

    const handleDelete = (id) => {
        try {
            Swal.fire({
                title: '¿Estas seguro?',
                text: "No podras revertir esto!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Si, eliminar!'
            }).then((result) => {
                if (result.value) {                  
                    API.deleteByIdUsuario(id)
                        .then(data => {
                            if(!data){
                                Swal.fire({
                                    title:'Eliminado!',
                                    text:'El usuario ha sido eliminado',
                                    icon:'success',
                                    timer:300,
                                })
                                getAllUsuarios();
                            }else{
                                Swal.fire(
                                    'Error!',
                                    data.error,
                                    'error'
                                )  
                            }
                            console.log(data)
                        })
                        .catch(error => {
                            console.log(error);
                        });
                }
            })
        } catch (error) {
            console.log(error);
        }
    }


    return(
        <>
            <Navbar />
            <div className="container position-relative">
                <div className='title text-center m-2'>
                    <h1>Usuarios</h1>
                </div>
                <div className="row">
                    <div className="col">
                        <table className="table p-5">
                            <thead className='text-center'>
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Estado</th>
                                    <th scope="col">Editar</th>
                                    <th scope="col">Eliminar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <>
                                    {
                                        usuarios.map(usuario => (
                                            <UserItem key={usuario.id} {...usuario} handleDelete={handleDelete} />
                                        ))
                                    }
                                </>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <Link to='/usuarios/crear' className="btn position-absolute bottom-0 end-0 fs-1 my-5 mx-5" > <FaPlusCircle /> </Link>
        </>
    )
}