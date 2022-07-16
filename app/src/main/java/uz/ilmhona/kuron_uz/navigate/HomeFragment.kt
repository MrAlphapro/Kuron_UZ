package uz.ilmhona.kuron_uz.navigate

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import uz.ilmhona.kuron_uz.R
import uz.ilmhona.kuron_uz.adapter.MyAdapter
import uz.ilmhona.kuron_uz.arrays.ListArabi
import uz.ilmhona.kuron_uz.arrays.ListKirili
import uz.ilmhona.kuron_uz.arrays.ListTarjuma
import uz.ilmhona.kuron_uz.databinding.FragmentHomeBinding
import uz.ilmhona.kuron_uz.model.KuronModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var modelList: MutableList<KuronModel>
    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    private var oyat = 0
    private var position = 0
    private var positionScroll = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        requireActivity().title = resources.getString(R.string.app_name)

        tvMavqeiOkhiron = binding.tvMavqeiOkhiron

        modelList = ArrayList()

        sharedPreferences = requireContext().getSharedPreferences("SAMPLE_PREFERENCE",
            MODE_PRIVATE
        )
        editor = sharedPreferences?.edit()
        oyat = sharedPreferences!!.getInt("POSITION", 0)
        position = sharedPreferences!!.getInt("POSITION_ITEM", 0)
        positionScroll = sharedPreferences!!.getInt("POSITION_SCROLL", 0)
        val name = sharedPreferences!!.getString("TEXT", "")
        Log.d("TestText", "onCreateView: $name")

        if (name!!.isNotEmpty()) {
            binding.LL1.visibility = View.VISIBLE
            val i = if (oyat == 0) 1
            else oyat
            binding.tvMavqeiOkhiron.text = "$name сураси $i-оят"
        }

        binding.LL1.setOnClickListener {
            setOnClick(position, view)
        }


        for (i in resources.getStringArray(R.array.nameSyra).indices){
            modelList.add(KuronModel((i + 1), resources.getStringArray(R.array.nameSyra)[i], resources.getStringArray(R.array.nameSuraArabi)[i], resources.getStringArray(R.array.oyatSity)[i]))
        }

        val adapter = MyAdapter(requireContext(), R.layout.list_item, modelList)
