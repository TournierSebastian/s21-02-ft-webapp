
function ActivityItem({data}) {
  console.log("Activity Data:", data);


  const formattedDate = data.createdAt.split("T")[0]; // Extrae solo la fecha
  const [year, month, day] = formattedDate.split("-"); // Divide en año, mes y día

  const monthNames = [
    "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
    "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
  ];
  const monthName = monthNames[parseInt(month, 10) - 1]; // Convierte el número del mes a texto
  const formattedDateWithMonthName = `${day} de ${monthName} de ${year}`;


  const formattedTime = data.createdAt.split("T")[1].split(".")[0]; // Extrae la hora completa
  const [hour, minutes] = formattedTime.split(":"); // Divide en horas y minutos
  const formattedTimeWithoutSeconds = `${hour}:${minutes}`;
//   {
//     "movementId": 1,
//     "transactionId": 1,
//     "sourceAccount": {
//         "cbu": "CBU000000035110000000000011",
//         "alias": "otter.wood.chair",
//         "currency": "ARS",
//         "owner": "María Gómez",
//         "dni": "87654321"
//     },
//     "destinationAccount": {
//         "cbu": "CBU000000035160000000000024",
//         "alias": "gnat.rubber.bed",
//         "currency": "ARS",
//         "owner": "Carlos López",
//         "dni": "56789123"
//     },
//     "createdAt": "2025-03-10T12:31:45.830149",
//     "description": "Movimiento generado para la transacción 1",
//     "type": "TRANSFER",
//     "status": "COMPLETED",
//     "currency": "ARS",
//     "amount": 1000
// }
  return (

    <div className="border-b bg-white border-blue-800 rounded-3xl py-4 px-3">
       <div className="text-sm text-gray-700 mb-2">{formattedDateWithMonthName}</div>
      <div className="flex items-start justify-between">
        <div className="flex items-start gap-3">
          <div className="w-8 h-8  rounded-full flex items-center justify-center">

            <span className="text-xl"></span>
          </div>
          <div>
            <p className="font-medium">{data.destinationAccount.owner}</p>
            <p className="text-sm text-Gray">{data.type}</p>
          </div>
        </div>
        <div className="text-right">
          <p className="font-medium">${data.amount}</p>
          <p className="text-sm text-Gray">{formattedTimeWithoutSeconds}</p>
        </div>
      </div> 
    </div>
  )
}

export default ActivityItem;