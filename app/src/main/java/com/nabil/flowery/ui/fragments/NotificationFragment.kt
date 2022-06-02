package com.nabil.flowery.ui.fragments



import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nabil.flowery.AlarmReceiver
import com.nabil.flowery.databinding.FragmentNotificationBinding
import java.util.*


class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private lateinit var timePicker: TimePicker
    private lateinit var button: Button



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timePicker = binding.TimePicker
        button = binding.s
        val calendar: Calendar = Calendar.getInstance()
        timePicker.currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        timePicker.currentMinute = calendar.get(Calendar.MINUTE)
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val cal: Calendar = Calendar.getInstance()
                cal.set(timePicker.currentHour, timePicker.currentMinute, 0)
                setAlarm(cal)
                Log.e("a",cal.toString())
            }

            private fun setAlarm(targetcal: Calendar) {
                Toast.makeText(context, "Alarm is set", Toast.LENGTH_LONG).show()
                val intent = Intent(context!!.applicationContext, AlarmReceiver::class.java)
                val pendingIntent = PendingIntent.getBroadcast(context!!.applicationContext, 0, intent, 0)
                val alarmmanager = activity!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                alarmmanager[AlarmManager.RTC_WAKEUP, targetcal.getTimeInMillis()] = pendingIntent
            }
        })

    }




}