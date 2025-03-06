import api from "./api";

const CardService = () => {
  const FetchCardService = async () => {
    try {
      const response = await api.get('/api/cards');

      return response;
    } catch (error) {
        
      return error
    }
  };


  const CreateCardService = async (currency) => {
    try {
        await api.post('/api/cards/register', {currency});
     
        return '';
    } catch (error) {
       
        return error
    }
};
  return {FetchCardService, CreateCardService };
};

export default CardService;
