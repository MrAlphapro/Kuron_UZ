package uz.ilmhona.kuron_uz.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import uz.ilmhona.kuron_uz.Model
import uz.ilmhona.kuron_uz.R

class MyListAdapter(var mCtx: Context, var resource: Int, var items: MutableList<Model>, var size: Int, var size2: Int) :
    ArrayAdapter<Model>(mCtx, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        val relative: ConstraintLayout = view.findViewById(R.id.relative)
        val tvArabi: TextView = view.findViewById(R.id.tvArabi)
        val tvKrili: TextView = view.findViewById(R.id.tvKrili)
        val tvTarjuma: TextView = view.findViewById(R.id.tvTarjuma)
        val tvKriliNamber: TextView = view.findViewById(R.id.tvKriliNamber)
        val tvTarjumaNamber: TextView = view.findViewById(R.id.tvTarjumaNamber)
        val tvArabiNamber: TextView = view.findViewById(R.id.imiya_raqam)
        val tvArabiIcon: TextView = view.findViewById(R.id.tvArabiIcon)
        val share: ImageView = view.findViewById(R.id.share)

        tvArabi.textSize = (18 + size).toFloat()
        tvArabiIcon.textSize = (18 + size).toFloat()
        tvKrili.textSize = (14 + size2).toFloat()
        tvKriliNamber.textSize = (14 + size2).toFloat()
        tvTarjuma.textSize = (14 + size2).toFloat()
        tvTarjumaNamber.textSize = (14 + size2).toFloat()


        val person: Model = items[position]

        if (person.int != 0) {
            tvKriliNamber.text = "${person.int}. "
            tvTarjumaNamber.text = "${person.int}. "
        } else {
            tvKriliNamber.visibility = View.GONE
            tvTarjumaNamber.visibility = View.GONE
        }
        tvArabiNamber.text = "${PersianToEnglish(person.int.toString())}"
//        imageView.setImageDrawable(mCtx.resources.getDrawable(person.photo))
        tvArabi.text = "${person.arabi}"
        tvKrili.text = person.krili
        tvTarjuma.text = person.tarjuma

        share.setOnClickListener {
//            val clipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
//            val clipData = ClipData.newPlainText(
//                "nonsense_data",
//                tvArabi.getText().toString()
//            )
//            clipboardManager.setPrimaryClip(clipData)

            //Get text from TextView and store in variable "s"
            val s = "Сураи ${person.name} ояти ${person.int} \n\n${tvArabi.text} \n\n${tvKrili.text} \n\n${tvTarjuma.text}"
            //Intent to share the text
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, s)
            context.startActivity(Intent.createChooser(shareIntent,"Share via"))

        }

        if (position % 2 != 0) relative.setBackgroundResource(R.color.white)
        else relative.setBackgroundResource(R.color.whiteF6)


        return view
    }

    private fun PersianToEnglish(persianStr: String): String {
        var result = ""
        var en = '0'
        for (ch in persianStr) {
            en = ch
            when (ch) {
                '0' -> en = '۰'
                '1' -> en = '۱'
                '2' -> en = '۲'
                '3' -> en = '۳'
                '4' -> en = '۴'
                '5' -> en = '۵'
                '6' -> en = '۶'
                '7' -> en = '۷'
                '8' -> en = '۸'
                '9' -> en = '۹'
            }
            result = "${result}$en"
        }
        return result
    }

}