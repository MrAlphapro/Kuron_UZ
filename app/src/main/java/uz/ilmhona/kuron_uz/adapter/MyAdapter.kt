package uz.ilmhona.kuron_uz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import uz.ilmhona.kuron_uz.R
import uz.ilmhona.kuron_uz.arrays.ListArabi
import uz.ilmhona.kuron_uz.arrays.ListKirili
import uz.ilmhona.kuron_uz.arrays.ListTarjuma
import uz.ilmhona.kuron_uz.model.KuronModel
import uz.ilmhona.kuron_uz.navigate.HomeFragmentDirections

class MyAdapter(var mCtx: Context, var resource: Int, var items: MutableList<KuronModel>) :
    ArrayAdapter<KuronModel>(mCtx, resource, items) {

    override fun getView(i: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        val relative: RelativeLayout = view.findViewById(R.id.relative)
        val number: TextView = view.findViewById(R.id.number)
        val nameK: TextView = view.findViewById(R.id.tvNameK)
        val tvOyat: TextView = view.findViewById(R.id.tvOyat)
        val nameA: TextView = view.findViewById(R.id.tvNameA)

        val model: KuronModel = items[i]

        number.text = "${model.int}."
        nameK.text = model.nameK
        tvOyat.text = model.oyat
        nameA.text = model.nameA

        relative.setOnClickListener{
           setOnClick(i, view)
        }

        return view
    }

    private fun setOnClick (i: Int, view: View){
        val model: KuronModel = items[i]
        var action: NavDirections? = null

        when(i){
            0 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(
                    model.nameK,
                    ListArabi.fotiha,
                    ListKirili.fotihaT,
                    ListTarjuma.fotihaP,
                    i,
                )
            }
            1 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(
                    model.nameK,
                    ListArabi.baqara,
                    ListKirili.baqaraT,
                    ListTarjuma.baqaraP,
                    i,
                )
            }
            2 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(
                    model.nameK,
                    ListArabi.imron,
                    ListKirili.imronT,
                    ListTarjuma.baqaraP,
                    i,
                )
            }
            3 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                    ListArabi.niso,
                    ListKirili.nisoT,
                    ListTarjuma.nisoP, i,)
            }
            4 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                    ListArabi.moida,
                    ListKirili.moidaT,
                    ListTarjuma.moidaP, i,)
            }
            5 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                    ListArabi.anom,
                    ListKirili.anomT,
                    ListTarjuma.anomP, i,)
            }
            6 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                    ListArabi.arof,
                    ListKirili.arofT,
                    ListTarjuma.arofP, i,)
            }
            7 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                    ListArabi.anfol,
                    ListKirili.anfolT,
                    ListTarjuma.anfolP, i,)
            }
            8 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                    ListArabi.tavba,
                    ListKirili.tavbaT,
                    ListTarjuma.tavbaP, i,)
            }
            9 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                    ListArabi.yunus,
                    ListKirili.yunusT,
                    ListTarjuma.yunusP, i,)
            }
            10 -> {
                action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                    ListArabi.hud,
                    ListKirili.hudT,
                    ListTarjuma.hudP, i,)
            }
            11 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.yusuf,
                ListKirili.yusufT,
                ListTarjuma.yusufP, i,)}
            12 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.raad,
                ListKirili.raadT,
                ListTarjuma.raadP, i,)}
            13 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.ibrohim,
                ListKirili.ibrohimT,
                ListTarjuma.ibrohimP, i,)}
            14 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.hijr,
                ListKirili.hijrT,
                ListTarjuma.hijrP, i,)}
            15 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.nahl,
                ListKirili.nahlT,
                ListTarjuma.nahlP, i,)}
            16 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.isro,
                ListKirili.isroT,
                ListTarjuma.isroP, i,)}
            17 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.qahf,
                ListKirili.qahfT,
                ListTarjuma.qahfP, i,)}
            18 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.maryam,
                ListKirili.maryamT,
                ListTarjuma.maryamP, i,)}
            19 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.toho,
                ListKirili.tohoT,
                ListTarjuma.tohoP, i,)}
            20 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.anbiyo,
                ListKirili.anbiyoT,
                ListTarjuma.anbiyoP, i,)}
            21 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.haj,
                ListKirili.hajT,
                ListTarjuma.hajP, i,)}
            22 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.muminun,
                ListKirili.muminunT,
                ListTarjuma.muminunP, i,)}
            23 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.nur,
                ListKirili.nurT,
                ListTarjuma.nurP, i,)}
            24 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.furqon,
                ListKirili.furqonT,
                ListTarjuma.furqonP, i,)}
            25 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.shuaro,
                ListKirili.shuaroT,
                ListTarjuma.shuaroP, i,)}
            26 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.naml,
                ListKirili.namlT,
                ListTarjuma.namlP, i,)}
            27 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.qasas,
                ListKirili.qasasT,
                ListTarjuma.qasasP, i,)}
            28 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.ankabut,
                ListKirili.ankabutT,
                ListTarjuma.ankabutP, i,)}
            29 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.rum,
                ListKirili.rumT,
                ListTarjuma.rumP, i,)}
            30 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.luqmon,
                ListKirili.luqmonT,
                ListTarjuma.luqmonP, i,)}
            31 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.sajda,
                ListKirili.sajdaT,
                ListTarjuma.sajdaP, i,)}
            32 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.azhob,
                ListKirili.azhobT,
                ListTarjuma.azhobP, i,)}
            33 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.sabo,
                ListKirili.saboT,
                ListTarjuma.saboP, i,)}
            34 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.fotir,
                ListKirili.fotirT,
                ListTarjuma.fotirP, i,)}
            35 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.yasin,
                ListKirili.yasinT,
                ListTarjuma.yasinP, i,)}
            36 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.soffot,
                ListKirili.soffotT,
                ListTarjuma.soffotP, i,)}
            37 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.sod,
                ListKirili.sodT,
                ListTarjuma.sodP, i,)}
            38 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.zumar,
                ListKirili.zumarT,
                ListTarjuma.zumarP, i,)}
            39 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.gofir,
                ListKirili.gofirT,
                ListTarjuma.gofirP, i,)}
            40 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.fusilat,
                ListKirili.fusilatT,
                ListTarjuma.fusilatP, i,)}
            41 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.shuro,
                ListKirili.shuroT,
                ListTarjuma.shuroP, i,)}
            42 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.zukhruf,
                ListKirili.zukhrufT,
                ListTarjuma.zukhrufP, i,)}
            43 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.dukhon,
                ListKirili.dukhonT,
                ListTarjuma.dukhonP, i,)}
            44 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.josiya,
                ListKirili.josiyaT,
                ListTarjuma.josiyaP, i,)}
            45 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.ahqof,
                ListKirili.ahqofT,
                ListTarjuma.ahqofP, i,)}
            46 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.muhammad,
                ListKirili.muhammadT,
                ListTarjuma.muhammadP, i,)}
            47 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.fath,
                ListKirili.fathT,
                ListTarjuma.fathP, i,)}
            48 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.hujurat,
                ListKirili.hujuratT,
                ListTarjuma.hujuratP, i,)}
            49 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.qof,
                ListKirili.qofT,
                ListTarjuma.qofP, i,)}
            50 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.zoriyot,
                ListKirili.zoriyotT,
                ListTarjuma.zoriyotP, i,)}
            51 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.tur,
                ListKirili.turT,
                ListTarjuma.turP, i,)}
            52 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.najm,
                ListKirili.najmT,
                ListTarjuma.najmP, i,)}
            53 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.qamar,
                ListKirili.qamarT,
                ListTarjuma.qamarP, i,)}
            54 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.rahmon,
                ListKirili.rahmonT,
                ListTarjuma.rahmonP, i,)}
            55 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.voqea,
                ListKirili.voqeaT,
                ListTarjuma.voqeaP, i,)}
            56 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.hadid,
                ListKirili.hadidT,
                ListTarjuma.hadidP, i,)}
            57 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.mujodala,
                ListKirili.mujodalaT,
                ListTarjuma.mujodalaP, i,)}
            58 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.hashr,
                ListKirili.hashrT,
                ListTarjuma.hashrP, i,)}
            59 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.mumtahana,
                ListKirili.mumtahanaT,
                ListTarjuma.mumtahanaP, i,)}
            60 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.saff,
                ListKirili.saffT,
                ListTarjuma.saffP, i,)}
            61 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.juma,
                ListKirili.jumaT,
                ListTarjuma.jumaP, i,)}
            62 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.munofiqun,
                ListKirili.munofiqunT,
                ListTarjuma.munofiqunP, i,)}
            63 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.tagobun,
                ListKirili.tagobunT,
                ListTarjuma.tagobunP, i,)}
            64 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.taloq,
                ListKirili.taloqT,
                ListTarjuma.taloqP, i,)}
            65 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.tahrim,
                ListKirili.tahrimT,
                ListTarjuma.tahrimP, i,)}
            66 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.mulk,
                ListKirili.mulkT,
                ListTarjuma.mulkP, i,)}
            67 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.qalam,
                ListKirili.qalamT,
                ListTarjuma.qalamP, i,)}
            68 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.hoqqa,
                ListKirili.hoqqaT,
                ListTarjuma.hoqqaP, i,)}
            69 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.maorij,
                ListKirili.maorijT,
                ListTarjuma.maorijP, i,)}
            70 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.nuh,
                ListKirili.nuhT,
                ListTarjuma.nuhP, i,)}
            71 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.jin,
                ListKirili.jinT,
                ListTarjuma.jinP, i,)}
            72 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.muzzammil,
                ListKirili.muzzammilT,
                ListTarjuma.muzzammilP, i,)}
            73 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.muddassir,
                ListKirili.muddassirT,
                ListTarjuma.muddassirP, i,)}
            74 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.qiyomat,
                ListKirili.qiyomatT,
                ListTarjuma.qiyomatP, i,)}
            75 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.inson,
                ListKirili.insonT,
                ListTarjuma.insonP, i,)}
            76 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.mursalot,
                ListKirili.mursalotT,
                ListTarjuma.mursalotP, i,)}
            77 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.naba,
                ListKirili.nabaT,
                ListTarjuma.nabaP, i,)}
            78 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.noziot,
                ListKirili.noziotT,
                ListTarjuma.noziotP, i,)}
            79 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.abasa,
                ListKirili.abasaT,
                ListTarjuma.abasaP, i,)}
            80 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.takvir,
                ListKirili.takvirT,
                ListTarjuma.takvirP, i,)}
            81 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.infitor,
                ListKirili.infitorT,
                ListTarjuma.infitorP, i,)}
            82 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.mutaffifin,
                ListKirili.mutaffifinT,
                ListTarjuma.mutaffifinP, i,)}
            83 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.inshiqoq,
                ListKirili.inshiqoqT,
                ListTarjuma.inshiqoqP, i,)}
            84 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.buruj,
                ListKirili.burujT,
                ListTarjuma.burujP, i,)}
            85 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.toriq,
                ListKirili.toriqT,
                ListTarjuma.toriqP, i,)}
            86 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.alo,
                ListKirili.aloT,
                ListTarjuma.aloP, i,)}
            87 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.goshia,
                ListKirili.goshiaT,
                ListTarjuma.goshiaP, i,)}
            88 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.fajr,
                ListKirili.fajrT,
                ListTarjuma.fajrP, i,)}
            89 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.balad,
                ListKirili.baladT,
                ListTarjuma.baladP, i,)}
            90 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.shams,
                ListKirili.shamsT,
                ListTarjuma.shamsP, i,)}
            91 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.layl,
                ListKirili.laylT,
                ListTarjuma.laylP, i,)}
            92 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.zukho,
                ListKirili.zukhoT,
                ListTarjuma.zukhoP, i,)}
            93 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.sharh,
                ListKirili.sharhT,
                ListTarjuma.sharhP, i,)}
            94 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.tin,
                ListKirili.tinT,
                ListTarjuma.tinP, i,)}
            95 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.alaq,
                ListKirili.alaqT,
                ListTarjuma.alaqP, i,)}
            96 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.qadr,
                ListKirili.qadrT,
                ListTarjuma.qadrP, i,)}
            97 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.bayyina,
                ListKirili.bayyinaT,
                ListTarjuma.bayyinaP, i,)}
            98 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.zalzala,
                ListKirili.zalzalaT,
                ListTarjuma.zalzalaP, i,)}
            99 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.odiyot,
                ListKirili.odiyotT,
                ListTarjuma.odiyotP, i,)}
            100 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.qoria,
                ListKirili.qoriaT,
                ListTarjuma.qoriaP, i,)}
            101 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.takosur,
                ListKirili.takosurT,
                ListTarjuma.takosurP, i,)}
            102 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.asr,
                ListKirili.asrT,
                ListTarjuma.asrP, i,)}
            103 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.humazah,
                ListKirili.humazahT,
                ListTarjuma.humazahP, i,)}
            104 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.fil,
                ListKirili.filT,
                ListTarjuma.filP, i,)}
            105 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.quraysh,
                ListKirili.qurayshT,
                ListTarjuma.qurayshP, i,)}
            106 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.moun,
                ListKirili.mounT,
                ListTarjuma.mounP, i,)}
            107 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.kavsar,
                ListKirili.kavsarT,
                ListTarjuma.kavsarP, i,)}
            108 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.kofirun,
                ListKirili.kofirunT,
                ListTarjuma.kofirunP, i,)}
            109 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.nasr,
                ListKirili.nasrT,
                ListTarjuma.nasrP, i,)}
            110 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.masad,
                ListKirili.masadT,
                ListTarjuma.masadP, i,)}
            111 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.ikhlos,
                ListKirili.ikhlosT,
                ListTarjuma.ikhlosP, i,)}
            112 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.falaq,
                ListKirili.falaqT,
                ListTarjuma.falaqP, i,)}
            113 -> {action = HomeFragmentDirections.navigateToOyatFragment(model.nameK,
                ListArabi.nos,
                ListKirili.nosT,
                ListTarjuma.nosP, i,)}

        }

        Navigation.findNavController(view).navigate(action!!)
    }


}