import AccountsService from "../../Services/AccountsService";


const UseAccounts = () =>{
    const {FetchAccountsService} = AccountsService();

    const Accountid = Number(localStorage.getItem("Account"));

    const FetchAccountsbyid= async ()=>{
            const data = await FetchAccountsService();        
            const filteredAccount = data.find(account => account.accountId === Accountid);

            return filteredAccount;


    };

    return {FetchAccountsbyid};

}
export default UseAccounts;