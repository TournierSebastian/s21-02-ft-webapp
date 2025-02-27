import CardService from "../../Services/CardService";


const UseFecthCard = () =>{
    const {FetchCardService} = CardService();
    const FecthCarduser = async ()=>{
            const data = await FetchCardService();

            if(data.status == 200){
                return data.data[0];
            }
            if(data.status == 404){
                return true;
            }


    };

    return {FecthCarduser};

}
export default UseFecthCard;