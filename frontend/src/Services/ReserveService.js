import api from "./api";

const ReserveService = () => {

    const FetchReserveService = async () => {
        try {
            const response = await api.get('api/accounts/1/reservations');
            
            return response.data; 
    
        } catch (error) {
            console.error('Error al traer reservas:', error);
            throw error;
        }
    };
    
    const CreateReserveService = async (amount, accountId, description)=>{
        try {
            await api.post("/api/reservations", { amount, accountId, description });
            return '';
        } catch (error) {
            if (error.response && error.response.status === 409) {
                alert('No hay dinero Insuficiente'); 
            }
            console.error("Error al crear reserva:", error);
        }
    }


    return {FetchReserveService, CreateReserveService}
}

export default ReserveService;