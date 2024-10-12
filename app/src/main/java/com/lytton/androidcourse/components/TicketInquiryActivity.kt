package com.lytton.androidcourse.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lytton.androidcourse.R
import com.lytton.androidcourse.base.activity.BaseViewModelActivity
import com.lytton.androidcourse.databinding.ActivityTicketInquiryBinding
import com.lytton.androidcourse.entity.TicketInfo

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
        
        ticketInfoList = mutableListOf(
            TicketInfo("杭州东", "宁波"),
            TicketInfo("杭州东", "宁波"),
            TicketInfo("杭州东", "宁波"),
            TicketInfo("杭州东", "宁波"),
            TicketInfo("杭州东", "宁波"),
            TicketInfo("杭州东", "宁波"),
        )
        
        binding.ticketInfoList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.ticketInfoList.adapter = TicketInfoListAdapter(ticketInfoList)
    }
    
    
}