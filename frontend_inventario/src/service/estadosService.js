const API_URL = "http://localhost:8082/iud/api/estados";

export async function getAllEstados() {
    try {
        const response = await fetch(API_URL);
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}

/* Create estado */
export async function createEstado(data) {
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

/*  get estado */
export async function getEstado(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}

/* update estado */
export async function updateEstado(id, data) {
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

/* delete estado */
export async function deleteEstado(id){
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