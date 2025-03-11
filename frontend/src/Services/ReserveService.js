import api from "./api";

const ReserveService = () => {

    const FetchReserveService = async () => {
        const id = localStorage.getItem('Account');

        try {
            const response = await api.get(`/api/accounts/${id}/reservations`);
            
            return response.data; 
    
        } catch (error) {
            console.error('Error al traer reservas:', error);
            throw error;
        }
    };
    
    const CreateReserveService = async (amount, description)=>{

        const accountId = localStorage.getItem('Account');
       

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