import { useState, useEffect } from "react"
import  { Link } from "react-router-dom"
import * as API from '../../service/inventariosService'
import { FaPlusCircle } from "react-icons/fa"
import { Navbar } from "../../components/navbar/Navbar"
import { CardItem } from "../../components/cardItem/CardItem"

import Swal from "sweetalert2"
export function ListInventarios() {

    const [inventarios, setInventarios] = useState([]);

    const getAllEquipos = async () => {
        try {
            
            Swal.fire({
                title: 'Cargando...',
                text: 'Espere un momento',
                showConfirmButton: false,
                timer: 1000,
            })
            Swal.showLoading();
            API.getAllEquipos()
            .then(data => {setInventarios(data)
            console.log(data)
            })
        } catch (error) {
            console.log(error);
        }
    }
    useEffect(() => {
        getAllEquipos();
    }, []);

    const handleDelete = (id) => {
        try {
            API.deleteEquipo(id)
                .then(data => {
                    console.log(data)
                    getAllEquipos();
                    }
                )
                .catch(error => console.log(error));
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <>
            <Navbar />
            <div className='container my-3'>
                <div className='row my-3'  >
                    <div className='col-md-12 text-center pt-10'>
                    <h1>Lista de Inventario</h1>
                    </div>
                </div>
                <div className="row" >
                    
                        <>
                            {
                                inventarios.length==0?(
                                    <div className="col-md-12 text-center">
                                        <h3>No hay inventarios</h3>
                                    </div>
                                ):(
                                    inventarios.map(inventario => (
                                        <CardItem key={inventario.id} {...inventario} handleDelete={handleDelete} />
                                    ))
                                )
                                
                            }
                        </>
                </div>
            </div>
            <Link to='/inventarios/crear' className="btn position-fixed bottom-0 end-0 fs-1 text-black mx-5 my-5" > <FaPlusCircle /> </Link>
        </>
    )
}