//        adapter.notifyDataSetChanged()
        binding.apply {
            list.adapter = adapter
            list.setSelection(positionScroll)
            list.setOnScrollListener(object : AbsListView.OnScrollListener{
                private var lastFirstVisibleItem = 0
                override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {}
                override fun onScroll(
                    view: AbsListView,
                    firstVisibleItem: Int,
                    visibleItemCount: Int,
                    totalItemCount: Int
                )
                {
                    if (lastFirstVisibleItem < firstVisibleItem) {
                        positionScroll = firstVisibleItem
                    }
                    if (lastFirstVisibleItem > firstVisibleItem) {
                        positionScroll = firstVisibleItem
                    }
                    lastFirstVisibleItem = firstVisibleItem
                }

            })
        }


        return view
    }

    companion object{
        @SuppressLint("StaticFieldLeak")
        var tvMavqeiOkhiron: TextView? = null
    }

    override fun onPause() {
        super.onPause()
        editor?.putInt("POSITION_SCROLL", positionScroll)
        editor?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        editor?.putInt("POSITION_SCROLL", positionScroll)
        editor?.commit()
    }

    private fun setOnClick (i: Int, view: View){
        var action: NavDirections? = null

        when(i){
            0 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(
                    resources.getStringArray(R.array.nameSyra)[i],
                    ListArabi.fotiha,
                    ListKirili.fotihaT,
                    ListTarjuma.fotihaP,
                    i,
                )
            }
            1 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(
                    resources.getStringArray(R.array.nameSyra)[i],
                    ListArabi.baqara,
                    ListKirili.baqaraT,
                    ListTarjuma.baqaraP,
                    i,
                )
            }
            2 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(
                    resources.getStringArray(R.array.nameSyra)[i],
                    ListArabi.imron,
                    ListKirili.imronT,
                    ListTarjuma.imronP,
                    i,
                )
            }
            3 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                    ListArabi.niso,
                    ListKirili.nisoT,
                    ListTarjuma.nisoP, i,)
            }
            4 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                    ListArabi.moida,
                    ListKirili.moidaT,
                    ListTarjuma.moidaP, i,)
            }
            5 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                    ListArabi.anom,
                    ListKirili.anomT,
                    ListTarjuma.anomP, i,)
            }
            6 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                    ListArabi.arof,
                    ListKirili.arofT,
                    ListTarjuma.arofP, i,)
            }
            7 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                    ListArabi.anfol,
                    ListKirili.anfolT,
                    ListTarjuma.anfolP, i,)
            }
            8 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                    ListArabi.tavba,
                    ListKirili.tavbaT,
                    ListTarjuma.tavbaP, i,)
            }
            9 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                    ListArabi.yunus,
                    ListKirili.yunusT,
                    ListTarjuma.yunusP, i,)
            }
            10 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                    ListArabi.hud,
                    ListKirili.hudT,
                    ListTarjuma.hudP, i,)
            }
            11 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.yusuf,
                ListKirili.yusufT,
                ListTarjuma.yusufP, i,)}
            12 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.raad,
                ListKirili.raadT,
                ListTarjuma.raadP, i,)}
            13 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.ibrohim,
                ListKirili.ibrohimT,
                ListTarjuma.ibrohimP, i,)}
            14 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.hijr,
                ListKirili.hijrT,
                ListTarjuma.hijrP, i,)}
            15 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.nahl,
                ListKirili.nahlT,
                ListTarjuma.nahlP, i,)}
            16 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.isro,
                ListKirili.isroT,
                ListTarjuma.isroP, i,)}
            17 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.qahf,
                ListKirili.qahfT,
                ListTarjuma.qahfP, i,)}
            18 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.maryam,
                ListKirili.maryamT,
                ListTarjuma.maryamP, i,)}
            19 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.toho,
                ListKirili.tohoT,
                ListTarjuma.tohoP, i,)}
            20 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.anbiyo,
                ListKirili.anbiyoT,
                ListTarjuma.anbiyoP, i,)}
            21 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.haj,
                ListKirili.hajT,
                ListTarjuma.hajP, i,)}
            22 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.muminun,
                ListKirili.muminunT,
                ListTarjuma.muminunP, i,)}
            23 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.nur,
                ListKirili.nurT,
                ListTarjuma.nurP, i,)}
            24 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.furqon,
                ListKirili.furqonT,
                ListTarjuma.furqonP, i,)}
            25 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.shuaro,
                ListKirili.shuaroT,
                ListTarjuma.shuaroP, i,)}
            26 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.naml,
                ListKirili.namlT,
                ListTarjuma.namlP, i,)}
            27 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.qasas,
                ListKirili.qasasT,
                ListTarjuma.qasasP, i,)}
            28 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.ankabut,
                ListKirili.ankabutT,
                ListTarjuma.ankabutP, i,)}
            29 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.rum,
                ListKirili.rumT,
                ListTarjuma.rumP, i,)}
            30 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.luqmon,
                ListKirili.luqmonT,
                ListTarjuma.luqmonP, i,)}
            31 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.sajda,
                ListKirili.sajdaT,
                ListTarjuma.sajdaP, i,)}
            32 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.azhob,
                ListKirili.azhobT,
                ListTarjuma.azhobP, i,)}
            33 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.sabo,
                ListKirili.saboT,
                ListTarjuma.saboP, i,)}
            34 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.fotir,
                ListKirili.fotirT,
                ListTarjuma.fotirP, i,)}
            35 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.yasin,
                ListKirili.yasinT,
                ListTarjuma.yasinP, i,)}
            36 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.soffot,
                ListKirili.soffotT,
                ListTarjuma.soffotP, i,)}
            37 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.sod,
                ListKirili.sodT,
                ListTarjuma.sodP, i,)}
            38 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.zumar,
                ListKirili.zumarT,
                ListTarjuma.zumarP, i,)}
            39 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.gofir,
                ListKirili.gofirT,
                ListTarjuma.gofirP, i,)}
            40 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.fusilat,
                ListKirili.fusilatT,
                ListTarjuma.fusilatP, i,)}
            41 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.shuro,
                ListKirili.shuroT,
                ListTarjuma.shuroP, i,)}
            42 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.zukhruf,
                ListKirili.zukhrufT,
                ListTarjuma.zukhrufP, i,)}
            43 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.dukhon,
                ListKirili.dukhonT,
                ListTarjuma.dukhonP, i,)}
            44 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.josiya,
                ListKirili.josiyaT,
                ListTarjuma.josiyaP, i,)}
            45 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.ahqof,
                ListKirili.ahqofT,
                ListTarjuma.ahqofP, i,)}
            46 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.muhammad,
                ListKirili.muhammadT,
                ListTarjuma.muhammadP, i,)}
            47 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.fath,
                ListKirili.fathT,
                ListTarjuma.fathP, i,)}
            48 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.hujurat,
                ListKirili.hujuratT,
                ListTarjuma.hujuratP, i,)}
            49 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.qof,
                ListKirili.qofT,
                ListTarjuma.qofP, i,)}
            50 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.zoriyot,
                ListKirili.zoriyotT,
                ListTarjuma.zoriyotP, i,)}
            51 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.tur,
                ListKirili.turT,
                ListTarjuma.turP, i,)}
            52 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.najm,
                ListKirili.najmT,
                ListTarjuma.najmP, i,)}
            53 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.qamar,
                ListKirili.qamarT,
                ListTarjuma.qamarP, i,)}
            54 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.rahmon,
                ListKirili.rahmonT,
                ListTarjuma.rahmonP, i,)}
            55 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.voqea,
                ListKirili.voqeaT,
                ListTarjuma.voqeaP, i,)}
            56 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.hadid,
                ListKirili.hadidT,
                ListTarjuma.hadidP, i,)}
            57 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.mujodala,
                ListKirili.mujodalaT,
                ListTarjuma.mujodalaP, i,)}
            58 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.hashr,
                ListKirili.hashrT,
                ListTarjuma.hashrP, i,)}
            59 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.mumtahana,
                ListKirili.mumtahanaT,
                ListTarjuma.mumtahanaP, i,)}
            60 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.saff,
                ListKirili.saffT,
                ListTarjuma.saffP, i,)}
            61 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.juma,
                ListKirili.jumaT,
                ListTarjuma.jumaP, i,)}
            62 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.munofiqun,
                ListKirili.munofiqunT,
                ListTarjuma.munofiqunP, i,)}
            63 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.tagobun,
                ListKirili.tagobunT,
                ListTarjuma.tagobunP, i,)}
            64 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.taloq,
                ListKirili.taloqT,
                ListTarjuma.taloqP, i,)}
            65 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.tahrim,
                ListKirili.tahrimT,
                ListTarjuma.tahrimP, i,)}
            66 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.mulk,
                ListKirili.mulkT,
                ListTarjuma.mulkP, i,)}
            67 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.qalam,
                ListKirili.qalamT,
                ListTarjuma.qalamP, i,)}
            68 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.hoqqa,
                ListKirili.hoqqaT,
                ListTarjuma.hoqqaP, i,)}
            69 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.maorij,
                ListKirili.maorijT,
                ListTarjuma.maorijP, i,)}
            70 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.nuh,
                ListKirili.nuhT,
                ListTarjuma.nuhP, i,)}
            71 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.jin,
                ListKirili.jinT,
                ListTarjuma.jinP, i,)}
            72 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.muzzammil,
                ListKirili.muzzammilT,
                ListTarjuma.muzzammilP, i,)}
            73 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.muddassir,
                ListKirili.muddassirT,
                ListTarjuma.muddassirP, i,)}
            74 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.qiyomat,
                ListKirili.qiyomatT,
                ListTarjuma.qiyomatP, i,)}
            75 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.inson,
                ListKirili.insonT,
                ListTarjuma.insonP, i,)}
            76 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.mursalot,
                ListKirili.mursalotT,
                ListTarjuma.mursalotP, i,)}
            77 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.naba,
                ListKirili.nabaT,
                ListTarjuma.nabaP, i,)}
            78 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.noziot,
                ListKirili.noziotT,
                ListTarjuma.noziotP, i,)}
            79 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.abasa,
                ListKirili.abasaT,
                ListTarjuma.abasaP, i,)}
            80 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.takvir,
                ListKirili.takvirT,
                ListTarjuma.takvirP, i,)}
            81 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.infitor,
                ListKirili.infitorT,
                ListTarjuma.infitorP, i,)}
            82 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.mutaffifin,
                ListKirili.mutaffifinT,
                ListTarjuma.mutaffifinP, i,)}
            83 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.inshiqoq,
                ListKirili.inshiqoqT,
                ListTarjuma.inshiqoqP, i,)}
            84 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.buruj,
                ListKirili.burujT,
                ListTarjuma.burujP, i,)}
            85 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.toriq,
                ListKirili.toriqT,
                ListTarjuma.toriqP, i,)}
            86 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.alo,
                ListKirili.aloT,
                ListTarjuma.aloP, i,)}
            87 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.goshia,
                ListKirili.goshiaT,
                ListTarjuma.goshiaP, i,)}
            88 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.fajr,
                ListKirili.fajrT,
                ListTarjuma.fajrP, i,)}
            89 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.balad,
                ListKirili.baladT,
                ListTarjuma.baladP, i,)}
            90 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.shams,
                ListKirili.shamsT,
                ListTarjuma.shamsP, i,)}
            91 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.layl,
                ListKirili.laylT,
                ListTarjuma.laylP, i,)}
            92 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.zukho,
                ListKirili.zukhoT,
                ListTarjuma.zukhoP, i,)}
            93 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.sharh,
                ListKirili.sharhT,
                ListTarjuma.sharhP, i,)}
            94 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.tin,
                ListKirili.tinT,
                ListTarjuma.tinP, i,)}
            95 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.alaq,
                ListKirili.alaqT,
                ListTarjuma.alaqP, i,)}
            96 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.qadr,
                ListKirili.qadrT,
                ListTarjuma.qadrP, i,)}
            97 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.bayyina,
                ListKirili.bayyinaT,
                ListTarjuma.bayyinaP, i,)}
            98 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.zalzala,
                ListKirili.zalzalaT,
                ListTarjuma.zalzalaP, i,)}
            99 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.odiyot,
                ListKirili.odiyotT,
                ListTarjuma.odiyotP, i,)}
            100 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.qoria,
                ListKirili.qoriaT,
                ListTarjuma.qoriaP, i,)}
            101 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.takosur,
                ListKirili.takosurT,
                ListTarjuma.takosurP, i,)}
            102 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.asr,
                ListKirili.asrT,
                ListTarjuma.asrP, i,)}
            103 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.humazah,
                ListKirili.humazahT,
                ListTarjuma.humazahP, i,)}
            104 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.fil,
                ListKirili.filT,
                ListTarjuma.filP, i,)}
            105 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.quraysh,
                ListKirili.qurayshT,
                ListTarjuma.qurayshP, i,)}
            106 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.moun,
                ListKirili.mounT,
                ListTarjuma.mounP, i,)}
            107 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.kavsar,
                ListKirili.kavsarT,
                ListTarjuma.kavsarP, i,)}
            108 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.kofirun,
                ListKirili.kofirunT,
                ListTarjuma.kofirunP, i,)}
            109 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.nasr,
                ListKirili.nasrT,
                ListTarjuma.nasrP, i,)}
            110 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.masad,
                ListKirili.masadT,
                ListTarjuma.masadP, i,)}
            111 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.ikhlos,
                ListKirili.ikhlosT,
                ListTarjuma.ikhlosP, i,)}
            112 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.falaq,
                ListKirili.falaqT,
                ListTarjuma.falaqP, i,)}
            113 -> {action = HomeFragmentDirections.navigateToOyatFragment(resources.getStringArray(R.array.nameSyra)[i],
                ListArabi.nos,
                ListKirili.nosT,
                ListTarjuma.nosP, i,)}

        }

        Navigation.findNavController(view).navigate(action!!)
    }

}