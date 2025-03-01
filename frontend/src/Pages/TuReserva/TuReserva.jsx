import React, { useEffect, useState } from "react";
import Navbar from "../../components/navbar/userNavbar";
import UseFetchReserve from "../../Hooks/Reserve/UseFecthReserve";
import iconchancho from '../../assets/General/chancho.png';

const TuReserva = () => {
  const { FetchReserve } = UseFetchReserve();
  const [reserve, setReserve] = useState([]);
  const [CreateReserve, SetCreateReserve] = useState(false);
  const [Name, SetName] = useState('');
  const [Amount, SetAmount] = useState(0);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await FetchReserve();
        console.log(data);
        setReserve(data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };


    fetchData();
  }, []);

  return (
    <div>
      <nav>
        <Navbar />
      </nav>

      <div className="flex justify-center items-center mt-10 ">

        {reserve.length === 0 ? (
          CreateReserve ? (
            <div className="bg-BlackBlue rounded-2xl items-center text-center p-5 flex flex-col w-full md:w-1/2">
              <div>
                <h3 className="text-white text-2xl sm:text-2xl md:text-2xl lg:text-3xl">Nombre de la reserva</h3>
                <input
                  type="text"
                  className="bg-WhiteBlack rounded-lg px-2 py-1"
                  onChange={(e) => SetName(e.target.value)}
                  placeholder="Ingrese nombre"
                />
              </div>
              <div>
                <h3 className="text-white text-2xl sm:text-3xl md:text-3xl lg:text-4xl">
                  ¿Cuánto dinero quieres reservar?
                </h3>
                <input
                  type="number"
                  value={Amount}
                  placeholder="$"
                  onChange={(e) => { SetAmount(e.target.value); }}
                  className="placeholder-white text-white text-5xl bg-transparent border-none focus:outline-none [&::-webkit-inner-spin-button]:appearance-none [&::-webkit-outer-spin-button]:appearance-none ml-2 w-full text-center"
                />
                <p className="text-white">$10.10 disponibles</p>

                <button className="bg-LightGolden hover:bg-DarkGolden px-4 py-2 rounded-2xl mt-3" onClick={() => SetCreateReserve(!CreateReserve)}>
                  Reservar
                </button>
              </div>
            </div>
          ) : (
            <div className="bg-BlackBlue rounded-2xl items-center text-center p-5 flex flex-col w-full md:w-1/2">
              <img src={iconchancho} className="invert mx-auto mb-3" alt="Icono chancho" />
              <h2 className="text-white text-3xl">Crear una nueva reserva</h2>
              <p className="text-white">Organizá tu dinero y alcanzá tus objetivos.</p>
              <button className="bg-LightGolden hover:bg-DarkGolden px-4 py-2 rounded-2xl mt-3" onClick={() => SetCreateReserve(!CreateReserve)}>
                Crear Reserva
              </button>
            </div>
          )
        ) : (
          <p>Estoy Lleno</p>
        )}
      </div>
    </div>
  );
};

export default TuReserva;
