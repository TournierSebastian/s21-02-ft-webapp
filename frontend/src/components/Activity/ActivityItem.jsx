
function ActivityItem({ date, username, reason, amount, time, icon }) {
  return (

    <div className="border-b bg-white border-blue-800 rounded-3xl py-4 px-3">
      <div className="text-sm text-gray-700 mb-2">{date}</div>
      <div className="flex items-start justify-between">
        <div className="flex items-start gap-3">
          <div className="w-8 h-8  rounded-full flex items-center justify-center">

            <span className="text-xl">{icon}</span>
          </div>
          <div>
            <p className="font-medium">{username}</p>
            <p className="text-sm text-Gray">{reason}</p>
          </div>
        </div>
        <div className="text-right">
          <p className="font-medium">${amount}</p>
          <p className="text-sm text-Gray">{time}</p>
        </div>
      </div>
    </div>
  )
}

export default ActivityItem;