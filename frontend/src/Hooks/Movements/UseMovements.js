import CardService from "../../Services/CardService";
import MovementsService from "../../Services/MovementsService";


const UseMovements = () =>{
    const {FetchMovementsService} = MovementsService();
    const FetchMovementsbyuser = async ()=>{
            const response = await FetchMovementsService();
            if(response.status == 200){
                return response.data.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
            }


    };

    return {FetchMovementsbyuser};

}
export default UseMovements;