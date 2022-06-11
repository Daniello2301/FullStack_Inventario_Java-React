import { useState, useEffect } from "react"

import * as API from '../../service/inventariosService'

import { Navbar } from "../../components/navbar/Navbar"
import { Footer } from "../../components/footer/Footer"
import { CardItem } from "../../components/cardItem/CardItem"

export function ListInventarios() {

    const [inventarios, setInventarios] = useState([]);

    useEffect(() => {
        API.getAllEquipos()
            .then(data => { setInventarios(data) })
            .catch(error => console.log(error));
    }, []);


    return (
        <>
            <Navbar />
            <div className='container my-3'>
                <div className='row my-3'  >
                    <div className='col-md-12 text-center pt-10'>
                    <h1>Lista de Inventario</h1>
                    </div>
                </div>
                <div className="row d-flex justify-content-center" >
                    <>
                        {
                            inventarios.length === 0 ?(
                                <div>Loading...</div>
                            ):(
                                inventarios.map(inventario => (
                                    <CardItem key={inventario.id} {...inventario} />
                                ))
                            )
                            
                        }
                    </>
                </div>
            </div>
            <Footer />
        </>
    )
}