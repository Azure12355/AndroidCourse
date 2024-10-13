package com.lytton.androidcourse.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.lytton.androidcourse.R
import com.lytton.androidcourse.base.activity.BaseViewModelActivity
import com.lytton.androidcourse.databinding.ActivityTicketInquiryBinding
import com.lytton.androidcourse.entity.TicketInfo
import java.io.File
import java.io.FileWriter

class TicketInquiryActivity : BaseViewModelActivity<ActivityTicketInquiryBinding>() {
    /**
     * 车票查询记录列表适配器
     */
    class TicketInfoListAdapter(private val ticketInfoList: List<TicketInfo>):
            RecyclerView.Adapter<TicketInfoListAdapter.TicketInfoViewHolder>() {
                
        class TicketInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val startingStation: TextView = itemView.findViewById(R.id.starting_station)
            val terminal: TextView = itemView.findViewById(R.id.terminal)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketInfoViewHolder {
            val itemView = LayoutInflater.from(parent.context).
                    inflate(R.layout.activity_ticket_info, parent, false)
            
            return TicketInfoViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return ticketInfoList.size
        }

        override fun onBindViewHolder(holder: TicketInfoViewHolder, position: Int) {
            val currentTicketInfo = ticketInfoList[position]
            holder.startingStation.text = currentTicketInfo.startingStation
            holder.terminal.text = currentTicketInfo.terminal
        }


    }
    
    //车票信息列表
    private lateinit var ticketInfoList: MutableList<TicketInfo>
    //车票信息列表适配器
    private lateinit var ticketInfoListAdapter: TicketInfoListAdapter

    override fun initDatum() {
        super.initDatum()
        
        /*ticketInfoList = mutableListOf(
            TicketInfo("杭州东", "宁波"),
            TicketInfo("杭州东", "宁波"),
            TicketInfo("杭州东", "宁波"),
            TicketInfo("杭州东", "宁波"),
            TicketInfo("杭州东", "宁波"),
            TicketInfo("杭州东", "宁波"),
        )*/
        
        ticketInfoList = mutableListOf()
        
        
        binding.ticketInfoList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        ticketInfoListAdapter = TicketInfoListAdapter(ticketInfoList)
        binding.ticketInfoList.adapter = ticketInfoListAdapter

        //从文件中加载车票信息
        loadTicketInfoFromFile()
        
        binding.ticketInquiry.setOnClickListener {
            saveCurrentTicketInfo()
        }
        
        binding.clearHistory.setOnClickListener {
            clearTicketInfoFile()
        }
    }


    /**
     * 从文件中加载车票信息
     */
    private fun loadTicketInfoFromFile() {
        val file = File(getExternalFilesDir(null), "ticket_info.json")
        if (file.exists()) {
            val content = file.readText()
            val gson = Gson()
            val listType = object : TypeToken<List<TicketInfo>>() {}.type
            ticketInfoList = gson.fromJson<MutableList<TicketInfo>?>(content, listType)?.toMutableList() ?: mutableListOf()

            // 更新适配器
            ticketInfoListAdapter = TicketInfoListAdapter(ticketInfoList)
            binding.ticketInfoList.adapter = ticketInfoListAdapter
            ticketInfoListAdapter.notifyDataSetChanged() // Notify adapter of data change

            // 显示第一个车票信息
            if (ticketInfoList.isNotEmpty()) {
                val firstTicket = ticketInfoList[0]
                binding.startingStation.setText(firstTicket.startingStation)
                binding.terminal.setText(firstTicket.terminal)
            }
        }
    }


    /**
     * 保存车票信息
     */
    private fun saveCurrentTicketInfo() {
        val startingStation = binding.startingStation.text.toString()
        val terminal = binding.terminal.text.toString()
        val newTicketInfo = TicketInfo(startingStation, terminal)

        ticketInfoList.add(newTicketInfo) // Add new ticket info to the list
        ticketInfoListAdapter.notifyItemInserted(ticketInfoList.size - 1)

        val gson = Gson()
        val json = gson.toJson(ticketInfoList)
        val file = File(getExternalFilesDir(null), "ticket_info.json")

        try {
            FileWriter(file).use { writer -> writer.write(json) }
            Toast.makeText(this, "车票信息已保存", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 删除车票信息
     */
    private fun clearTicketInfoFile() {
        val file = File(getExternalFilesDir(null), "ticket_info.json")
        if (file.exists()) {
            file.writeText("") // Clear the file
            ticketInfoList.clear() // Clear the list
            ticketInfoListAdapter.notifyDataSetChanged() // Notify adapter of data change
            Toast.makeText(this, "历史记录已清除", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "没有历史记录", Toast.LENGTH_SHORT).show()
        }
    }
    
}