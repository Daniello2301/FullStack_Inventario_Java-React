import { Link } from 'react-router-dom';

export function CardItem( entidad ){
    return(
        <>
            <div className="card bg-secondary  bg-opacity-25 m-5" style={{width: 18 +"rem"}}>
                <div className="card-body">
                    <h5 className="card-title text-center"> {entidad.nombre} </h5>
                    <p className="card-text text-center"> {entidad.email} </p>
                    <p className="card-text text-center"> {entidad.estado} </p>
                    <Link to={`/equipos/${entidad.id}`} className="btn btn-primary w-100">Ver</Link>
                </div>
            </div> 
        </>
        )
}