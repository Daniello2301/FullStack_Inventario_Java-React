import { Link } from "react-router-dom"
import { FaEdit, FaTrash } from "react-icons/fa"

export function UserItem(props){
    return(
        <>
            <tr className="text-center" >
                <th scope="row"> {props.id} </th>
                <td> {props.nombre} </td>
                <td> {props.email} </td>
                <td> {props.estado} </td>
                <td> <Link to={`/`}> <button className="btn btn-success"> <FaEdit/> </button> </Link> </td>
                <td> <button className="btn btn-danger"> <FaTrash/> </button> </td>
            </tr>
        </>
    )
} 