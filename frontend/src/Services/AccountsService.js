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

    const FetchAccountsServiceByCVU = async (data) => {

        let payload = {};

        if (/^[a-z]+\.[a-z]+\.[a-z]+$/.test(data)) {
            payload = { alias: data };  
          } else if (/^CBU\d{24}$/.test(data)) {
            payload = { cbu: data };  

          } else {
            payload = { cbu: data || null };         
         }

        
        try {
            const response = await api.post('/api/accounts/check', payload);

            return response.data;
        } catch (error) {            
                return true

        }
    };


    const CreateAccountsService = async (currency) => {

        try {
            const response = await api.post('/api/accounts', {currency});
         
            return '';
        } catch (error) {
            if(error.status === 404){ 
                return 'Ya existe cuenta en esa moneda'
            }
            return 'Fallo el sistema'
        }
    };

    return { FetchAccountsService, CreateAccountsService, FetchAccountsServiceByCVU };


}


export default AccountsService