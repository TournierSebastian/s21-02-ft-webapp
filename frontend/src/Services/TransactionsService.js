import api from "./api";


const TransactionsService = () => {
    const FetchTransactionsServicee = async () => {
        try {
            const response = await api.get('/api/accounts/1/transactions');
         
            return response;
        } catch (error) {

            return error
        }
    };

    return { FetchTransactionsServicee };


}


export default TransactionsService;