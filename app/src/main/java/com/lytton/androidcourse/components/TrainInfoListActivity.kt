package com.lytton.androidcourse.components

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
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
    class TrainInfoListAdapter(private val trainInfoList: List<TrainInfo>,
                               private val listener: OnTrainClickListener):
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

            holder.itemView.setOnClickListener {
                listener.onTrainClick(currentTrain)
            }

            Log.d("TrainInfoListAdapter", "Binding train: ${currentTrain.name}") // 添加日志
        }

        override fun getItemCount(): Int {
            return trainInfoList.size
        }
    }

    /**
     * 车次信息点击接口
     */
    interface OnTrainClickListener {
        fun onTrainClick(trainInfo: TrainInfo)
    }

    //列车信息列表
    private lateinit var trainInfoList: MutableList<TrainInfo>
    //列车信息列表适配器
    private lateinit var trainInfoListAdapter: TrainInfoListAdapter
    //编辑窗口启动器(修改列车信息)
    private val editTrainInfoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { data ->
                    val updatedTrainInfo = data.getParcelableExtra<TrainInfo>("updatedTrainInfo")
                    updatedTrainInfo?.let {
                        // 更新列车信息列表
                        val position = trainInfoList.indexOfFirst { it.name == updatedTrainInfo.name }
                        if (position != -1) {
                            trainInfoList[position] = updatedTrainInfo
                            trainInfoListAdapter.notifyItemChanged(position)
                        }
                    }
                }
            }
        }
    //编辑窗口启动器(新增列车信息)
    private val addTrainLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { data ->
                    val newTrainInfo = data.getParcelableExtra<TrainInfo>("updatedTrainInfo")
                    newTrainInfo?.let { trainInfo ->
                        // 将新的车次信息添加到列表
                        trainInfoList.add(trainInfo)
                        // 通知适配器更新列表
                        trainInfoListAdapter.notifyItemInserted(trainInfoList.size - 1)
                    }
                }
            }
        }
    

    /**
     * 创建菜单项
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu) //加载菜单资源
        return true
    }

    /**
     * 菜单项的点击回调
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                // 点击添加选项时，启动添加车次信息的窗口
                val intent = Intent(this, EditTrainInfoActivity::class.java)
                addTrainLauncher.launch(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * 创建钩子
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 初始化 Toolbar
        setSupportActionBar(binding.toolbar) // 确保 binding.toolbar 是您在布局中定义的 Toolbar
    }


    /**
     * 初始化数据
     */
    override fun initDatum() {
        super.initDatum()

        trainInfoList = mutableListOf(
            TrainInfo("G11111", "孟买", "迪拜", 888.0),
            TrainInfo("G22222", "南极", "北极", 1899.0),
            TrainInfo("G33333", "东京", "纽约", 200.0),
            TrainInfo("G44444", "开罗", "墨西哥", 8988.0),
            TrainInfo("G55555", "悉尼", "冰岛", 888.0),
            TrainInfo("G11111", "新西兰", "瑞士", 128.0),
            TrainInfo("G22222", "伦敦", "巴黎", 8818.0),
            TrainInfo("G33333", "华盛顿", "苏丹", 888.0),
            TrainInfo("G44444", "洛杉矶", "阿拉斯加", 828.0),
            TrainInfo("G55555", "牙买加", "摩洛哥", 188.0),
        )

        binding.trainInfoList.layoutManager = LinearLayoutManager(this)
        trainInfoListAdapter = TrainInfoListAdapter(trainInfoList, object : OnTrainClickListener {
            //当点击列车信息项时将当前的列车信息放入到intent通信对象中用于传输
            override fun onTrainClick(trainInfo: TrainInfo) {
                //创建intent通信对象
                val intent = Intent(this@TrainInfoListActivity, EditTrainInfoActivity::class.java).apply {
                    putExtra("trainInfo", trainInfo)
                }
                //使用启动器启动编辑窗口
                editTrainInfoLauncher.launch(intent)
            }
        })
        binding.trainInfoList.adapter = trainInfoListAdapter

    }
    
}