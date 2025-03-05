import api from "./api";


const CurrenciesService = () => {
    const FetchCurrenciesService = async () => {
        try {
            const response = await api.get('/api/accounts/currencies');
         
            return response.data;
        } catch (error) {

            return error
        }
    };

    return { FetchCurrenciesService };


}


export default CurrenciesService