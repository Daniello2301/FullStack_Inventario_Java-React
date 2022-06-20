import React from "react";

import SweetAlert2 from "sweetalert2";
import Swal from "sweetalert2";
import 'sweetalert2/src/sweetalert2.scss';

export function SualModal({ title, text, confirmButtonText, confirmButtonColor, onConfirm }) {

    const handleConfirm = () => {
        onConfirm();
        Swal.fire({
            title: 'Cargando...',
            text: 'Espere un momento',
            showConfirmButton: false,
            timer: 1000,
        })
    }

    return (
        <>
            <Swal
                title={title}
                text={text}
                confirmButtonText={confirmButtonText}
                confirmButtonColor={confirmButtonColor}
                onConfirm={handleConfirm}
            />
        </>
    )

}