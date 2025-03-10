import React, { useEffect, useState } from 'react'
import './home.css'
import Navbar from '../../components/navbar/Navbar'
import { ChevronRightIcon } from 'lucide-react'
import logo from '../../assets/Icons/Logo.png'
import mastercard from '../../assets/Icons/Mastercard.png'
import UseAccounts from '../../Hooks/Accounts/UseAccounts'
import ActivityItem from '../../components/Activity/ActivityItem'
import UseMovements from '../../Hooks/Movements/UseMovements'
const Home = () => {
  const {FetchAccountsbyid} = UseAccounts();
  const {FetchMovementsbyuser} = UseMovements();
  const [Accounts, SetAccounts] = useState([]);
  const [Movements, SetMovements] = useState([])
  const [activities] = useState([
    {
      date: '15 de Febrero, 2025',
      username: 'Carlos Rodriguez',
      reason: 'Transferencia de dinero',
      amount: '-150.00',
      time: '14:30',
      icon: "🔃",
    },
    {
      date: '14 de Febrero, 2025',
      username: 'María González',
      reason: 'Pago de servicios',
      amount: '-75.50',
      time: '11:20',
      icon: "🍔",
    },
    {
      date: '14 de Febrero, 2025',
      username: 'Juan Pérez',
      reason: 'Transferencia recibida',
      amount: '+200.00',
      time: '09:45',
      icon: "🔃",
    },
    {
      date: '13 de Febrero, 2025',
      username: 'Ana Silva',
      reason: 'Pago de alquiler',
      amount: '-300.00',
      time: '16:15',
      icon: "💵",
    },
    {
      date: '13 de Febrero, 2025',
      username: 'Luis Torres',
      reason: 'Depósito',
      amount: '+450.00',
      time: '10:00',
      icon: "💰",
    },
  ])


  const fetchData = async () => {
          try {
              const dataAccounts = await FetchAccountsbyid();
              const dataMovements = await FetchMovementsbyuser();
              SetMovements(dataMovements)
              SetAccounts(dataAccounts || [])
              console.log(dataMovements)
          } catch (error) {
              SetCurrencies([]);
          }
      };
      useEffect(() => {
  
          fetchData();
      }, [])
  
  return (
    <div className='Contenido'>
      <nav><Navbar/></nav>
      <main>
      <div className='flex flex-col justify-center items-center mt-10'>

      <div className=" w-full max-w-md bg-BlackBlue rounded-xl shadow-lg p-6 space-y-6 text-white">
      {/* Gaston: Cabecera de la tarjeta*/}
      <div className="flex justify-between items-center">
        <h2 className="text-xl font-semibold">Dinero Disponible</h2>
        <a className="px-4 py-1 bg-LightGolden text-gray-900 rounded-lg cursor-pointer hover:bg-DarkGolden transition-colors" href='/CVU'>
          Tu CVU
        </a>
      </div>

      {/*Gaston: Dinero disponible*/}
      <div className="text-3xl font-bold text-left">
        ${Accounts.balance}
      </div>

      {/* Gaston: Botones de accion*/}
      <div className="grid grid-cols-2 gap-4">
        <button className="px-4 py-1 bg-LightGolden  cursor-pointer text-blue-900 rounded-lg hover:bg-DarkGolden transition-colors font-medium">
          Ingresar Dinero
        </button>
        <button className="px-4 py-1 bg-LightGolden cursor-pointer text-blue-900 rounded-lg hover:bg-DarkGolden transition-colors font-medium">
          Transferir Dinero
        </button>
      </div>
        <div className='flex justify-between'>        
      {/*Gaston: Marca de la tarjeta*/}
      {/*Gaston: Falta agregar los iconos */}
      <a className="w-3/4 border-LightGolden px-4 py-1 border-6 rounded-xl " href='/tutarjeta'>
        <div className='flex flex-row gap-3 '>
          <img src ={mastercard} className='w-10 h-10'></img>
          <img src = {logo} className='w-10 h-10'></img>
        </div>
     
        <div className='flex justify-between'>
          <span className="text-1xl text-blue-200 italic ">Tarjeta Wallex</span>
          <ChevronRightIcon size={20} color="#F5E7BE" strokeWidth={2} />
        </div>
      </a>

        <img src={logo} className='w-15 h-15'></img>
      </div>
    </div>
    
    <div className="w-full max-w-md bg-BlackBlue rounded-3xl shadow-lg p-6 space-y-6 text-black mt-10">
          <h2 className="text-xl font-semibold text-white">Tu última actividad</h2>

          <div className="space-y-2 ">
            {Movements.map((movements, index) => (
              <ActivityItem key={index} {...movements} />
            ))}
          </div>

          <button className="w-full text-xl py-3 text-gray-300 text-left px-2 rounded-lg cursor-pointer hover:text-white transition-colors font-medium">
            Ver toda la actividad
          </button>
        </div>
  </div>
      </main>
      
    </div>
  )
}

export default Home