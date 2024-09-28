package com.lytton.androidcourse.components

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lytton.androidcourse.R
import com.lytton.androidcourse.base.activity.BaseViewModelActivity
import com.lytton.androidcourse.databinding.ActivityTrainInfoListBinding
import com.lytton.androidcourse.entity.TrainInfo

class TrainInfoListActivity : BaseViewModelActivity<ActivityTrainInfoListBinding>() {

    /**
     * 列车信息列表适配器
     */
    class TrainInfoListAdapter(private val trainInfoList: List<TrainInfo>):
        RecyclerView.Adapter<TrainInfoListAdapter.TrainViewHolder>() {

        class TrainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val name: TextView = itemView.findViewById(R.id.train_name)
            val start: TextView = itemView.findViewById(R.id.train_start)
            val end: TextView = itemView.findViewById(R.id.train_end)
            val price: TextView = itemView.findViewById(R.id.train_price)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
            val itemView = LayoutInflater.from(parent.context).
            inflate(R.layout.activity_train_info_item, parent, false)

            return TrainViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
            val currentTrain = trainInfoList[position]
            holder.name.text = currentTrain.name
            holder.start.text = currentTrain.start
            holder.end.text = currentTrain.end
            holder.price.text = currentTrain.price.toString()

            Log.d("TrainInfoListAdapter", "Binding train: ${currentTrain.name}") // 添加日志
        }

        override fun getItemCount(): Int {
            return trainInfoList.size
        }
    }

    //列车信息列表
    private lateinit var trainInfoList: List<TrainInfo>
    //列车信息列表适配器
    private lateinit var trainInfoListAdapter: TrainInfoListAdapter

    /**
     * 初始化数据
     */
    override fun initDatum() {
        super.initDatum()

        trainInfoList = listOf(
            TrainInfo("G11111", "孟买", "迪拜", 888888.0),
            TrainInfo("G22222", "南极", "北极", 18799999.0),
            TrainInfo("G33333", "东京", "纽约", 2000000.0),
            TrainInfo("G44444", "开罗", "墨西哥", 8909988.0),
            TrainInfo("G55555", "悉尼", "冰岛", 8232288.0),
            TrainInfo("G11111", "新西兰", "瑞士", 1293188.0),
            TrainInfo("G22222", "伦敦", "巴黎", 88123418.0),
            TrainInfo("G33333", "华盛顿", "苏丹", 8899988.0),
            TrainInfo("G44444", "洛杉矶", "阿拉斯加", 8234188.0),
            TrainInfo("G55555", "牙买加", "摩洛哥", 1321888.0),
        )

        binding.trainInfoList.layoutManager = LinearLayoutManager(this)
        trainInfoListAdapter = TrainInfoListAdapter(trainInfoList)
        binding.trainInfoList.adapter = trainInfoListAdapter

    }
    
}