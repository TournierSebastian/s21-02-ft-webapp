import React, { useContext, useEffect, useState } from 'react'
import logo from './../../assets/Icons/Logo.png'
import profile from './../../assets/Icons/profile.png'

import { Bell, ChevronRight, CircleHelp, CreditCard, House, LogOut, Menu, PiggyBank, SquareActivity, SquareUserRound, Wallet, WalletCards, X } from 'lucide-react';
import { AuthContext } from '../../Context/AuthContext';
import Notification from '../Notification/Notification';
const Navbar = () => {
  const [Route, Setroute] = useState("");
  const [menuOpen, setMenuOpen] = useState(false);
  const { Name } = useContext(AuthContext);
  const [OpenProfile, SetOpenProfile] = useState(false)
  const {logout} = useContext(AuthContext);

  useEffect(() => {
    const currentPath = window.location.pathname;
    Setroute(currentPath);
  }, []);


  return (
    <>
      {(Route == '/' || Route == '/login' || Route == '/register') &&
        <div className="bg-BlackBlue px-4 py-2 flex justify-between items-center">
          <a className="flex items-center" href="/">
            <img src={logo} className="w-16 md:w-20" alt="Logo" />
            <h3 className="text-LightGolden text-xl md:text-2xl ml-3">Wallex</h3>
          </a>

          <div className="hidden min-[450px]:flex">
            <a
              className="bg-LightGolden px-4 py-2 rounded-2xl hover:bg-DarkGolden mr-3"
              href="/login"
            >
              Iniciar sesión
            </a>
            <a
              className="bg-LightGolden px-4 py-2 rounded-2xl hover:bg-DarkGolden"
              href="/register"
            >
              Abrí tu cuenta
            </a>
          </div>

          <button
            className="min-[450px]:hidden text-LightGolden text-2xl"
            onClick={() => setMenuOpen(!menuOpen)}
          >
            {menuOpen ? <X size={30} /> : <Menu size={30} />}
          </button>

          {menuOpen && (
            <div className="absolute top-16 left-0 w-full bg-BlackBlue flex flex-col items-center py-5 space-y-4 min-[450px]:hidden">
              <a
                className="bg-LightGolden px-4 py-2 rounded-2xl hover:bg-DarkGolden w-3/4 text-center"
                href="/login"
                onClick={() => setMenuOpen(false)}
              >
                Iniciar sesión
              </a>
              <a
                className="bg-LightGolden px-4 py-2 rounded-2xl hover:bg-DarkGolden w-3/4 text-center"
                href="/register"
                onClick={() => setMenuOpen(false)}
              >
                Abrí tu cuenta
              </a>
            </div>
          )}
        </div>
      }

      {(Route != '/' && Route != '/login' && Route != '/register') &&
        <>
          <div className='bg-BlackBlue flex justify-between items-center'>
            <div className='flex items-center'>
              <button className='flex justify-between border-r-2 w-55 max-[405px]:w-37  border-[#B6B2B2]' onClick={() => { SetOpenProfile(!OpenProfile) }}>
                <div className='flex  items-center mx-2 max-[405px]:mx-0 '>
                  <div className='mr-2'>
                    <img src={profile} className='w-20 max-[405px]:w-10'></img>

                  </div>
                  <div className='text-lg'>
                    <h3 className='text-LightGolden'> 

                    {Name ? (Name.length > 10 ? Name.slice(0, 10) + ".." : Name) : "Cargando..."}
                    </h3>
                    <a className='flex items-center mt-1' >
                      <h4 className='text-LightGolden'>Tu Perfil</h4>
                      <ChevronRight size={20} color="#F5E7BE" strokeWidth={2} />
                    </a>
                  </div>
                </div>
              </button>


              <section className="ml-10 max-[405px]:ml-1">
                <p className='text-white text-2xl'>
                  {Route === '/home' && 'Inicio'}
                  {Route === '/tudinero' && 'Tu dinero'}
                  {Route === '/actividad' && 'Actividad'}
                  {Route === '/transferir' && 'Transferir'}
                  {Route === '/tureserva' && 'Reservas'}
                  {Route === '/tutarjeta' && 'Tu tarjeta'}
                  {Route === '/account' && 'Cuentas'}
                  {Route === '/CVU' && 'Consultar CVU y Alias'}
                </p>
              </section>
            </div>

            <button
              className="min-[490px]:hidden text-LightGolden text-2xl"
              onClick={() => setMenuOpen(!menuOpen)}
            >
              {menuOpen ? <X size={30} /> : <Menu size={30} />}
            </button>


            {menuOpen && (
              <div className="absolute top-15 right-0  w-30 bg-BlackBlue flex flex-col items-center py-2 space-y-4 min-[405px]:hidden rounded-b-2xl">
                <section className=' flex items-center'>
                  <Notification />
                  <a className='flex bg-LightGolden text-black px-2 py-1 rounded-2xl mx-2 hover:bg-DarkGolden' href='/ayuda'>
                    <CircleHelp className='mr-1' />
                    <p>Ayuda</p>
                  </a>
                </section>
              </div>
            )}
            
            <section className=' flex items-center max-[490px]:hidden'>
              <Notification />
              <a className='flex bg-LightGolden text-black px-2 py-1 rounded-2xl mx-2 hover:bg-DarkGolden' href='/ayuda'>
                <CircleHelp className='mr-1' />
                <p>Ayuda</p>
              </a>
            </section> 
          </div>






          {OpenProfile && (
            <div className="bg-[#CCCCCC] absolute top-21 max-[405px]:top-15 left-0  w-55  max-[330px]:w-37 border-r-2 border-b-2 border-[#B6B2B2]  rounded-br-2xl p-3 ">
              {Route == '/home' && (
                <div className="absolute top-3 left-0 h-10 w-[4px] bg-BlackBlue"></div> // Barra azul a la izquierda
              )}
              {Route == '/tudinero' && (
                <div className="absolute top-15 left-0 h-8 w-[4px] bg-BlackBlue"></div> // Barra azul a la izquierda
              )}
              {Route == '/actividad' && (
                <div className="absolute top-26 left-0 h-8 w-[4px] bg-BlackBlue"></div> // Barra azul a la izquierda
              )}
              {Route == '/transferir' && (
                <div className="absolute top-37 left-0 h-8 w-[4px] bg-BlackBlue"></div> // Barra azul a la izquierda
              )}
              {Route == '/tureserva' && (
                <div className="absolute top-48 left-0 h-8 w-[4px] bg-BlackBlue"></div> // Barra azul a la izquierda
              )}
              {Route == '/tutarjeta' && (
                <div className="absolute top-58 left-0 h-8 w-[4px] bg-BlackBlue"></div> // Barra azul a la izquierda
              )}
              {Route == '/account' && (
                <div className="absolute top-69 left-0 h-8 w-[4px] bg-BlackBlue"></div> // Barra azul a la izquierda
              )}

              <a href='/home' className={`  hover:text-BlackBlue flex border-[#8D8D8D] border-b-2 items-end w-45 mx-auto ${Route == '/home' && 'text-BlackBlue'}`}>
                <House size={35} className='mb-1' />
                <p className='text-2xl leading-none mb-1'>Inicio</p>
              </a>

              <div className='mt-2 text-2xl leading-none '>
                <a href='/tudinero' className={`hover:text-BlackBlue  flex items-end w-45 mx-auto   ${Route == '/tudinero' && 'text-BlackBlue'} `}>
                  <Wallet size={35} />
                  <p>Tu dinero</p>
                </a>

                <a href='/actividad' className={`hover:text-BlackBlue  flex items-end w-45 mx-auto mt-2 ${Route == '/actividad' && 'text-BlackBlue'} `}>
                  <SquareActivity size={35} />
                  <p>Actividad</p>
                </a>

                <a href='/transferir' className={`hover:text-BlackBlue  flex items-end w-45 mx-auto mt-2 ${Route == '/transferir' && 'text-BlackBlue'} `}>
                  <WalletCards size={35} />
                  <p>Transferir</p>
                </a>

                <a href='/tureserva' className={`hover:text-BlackBlue  flex items-end w-45 mx-auto mt-2 ${Route == '/tureserva' && 'text-BlackBlue'} `}>
                  <PiggyBank size={35} />
                  <p>Reservas</p>
                </a>

                <a href='/tutarjeta' className={`hover:text-BlackBlue  flex items-end w-45 mx-auto mt-2 ${Route == '/tutarjeta' && 'text-BlackBlue'} `}>
                  <CreditCard size={35} />
                  <p>Tu tarjeta</p>
                </a>

                <a href='/account' className={`hover:text-BlackBlue  flex items-end w-45 mx-auto mt-2 ${Route == '/account' && 'text-BlackBlue'} `}>
                  <SquareUserRound size={35} />
                  <p>Cuentas</p>
                </a>
                
                <button  className={`flex items-end w-45 mx-auto mt-2 `} onClick={()=>(logout())}>
                  <LogOut size={20} />
                  <p className='text-lg leading-none'>Cerrar sesion</p>
                </button>
              </div>

            </div>
          )}
        </>
      }



    </>
  )
}

export default Navbar