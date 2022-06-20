import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';

import { FaEdit, FaTrash} from 'react-icons/fa';

export function CardItem({ descripcion, handleDelete, handleEdit, imagen, tipoEquipo, usuario, marca, id }) {

    return(
        <>
            <div className="col co-sm-12 col-md-4 d-flex justify-content-center ">
                <div className="card m-2 bg-dark text-white shadow-lg" style={{width: 18 + "rem"}}>
                    <img src={imagen ? imagen : "No content" } className="card-img-top mt-3" style={{height: 9 +'rem'}} alt="..." />
                    <div className="card-body">
                        <h5 className="card-title text-center"> {tipoEquipo?.nombre} </h5>
                        <p className="card-text text-center"> {descripcion} </p>
                    </div>
                    <ul className="list-group list-group-flush text-center bg-dark bg-opacity-50">
                        <li className="list-group-item bg-transparent text-white" > {usuario?.nombre} </li>
                        <li className="list-group-item bg-transparent text-white"> {marca?.nombre} </li>
                    </ul>
                    <div className="card-body d-flex justify-content-center gap-3">
                        <Link to={``} > <button className="btn btn-success"> <FaEdit/> </button> </Link>
                        <button className="btn btn-danger" onClick={() => handleDelete(id)}> <FaTrash/> </button>

                    </div>
                </div> 
            </div>
        </>
        )
}