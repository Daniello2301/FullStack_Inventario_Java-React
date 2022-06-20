import {FaEdit} from 'react-icons/fa';
import {FaTrash} from 'react-icons/fa';

import {Link } from 'react-router-dom';

export function ItemList({ handleDelete, ...props}){
    return(
        <>
            <tr className="text-center" >
                <th scope="row"> {props.id} </th>
                <td> {props.nombre} </td>
                <td> {props.estado} </td>
                <td> {props.usuario?.nombre} </td>
                <td> <Link to={`/`}> <button className="btn btn-success"> <FaEdit/> </button> </Link> </td>
                <td> <button className="btn btn-danger" onClick={() => handleDelete(props.id)} > <FaTrash/> </button> </td>
            </tr>
        </>
    )
}