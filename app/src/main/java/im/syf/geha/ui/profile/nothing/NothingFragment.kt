package im.syf.geha.ui.profile.nothing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import im.syf.geha.databinding.ViewTextBinding

class NothingFragment : Fragment() {

    private var _binding: ViewTextBinding? = null
    private val binding get() = _binding!!

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
        binding.textView.text = "This fragment doesn't expect any argument"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
