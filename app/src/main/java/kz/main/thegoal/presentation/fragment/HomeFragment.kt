package kz.main.thegoal.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kz.main.thegoal.databinding.FragmentHomeBinding
import kz.main.thegoal.domain.entity.Word
import kz.main.thegoal.presentation.adapters.WordAdapter
import kz.main.thegoal.presentation.viewModels.WordViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val wordViewModel: WordViewModel by activityViewModel()
    private lateinit var adapter: WordAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wordViewModel.getAllFavorites()
        wordViewModel.favorites.observe(viewLifecycleOwner) {
            initAdapter(it)
        }
    }

    private fun initAdapter(list: List<Word>) {
        binding.goalsList.layoutManager = LinearLayoutManager(requireContext())
        adapter = WordAdapter(list, ::setOnClick)
        binding.goalsList.adapter = adapter
    }

    private fun setOnClick(word: Word) {
        val direction = HomeFragmentDirections.actionNavigationHomeToWordFragment(word)
        findNavController().navigate(direction)
    }
}
