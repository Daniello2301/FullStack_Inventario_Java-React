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