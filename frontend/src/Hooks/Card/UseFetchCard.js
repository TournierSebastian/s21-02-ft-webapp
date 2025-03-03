import CardService from "../../Services/CardService";


const UseFecthCard = () =>{
    const {FetchCardService} = CardService();
    const FetchCarduser = async ()=>{
            const data = await FetchCardService();

            if(data.status == 200){
                return data.data[0];
            }
            if(data.status == 404){
                return true;
            }


    };

    return {FetchCarduser};

}
export default UseFecthCard;