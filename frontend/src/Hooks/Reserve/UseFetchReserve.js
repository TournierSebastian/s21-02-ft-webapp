import ReserveService from "../../Services/ReserveService";

const UseFecthReserve = () =>{
    const {FetchReserveService} = ReserveService();
    const FetchReserve = async() =>{

        const response = FetchReserveService();

        return response;
        
    }
    return {FetchReserve}
}
export default UseFecthReserve;