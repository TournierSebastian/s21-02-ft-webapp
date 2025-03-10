import api from "./api";


const MovementsService = () => {
    const FetchMovementsService = async () => {
        try {
            const response = await api.get('/api/movements');
         
            return response;
        } catch (error) {

            return error
        }
    };

    return { FetchMovementsService };


}


export default MovementsService