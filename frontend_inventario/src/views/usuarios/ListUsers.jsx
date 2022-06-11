import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';

import { useState, useEffect } from "react";

import { Navbar } from "../../components/navbar/Navbar"
import { UserItem } from '../../components/userItem.jsx/UserItem';
import { Footer } from "../../components/footer/Footer"
import * as API from '../../service/usuariosService';

export function ListUsers(){

    const [usuarios, setUsuarios] = useState([]);

    useEffect(() => {
            API.getAllUsuarios()
                .then(setUsuarios)
                .catch(console.log);
        },[]);

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
                                            <UserItem key={usuario.id} {...usuario} />
                                        ))
                                    }
                                </>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <Footer />
        </>
    )
}