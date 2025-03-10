import { useEffect, useState } from 'react'
import Navbar from '../../components/navbar/Navbar'
import ActivityItem from '../../components/Activity/ActivityItem'
import UseTransactions from '../../Hooks/Transactions/UseTransactions'

function Actividad() {
  const [Transacción, SetTransacción] = useState([])
  const { FetchTransactions } = UseTransactions();
  const [Order, setOrder] = useState("desc")
  const fetchData = async () => {
    try {
      const dataTransacción = await FetchTransactions(Order);
      SetTransacción(dataTransacción || [])
    } catch (error) {
      SetCurrencies([]);
    }
  };
  useEffect(() => {
    fetchData();
  }, [Order])
  return (
    <div className='Contenido'>
      <nav> <Navbar />   </nav>
      <main>
        <div className='flex justify-center items-center mt-10'>
          <div className="w-full max-w-md bg-BlackBlue rounded-3xl shadow-lg p-6 space-y-6 text-black ">
            <h2 className="text-xl font-semibold text-white">Tu última actividad</h2>
            <select
              onChange={(e) => setOrder(e.target.value)}
              className="bg-white text-black rounded p-2"
            >
              <option value="" disabled selected>Ordenar por fecha</option> {/* Opción no seleccionable */}
              <option value="desc">Descendente</option>
              <option value="asc">Ascendente</option>
            </select>
            <div className="space-y-2 ">
              {Transacción.map((transacción, index) => (
                <ActivityItem key={index} data={transacción} />
              ))}
            </div>
          </div>
        </div>
      </main>
    </div>

  )
}

export default Actividad
