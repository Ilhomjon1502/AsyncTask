package Adapters

import Entity.User
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilhomjon.asynctask.databinding.ItemRvBinding

class UserAdapter(val list: List<User>):RecyclerView.Adapter<UserAdapter.Vh>() {

    inner class Vh(var itemRv:ItemRvBinding):RecyclerView.ViewHolder(itemRv.root){

        fun onBind(user: User, position: Int){
            itemRv.tv1.text = user.userName
            itemRv.tv2.text = user.password
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}