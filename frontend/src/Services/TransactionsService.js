import UseAccounts from "../Hooks/Accounts/UseAccounts";
import api from "./api";


const TransactionsService = () => {
    const { FetchAccountsbyid } = UseAccounts();

    const FetchTransactionsServicee = async () => {
        try {
            const response = await api.get('/api/accounts/1/transactions');

            return response;
        } catch (error) {

            return error
        }
    };


    const CreateTransactionsService = async (transferUser, amount, Reason) => {

        const cvu = await FetchAccountsbyid();
        
        const formdata = 
        {
            sourceCbu: cvu.cbu,
            destinationCbu: transferUser ,
            amount: amount,
            type: "TRANSFER",
            reason: Reason
        }

        console.log(formdata)
        try {
            const response = await api.post('/api/transactions', formdata);
            if(response.status){
                 window.location.href = '/home'
            }
            return '';
        } catch (error) {
            if(error.status === 409){
                alert('No hay dinero suficiente');
                return error;

            }
            alert('falla el servidor')
            return error;
        }
    };

    return { FetchTransactionsServicee, CreateTransactionsService};


}


export default TransactionsService;