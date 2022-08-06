package im.syf.geha.ui.profile.text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import im.syf.geha.databinding.ViewTextBinding

class TextFragment : Fragment() {

    private var _binding: ViewTextBinding? = null
    private val binding get() = _binding!!

    private val textArg: String by lazy {
        requireNotNull(requireArguments().getString(ARG_KEY))
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
        binding.textView.text = "This fragment expects a string and receives: $textArg"
    }

    companion object {

        private const val ARG_KEY = "arg_key"

        @JvmStatic
        fun newInstance(text: String): TextFragment {
            return TextFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_KEY, text)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
