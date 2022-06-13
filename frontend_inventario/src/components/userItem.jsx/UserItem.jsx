import * as API from '../../service/usuariosService';

import { useEffect, useState } from 'react';

import { Link } from "react-router-dom"
import { FaEdit, FaTrash } from "react-icons/fa"

export function UserItem(props){

    const [ usuarios, setUsuarios ] = useState([]);

    useEffect(() => {
        getUsers();
    }, []);

    const getUsers = async () => {
        try {
            const response = await API.getAllUsuarios();
            setUsuarios(response.data);
        }
        catch (error) {
            console.log(error);
        }
    }

    const deleteByIdUsuario = async (id) => {
        try {            
            await API.deleteByIdUsuario(id);
            getUsers();
        }
        catch (error) {
            console.log(error);
        }


       
    }

    return(
        <>
            <tr className="text-center" >
                <th scope="row"> {props.id} </th>
                <td> {props.nombre} </td>
                <td> {props.email} </td>
                <td> {props.estado} </td>
                <td> <Link to={`/`}> <button className="btn btn-success"> <FaEdit/> </button> </Link> </td>
                <td> <button className="btn btn-danger" onClick={ () => deleteByIdUsuario(props.id) } > <FaTrash/> </button> </td>
            </tr>
        </>
    )
} 