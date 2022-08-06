package im.syf.geha.ui.profile.parcelable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import im.syf.geha.databinding.ViewTextBinding

class ParcelableFragment : Fragment() {

    private var _binding: ViewTextBinding? = null
    private val binding get() = _binding!!

    private val someArg: SomeParcelable by lazy {
        requireNotNull(requireArguments().getParcelable(ARG_KEY))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = "This fragment expects a parcelable and receives: $someArg"
    }

    companion object {

        private const val ARG_KEY = "arg_key"

        @JvmStatic
        fun newInstance(some: SomeParcelable): ParcelableFragment {
            return ParcelableFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_KEY, some)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
