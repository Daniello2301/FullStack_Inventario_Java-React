import { useState, useEffect } from 'react';

import * as API from '../../service/estadosService';

import { Navbar } from '../../components/navbar/Navbar';
import { ItemList } from '../../components/listItem/ItemList';
import {Footer} from '../../components/footer/Footer';

export function ListEstadosEquipos(){

    const [estados, setEstados] = useState([]);

    useEffect(() => {
        API.getAllEstados()
            .then(data => {setEstados(data)})
            .catch(error => console.log(error));
        },[]);

    return(
        <>
            <Navbar />
            <div className='title'>
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
                                            <ItemList key={estado.id} {...estado} />
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