import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';

import { useState, useEffect } from "react";

import { Navbar } from "../../components/navbar/Navbar"
import { CardItem } from '../../components/cardItem/CardItem';
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
            <div className='container'>
                <div className='row' >
                    <div className='col-md-12 text-center pt-10'>
                    <h1>Lista de Usuarios</h1>
                    </div>
                </div>
                <div className="row d-flex justify-content-center" >
                        {
                            usuarios.length === 0 ? 
                            (
                                <h1> Loading... </h1>
                            ) :  (
                                <>
                                    {
                                    usuarios.map( usuario => (
                                            <CardItem key={usuario.id} {...usuario} />
                                        ))
                                    }
                                </>
                            )}
                </div>
            </div>
        </>
    )
}