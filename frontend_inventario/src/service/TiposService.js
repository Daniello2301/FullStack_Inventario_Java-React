
const API_URL = "http://localhost:8082/iud/api/tipos";

export async function getAllTipos() {
    try {
        const response = await fetch(API_URL);
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}

/* get tipo */
export async function getTipo(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}

/* create tipo */
export async function createTipo(data) {
    try {
        const response = await fetch(API_URL, {
            method: "POST",
            body: JSON.stringify(data),
            headers: {
                "Content-Type": "application/json"
            }
        });
        const dataResponse = await response.json();
        return dataResponse;
    } catch (error) {
        console.log(error);
    }

}

/* update marca */
export async function updateTipo(id, data) {
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "PUT",
            body: JSON.stringify(data),
            headers: {
                "Content-Type": "application/json"
            }
        });
        const dataResponse = await response.json();
        return dataResponse;
    } catch (error) {
        console.log(error);
    }
}

/*  delete tipo */
export async function deleteTipo(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "DELETE"
        });
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}