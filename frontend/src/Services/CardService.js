import api from "./api";

const CardService = () => {
  const FetchCardService = async () => {
    try {
      const response = await api.get('card/by-user');
      return response;
    } catch (error) {
        
      return error
    }
  };

  return { FetchCardService };
};

export default CardService;
