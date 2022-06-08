package com.nabil.flowery.ui.fragments

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.nabil.flowery.databinding.FragmentNotificationFunctionBinding
import com.nabil.flowery.receiver.AlarmReceiver
import com.nabil.flowery.receiver.channelID
import com.nabil.flowery.receiver.notificationID
import java.util.*

class NotificationFunctionFragment : Fragment() {
    private lateinit var binding:FragmentNotificationFunctionBinding
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private lateinit var button: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNotificationFunctionBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timePicker = binding.TimePicker
        datePicker = binding.DatePicker
        button = binding.setAlarmButton

        createNotificationChannel()
        button.setOnClickListener{
            setNotification()
            changeFragment(backToParentFragment)
        }


    }

    private val backToParentFragment by lazy {
        NotificationFragment()
    }

    private fun changeFragment(fragment:Fragment){
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(com.nabil.flowery.R.id.fragment_container, fragment)
        transaction.commit()
    }

    private fun getTime(): Long{
        val minute = timePicker.minute
        val hour = timePicker.hour
        val day = datePicker.dayOfMonth
        val month = datePicker.month
        val year = datePicker.year
        val calendar: Calendar = Calendar.getInstance()

        calendar.set(minute,hour,day,month,year)
        return calendar.timeInMillis
    }

    private fun createNotificationChannel(){
        val name = "desc"
        val desc = "Desc"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID,name,importance)
        channel.description = desc
        val notificationManager = activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun setNotification(){


        val intent = Intent(context, AlarmReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationID,intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )

    }


}