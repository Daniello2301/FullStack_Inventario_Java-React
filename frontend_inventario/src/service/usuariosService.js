const API_URL = 'http://localhost:8082/iud/api/usuarios';

export async function getAllUsuarios(){
    try 
    {
        const response = await fetch(API_URL);
        const data = await response.json();
        return data;
        
    } catch (error) {
        console.log(error);
    }
}