import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';

import {useState, useEffect} from 'react';
import * as API from '../../service/marcasService';

import {Navbar} from '../../components/navbar/Navbar';
import { ItemList } from '../../components/listItem/ItemList';

export function ListMarcas() {

    const [marcas, setMarcas] = useState([]);

    useEffect(() =>{
        API.getAllMarcas()
            .then(data => {setMarcas(data)})
            .catch(error => console.log(error));
        },[]);

    return (
        <>
            <Navbar />
            <div className='title text-center' >
                <h1>Marcas</h1>
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
                                        marcas.map(marca => (
                                            <ItemList key={marca.id} {...marca} />
                                        ))
                                    }
                                </>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </>
    )
}