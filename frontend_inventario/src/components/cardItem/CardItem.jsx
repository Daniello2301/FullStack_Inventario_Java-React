import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import { Link } from 'react-router-dom';

import { FaEdit, FaTrash} from 'react-icons/fa';

export function CardItem( entidad ){
    return(
        <>
            <div className="card border-secondary mx-5" style={{width: 18 + "rem"}}>
                <img src={entidad.imagen} className="card-img-top" style={{height:7 +'rem'}} alt="..." />
                <div className="card-body">
                    <h5 className="card-title text-center"> {entidad.tipoEquipo?.nombre} </h5>
                    <p className="card-text text-center"> {entidad.descripcion} </p>
                </div>
                <ul className="list-group list-group-flush text-center   ">
                    <li className="list-group-item"> {entidad.estado} </li>
                    <li className="list-group-item"> {entidad.usuario?.nombre} </li>
                    <li className="list-group-item"> {entidad.marca?.nombre} </li>
                    <li className="list-group-item"> {entidad.estadoEquipo?.nombre} </li>
                </ul>
                <div className="card-body d-flex justify-content-center gap-3">
                    <Link to={``} > <button className="btn btn-success"> <FaEdit/> </button> </Link>
                    <Link to={``}> <button className="btn btn-danger"> <FaTrash/> </button> </Link>
                </div>
                </div> 
        </>
        )
}