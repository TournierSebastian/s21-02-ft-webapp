import React, { useEffect, useState } from 'react'
import Navbar from '../../components/navbar/userNavbar'
import CurrenciesService from '../../Services/CurrenciesService'
import AccountsService from '../../Services/AccountsService';
import { useNavigate } from 'react-router-dom';

const Accounts = () => {
    const { FetchCurrenciesService } = CurrenciesService();
    const { FetchAccountsService } = AccountsService();

    const [Currencies, SetCurrencies] = useState([]);
    const [Accounts, SetAccounts] = useState([]);
    const navigate = useNavigate();
    
    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await FetchCurrenciesService();
                const dataAccounts = await FetchAccountsService();
                SetAccounts(dataAccounts || [])
                SetCurrencies(data || []);
            } catch (error) {
                SetCurrencies([]);
            }
        };

        fetchData();
    }, [])

    const HandlerSelectAccount = (accountId) =>{
        localStorage.setItem("Account", accountId);
        navigate('/home')


    }
    return (
        <div className='Contenido'>
            <nav>
                <Navbar />
            </nav>
            <main>
                <div className='flex flex-col justify-center items-center mt-10'>
                    <div className='max-w-lg w-full'>
                        <h2 className='text-4xl'> ¡Bienvenido a Wallex! 🎉 </h2>

                        <p className='text-2xl'>
                            Tu billetera virtual para manejar tu dinero de forma segura y sencilla. Selecciona tus cuentas y empieza a operar de inmediato en la moneda que desees.
                        </p>
                    </div>


                    <div className='flex  flex-col items-center bg-BlackBlue p-5  rounded-3xl justify-center mt-10'>
                        <h1 className='text-LightGolden text-4xl'>Abre tu Cuenta</h1>
                        <div>
                            <h4 className='text-white text-2xl'>Seleciona tu moneda</h4>
                            <select className='bg-WhiteBlack w-full rounded-2xl py-1 m-2'>
                                {Currencies.length > 0 ? (
                                    Currencies.map((currency, index) => (
                                        <option key={index} value={currency}>
                                            {currency}
                                        </option>
                                    ))
                                ) : (
                                    <option>No hay opciones</option>
                                )}
                            </select>
                        </div>

                        <button className='bg-LightGolden hover:bg-DarkGolden rounded-2xl px-2 py-1'>
                            Abrir cuenta
                        </button>
                    </div>

                        {Accounts.length > 0 ? (
                            Accounts.map((account, index) => (
                                <div key={index} className='bg-BlackBlue p-4 rounded-xl mt-4 w-3/4 flex justify-between items-center'>
                                    <h3 className='text-LightGolden text-2xl'>{account.alias}</h3>
                                    <p className='text-white text-xl'>Saldo: {account.balance}</p>
                                    <button className='bg-LightGolden hover:bg-DarkGolden rounded-2xl px-4 py-2 mt-2'     onClick={() => {HandlerSelectAccount(account.accountId)}}>
                                        Entrar
                                    </button>
                                </div>
                            ))
                        ) : (
                            <p className='text-white text-xl'>No tienes cuentas aún.</p>
                        )}
                </div>
            </main>

        </div>
    )
}

export default Accounts