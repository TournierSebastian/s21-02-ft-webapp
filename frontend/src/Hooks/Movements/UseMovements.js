import CardService from "../../Services/CardService";
import MovementsService from "../../Services/MovementsService";


const UseMovements = () =>{
    const {FetchMovementsService} = MovementsService();
    const FetchMovementsbyuser = async ()=>{
            const response = await FetchMovementsService();
            if(response.status == 200){
                return response.data;
            }


    };

    return {FetchMovementsbyuser};

}
export default UseMovements;