
const API_URL = "http://localhost:8082/iud/api/marcas";

export async function getAllMarcas() {
    try {
        const response = await fetch(API_URL);
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}

/* Create marca */
export async function createMarca(marca) {
    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(marca)
        });
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}

/* get Marca by id*/
export async function getMarcaById(id){
    try {
        const response = await fetch(API_URL + "/" + id);
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}

/* Update marca */
export async function updateMarca(marca){
    try {
        const response = await fetch(API_URL + "/" + marca.id, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(marca)
        });
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}

/* Delete marca */
export async function deleteMarca(id){
    try {
        const response = await fetch(API_URL + "/" + id, {
            method: "DELETE"
        });
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}