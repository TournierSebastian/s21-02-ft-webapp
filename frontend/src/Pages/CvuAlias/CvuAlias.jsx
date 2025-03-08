import React, { useEffect, useState } from 'react'
import Navbar from '../../components/navbar/Navbar'
import UseAccounts from '../../Hooks/Accounts/UseAccounts'
import { Files } from 'lucide-react';

const CvuAlias = () => {
    const { FetchAccountsbyid } = UseAccounts();
    const [Account, SetAccount] = useState();
    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await FetchAccountsbyid();
                SetAccount(data)
              
            } catch (error) {
                console.error("Error fetching accounts:", error);
            }
        };

        fetchData();
    }, []);

    const copyToClipboard = (text) => {
        if (text) {
            navigator.clipboard.writeText(text);
        }
    };
    

    return (
        <div className="Contenido">
            <nav>
                <Navbar />
            </nav>
            <main>
                <div className="flex flex-col justify-center items-center mt-10">
                    <h1 className="text-2xl text-center">
                        Copia tu CVU o alias para ingresar o transferir dinero desde otra cuenta
                    </h1>

                    {/* CVU */}
                    <div className="bg-BlackBlue text-LightGolden flex flex-col w-3/4 rounded-2xl p-2 mt-4">
                        <div className="flex justify-between items-center">
                            <h2 className='text-2xl'>CVU:</h2>
                            <Files className="cursor-pointer" onClick={() => copyToClipboard(Account?.cbu || "")} />
                            </div>
                        <p className='text-1xl'>{Account ? Account.cbu : "Cargando..."}</p>
                    </div>

                    {/* Alias */}
                    <div className="bg-BlackBlue text-LightGolden flex flex-col w-3/4 rounded-2xl p-2 mt-4">
                        <div className="flex justify-between items-center">
                            <h2 >Alias</h2>
                            <Files className="cursor-pointer" onClick={() => copyToClipboard(Account?.alias || "")} />
                        </div>
                        <p className='text-1xl'>{Account ? Account.alias : "Cargando..."}</p>
                    </div>
                </div>
            </main>
        </div>
    );
};

export default CvuAlias