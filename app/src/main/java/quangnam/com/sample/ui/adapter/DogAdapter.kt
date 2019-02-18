package quangnam.com.sample.ui.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import quangnam.com.sample.R
import quangnam.com.sample.data.network.response.test.DogResponse
import quangnam.com.sample.databinding.ItemDogBinding
import javax.inject.Inject

/**
 * Created by quangnam on 1/21/19.
 * Project Sample
 */
class DogAdapter
@Inject constructor() : RecyclerView.Adapter<DogAdapter.ViewHolder>() {
    var data: List<DogResponse> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_dog, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {
        viewHolder.bind()
        data.get(index).value?.let { viewHolder.setData(it) }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unBind()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var binding: ItemDogBinding? = null

        fun setData(itemData: String) {
            if (binding != null) {
                binding!!.data = itemData
            }
        }

        fun bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView)
            }
        }

        fun unBind() {
            if (binding != null) {
                binding!!.unbind()
            }
        }
    }
}