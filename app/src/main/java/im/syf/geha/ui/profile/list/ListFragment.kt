package im.syf.geha.ui.profile.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import im.syf.geha.databinding.ViewListBinding

class ListFragment : Fragment() {

    private var _binding: ViewListBinding? = null
    private val binding get() = _binding!!

    private val listTypeArg: ListType by lazy {
        requireNotNull(requireArguments().getParcelable(ARG_PROPERTY_KEY))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val placeholderAdapter = PlaceholderAdapter(listTypeArg)

        with(binding.recyclerView) {
            adapter = placeholderAdapter
            setHasFixedSize(true)
        }
    }

    companion object {

        private const val ARG_PROPERTY_KEY = "arg_property_key"

        @JvmStatic
        fun newInstance(listType: ListType): ListFragment {
            return ListFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PROPERTY_KEY, listType)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
