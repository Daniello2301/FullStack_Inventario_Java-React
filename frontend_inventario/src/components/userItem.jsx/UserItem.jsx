import * as API from '../../service/usuariosService';

import { useEffect, useState } from 'react';

import { Link } from "react-router-dom"
import { FaEdit, FaTrash } from "react-icons/fa"

export function UserItem({handleDelete, ...props}){

    return(
        <>
            <tr className="text-center" >
                <th scope="row"> {props.id} </th>
                <td> {props.nombre} </td>
                <td> {props.email} </td>
                <td> {props.estado} </td>
                <td> <Link to={`/`}> <button className="btn btn-success"> <FaEdit/> </button> </Link> </td>
                <td> <button className="btn btn-danger" onClick={() => handleDelete(props.id)}> <FaTrash/> </button> </td>
            </tr>
        </>
    )
} 