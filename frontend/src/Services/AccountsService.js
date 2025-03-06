import api from "./api";


const AccountsService = () => {
    const FetchAccountsService = async () => {
        try {
            const response = await api.get('/api/accounts');
            
            return response.data;
        } catch (error) {

            return error
        }
    };


    const CreateAccountsService = async (currency) => {
        
        try {
            const response = await api.post('/api/accounts', {currency});
         
            return '';
        } catch (error) {
            if(error.state = 404){ 
                return 'Ya existe cuenta en esa moneda'
            }
            return error
        }
    };

    return { FetchAccountsService, CreateAccountsService };


}


export default AccountsService