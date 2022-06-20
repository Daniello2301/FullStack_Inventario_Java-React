const API_URL = "http://localhost:8082/iud/api/equipos";

export async function getAllEquipos() {
    try {
        const response = await fetch(API_URL);
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}


export async function getEquipoById(id) {
    try {
        const response = await fetch(API_URL + "/" + id);
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}

export async function createEquipo(equipo) {
    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(equipo)
        });
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}

export async function updateEquipo(equipo) {
    try {
        const response = await fetch(API_URL + "/" + equipo.id, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(equipo)
        });
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}

export async function deleteEquipo(id) {
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