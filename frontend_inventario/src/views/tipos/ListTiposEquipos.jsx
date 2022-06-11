import { useState, useEffect } from "react"

import { ItemList } from "../../components/listItem/ItemList";

import * as API from "../../service/TiposService"
import { Navbar } from "../../components/navbar/Navbar"
import { Footer } from "../../components/footer/Footer";

export function ListTiposEquipos(){

    const [tipos, setTipos] = useState([]);

    useEffect(() => {
        API.getAllTipos()
            .then(data => {setTipos(data)})
            .catch(error => console.log(error));
        },[]);

    return(
        <>
            <Navbar />
            <div className='title'>
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
                                            <ItemList key={tipo.id} {...tipo} />
                                        ))
                                    }
                                </>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <Footer/>
        </>
        )
}