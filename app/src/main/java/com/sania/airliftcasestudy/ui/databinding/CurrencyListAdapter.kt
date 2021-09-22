import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sania.airliftcasestudy.R
import com.sania.airliftcasestudy.models.ProductModel
import com.sania.airliftcasestudy.ui.utility.Helper
import kotlinx.android.synthetic.main.row_product.view.*

class CurrencyListAdapter(
    data: List<ProductModel>
) : RecyclerView.Adapter<CurrencyListAdapter.CustomViewHolder>() {
    var mActivityList = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyListAdapter.CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val holder: RecyclerView.ViewHolder?
        val itemView = inflater.inflate(R.layout.row_product, parent, false)
        holder = CustomViewHolder(itemView)

        return holder
    }

    override fun onBindViewHolder(holder: CurrencyListAdapter.CustomViewHolder, position: Int) {
        holder.setData(mActivityList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.row_product
    }

    override fun getItemCount(): Int {
        return mActivityList.size
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(data: ProductModel) {
            itemView.tvCryptoName.text = data.crypto
            itemView.tvCurrencyUnit.text = data.target
            itemView.tvCurrencyValue.text = data.amount.toInt().toString()
            itemView.tvCryptoVal.text = data.inputAmount.toString()
            itemView.ivCrypto.setImageResource(Helper.getCryptoIcon(data.crypto.toString()))
        }
    }
}