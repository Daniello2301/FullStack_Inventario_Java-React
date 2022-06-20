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

export async function getByIdUsuario(id){
    try 
    {
        const response = await fetch(API_URL + '/' + id);
        const data = await response.json();
        return data;
        
    } catch (error) {
        console.log(error);
    }
}

export async function createUsuario(usuario){
    try 
    {
        const response = await fetch(API_URL, {
            method: 'POST',
            body: JSON.stringify(usuario),
            headers: {
                'Content-Type': 'application/json'
            }
        });
        const data = await response.json();
        return data;
        
    } catch (error) {
        console.log(error);
    }
}


export async function deleteByIdUsuario(id){
    try 
    {
        const response = await fetch(API_URL + '/' + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }

        });
        const data = await response.json();
        return data;
        
    } catch (error) {
        console.log(error);
    }
}

export async function updateUser(usuario){
    try 
    {
        const response = await fetch(API_URL + '/' + usuario.id, {
            method: 'PUT',
            body: JSON.stringify(usuario),
            headers: {
                'Content-Type': 'application/json'
            }
        });
        const data = await response.json();
        return data;
        
    } catch (error) {
        console.log(error);
    }
}
