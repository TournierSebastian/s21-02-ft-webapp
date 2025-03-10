import CardService from "../../Services/CardService";
import MovementsService from "../../Services/MovementsService";
import TransactionsService from "../../Services/TransactionsService";

const UseTransactions = () => {
    const { FetchTransactionsServicee } = TransactionsService();

    const FetchTransactions = async (order = 'asc') => {
        const response = await FetchTransactionsServicee();
        if (response.status == 200) {
            const sortedData = response.data.sort((a, b) => {
                const dateA = new Date(a.date);
                const dateB = new Date(b.date);

              
                if (order === 'asc') {
                    return dateA - dateB;
                } else if (order === 'desc') {
                    return dateB - dateA;
                }
                return 0; 
            });
            return sortedData;
        }
    };

    return { FetchTransactions };
};

export default UseTransactions;
