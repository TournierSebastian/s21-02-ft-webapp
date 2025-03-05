import React from 'react'
import './Transferir'
import Navbar from '../../components/navbar/userNavbar'
const Transferir = () => {
  return (
    <div className='Contenido'>
      <nav><Navbar /></nav>
      <main>
      <div className="flex justify-center items-center mt-10">
          <div className="flex flex-col w-full max-w-md space-y-6 space rounded-xl bg-BlackBlue p-8 text-WhiteBlack">
              <div>
                  <h1 className="text-2xl">Ingresa nuevo destinatario</h1>
              </div>

              <div className="flex flex-col justify-center gap-3 text-white">
                  <label htmlFor="transfer-input">Ingrese el CBU o Alias</label>
                  <input className="px-8 py-2 bg-LightGolden rounded-md text-BlackBlue placeholder:text-Gray" type="text" name="transfer-input" id="transfer-input" placeholder="Ingrese CBU o Alias" />
                  <label htmlFor="amount-input">Monto</label>
                  <input className="px-8 py-2 bg-LightGolden rounded-md text-BlackBlue appearance-none [&::-webkit-inner-spin-button]:appearance-none [&::-webkit-outer-spin-button]:appearance-none placeholder:text-Gray" type="number" name="amount-input" id="amount-input" placeholder="Monto a transferir"/>
                  <input className="px-4 py-2 bg-LightGolden text-BlackBlue rounded-3xl cursor-pointer hover:bg-Gray hover:text-LightGolden transition duration-500" type="submit" value="Continuar" />
              </div>
          </div>
      </div>
        </main>
    </div>
  )
}

export default Transferir

