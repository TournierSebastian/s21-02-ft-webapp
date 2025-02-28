import ReserveService from "../../Services/ReserveService";

const UseFecthReserve = () =>{
    const {FecthReserveService} = ReserveService();
    const FecthReserve = async() =>{

        const response = FecthReserveService();

        return response;
        
    }
    return {FecthReserve}
}
export default UseFecthReserve;