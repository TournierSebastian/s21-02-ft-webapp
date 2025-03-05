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

    return { FetchAccountsService };


}


export default AccountsService