package uz.ilmhona.kuron_uz.navigate

import android.app.Dialog
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.AbsListView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import uz.ilmhona.kuron_uz.Model
import uz.ilmhona.kuron_uz.R
import uz.ilmhona.kuron_uz.adapter.MyListAdapter
import uz.ilmhona.kuron_uz.databinding.FragmentOyatBinding

class OyatFragment : Fragment() {
    val args: OyatFragmentArgs by navArgs()
    lateinit var model: MutableList<Model>
    private var _binding: FragmentOyatBinding? = null
    private val binding get() = _binding!!

    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    private var progress = 0
    private var progress2 = 0
    private var position = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOyatBinding.inflate(inflater, container, false)
        val view = binding.root
        val name = args.name
        val i = args.position

        sharedPreferences = requireContext().getSharedPreferences("SAMPLE_PREFERENCE",
            AppCompatActivity.MODE_PRIVATE
        )
        editor = sharedPreferences?.edit()
        progress = sharedPreferences!!.getInt("FONT_SIZE", 0)
        progress2 = sharedPreferences!!.getInt("FONT_SIZE2", 0)
//        position = sharedPreferences!!.getInt("POSITION", 0)
        val setPosition = sharedPreferences!!.getInt("$i", 0)

        requireActivity().title = name

        model = ArrayList()

        val arabi = args.arabi
        val kirili = args.kirili
        val tarjuma = args.tarjuma
        for (i in arabi.indices){
            if (name == "Фотиҳа") model.add(Model((i + 1), arabi[i], kirili[i], tarjuma[i], name))
            else model.add(Model((i), arabi[i], kirili[i], tarjuma[i], name))
        }

        val adapter = MyListAdapter(requireContext(), R.layout.listview_item, model, progress, progress2)
//        adapter.notifyDataSetChanged()
        binding.listView.adapter = adapter
        binding.listView.setSelection(setPosition)

        binding.listView.setOnScrollListener(object : AbsListView.OnScrollListener {
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
                    position = firstVisibleItem + 1
                }
                if (lastFirstVisibleItem > firstVisibleItem) {
                    position = firstVisibleItem
                }
                lastFirstVisibleItem = firstVisibleItem
            }
        })

//        binding.listView.onItemClickListener =
//            AdapterView.OnItemClickListener { parent, view, position, id ->
//                val selectedItemText = parent.getItemAtPosition(position)
////                textView.text = "Selected : $selectedItemText"
//                Toast.makeText(requireContext(), "$selectedItemText", Toast.LENGTH_SHORT).show()
//            }

        return view
    }

    //enable options menu in this fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.size_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    //handle item clicks of menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //get item id to handle item clicks
        val id = item.itemId
        //handle item clicks
        if (id == R.id.size){
            val dialog = Dialog(requireContext())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            if (dialog.window != null) {
                val colorDrawable = ColorDrawable(Color.TRANSPARENT)
                dialog.window!!.setBackgroundDrawable(colorDrawable)
            }
            dialog.setContentView(R.layout.uploaded_dialog)
//            dialog.setCancelable(false)
            dialog.findViewById<SeekBar>(R.id.seekBar).setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    progress = i

                    val adapter = MyListAdapter(requireContext(), R.layout.listview_item, model, progress, progress2)
                    adapter.notifyDataSetChanged()
                    binding.listView.adapter = adapter
                    binding.listView.setSelection(position)
                    editor?.putInt("FONT_SIZE", progress)
                    editor?.commit()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            })

            dialog.findViewById<SeekBar>(R.id.seekBar2).setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    progress2 = i

                    val adapter = MyListAdapter(requireContext(), R.layout.listview_item, model, progress, progress2)
                    adapter.notifyDataSetChanged()
                    binding.listView.adapter = adapter
                    binding.listView.setSelection(position)
                    editor?.putInt("FONT_SIZE2", progress2)
                    editor?.commit()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            })
//            dialog.findViewById<View>(R.id.ok).setOnClickListener {
//                dialog.dismiss()
//            }
            val size = sharedPreferences?.getInt("FONT_SIZE", 0)
            dialog.findViewById<SeekBar>(R.id.seekBar).progress = size!!
            val size2 = sharedPreferences?.getInt("FONT_SIZE2", 0)
            dialog.findViewById<SeekBar>(R.id.seekBar2).progress = size2!!
            dialog.show()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        editor?.putInt("${args.position}", position)
        editor?.putInt("POSITION", position)
        editor?.putInt("POSITION_ITEM", args.position)
        editor?.putString("TEXT", args.name)
        editor?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        editor?.putInt("${args.position}", position)
        editor?.putInt("POSITION", position)
        editor?.putInt("POSITION_ITEM", args.position)
        editor?.putString("TEXT", args.name)
        editor?.commit()

        val i = if (position == 0) 1
        else position
        HomeFragment.tvMavqeiOkhiron?.text = "${args.name} сураси $i-оят"
    }
}

