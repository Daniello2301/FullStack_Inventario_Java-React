import {useState, useEffect} from 'react';
import * as API from '../../service/marcasService';

import {Navbar} from '../../components/navbar/Navbar';
import { ItemList } from '../../components/listItem/ItemList';
import { Link }  from 'react-router-dom';
import {FaPlusCircle } from 'react-icons/fa'

import Swal from 'sweetalert2';
import 'sweetalert2/src/sweetalert2.scss';

export function ListMarcas() {

    const [marcas, setMarcas] = useState([]);

    const getAllMarcas = async () => {
        try {
            Swal.fire({
                title: 'Cargando...',
                text: 'Espere un momento',
                showConfirmButton: false,
                timer: 1000,
            })
            Swal.showLoading();
            API.getAllMarcas()
            .then(data => {setMarcas(data)
            console.log(data)
            })
        } catch (error) {
            console.log(error);
        }

    }

    useEffect(() =>{
        getAllMarcas();
    },[]);

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
                    API.deleteMarca(id)
                        .then(data => {
                            if(!data){
                                Swal.fire({
                                    title:'Eliminado!',
                                    text:'La marca ha sido eliminada',
                                    icon:'success',
                                    timer:300,
                                })
                                getAllMarcas();
                            }else{
                                Swal.fire(
                                    'Error!',
                                    'No se pudo eliminar la marca',
                                    'error',
                                    300,
                                )}
                            console.log(data)
                            }
                        )
                        .catch(error => {
                            console.log(error);
                        });
                    Swal.fire({
                        title:'Eliminado!',
                        text: 'La marca ha sido eliminada.',
                    })
                }
            })
        } catch (error) {
            console.log(error);
        }
    }
    return (
        <>
            <Navbar />
            <div className="container position-relative">
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
                                                <ItemList key={marca.id} {...marca} handleDelete={handleDelete} />
                                            ))
                                        }
                                    </>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <Link to='/marcas/crear' className="btn position-absolute bottom-0 end-0 fs-1 my-5 mx-5" > <FaPlusCircle /> </Link>

        </>
    )
}