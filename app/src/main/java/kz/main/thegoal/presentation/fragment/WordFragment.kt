package kz.main.thegoal.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import kz.main.thegoal.R
import kz.main.thegoal.databinding.FragmentWordBinding
import kz.main.thegoal.presentation.viewModels.WordViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

private const val INSTAGRAM_LINK = "https://www.instagram.com/abay_app/"

class WordFragment : Fragment() {

    private lateinit var binding: FragmentWordBinding

    private val args by navArgs<WordFragmentArgs>()
    private val wordVM: WordViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    private fun setUpUi() {
        val wordTitle = args.word.wordTitle
        val wordText = args.word.wordText
        binding.wordFragmentTitle.text = wordTitle
        binding.wordFragmentText?.text = wordText

        setUpSrc(args.word.liked)
        binding.wordComponent?.likeImg?.setOnClickListener {

            Snackbar.make(
                it,
                requireContext().getText(R.string.added),
                Snackbar.LENGTH_SHORT
            ).show()

            args.word.liked = true
            setUpSrc(true)
            wordVM.addToFavorite(args.word)
        }

        binding.wordComponent?.savedImg?.setOnClickListener {

            Snackbar.make(
                it,
                requireContext().getText(R.string.removed),
                Snackbar.LENGTH_SHORT
            ).show()

            args.word.liked = false
            setUpSrc(false)
            wordVM.removeFromFavorite(args.word)
        }

        binding.wordComponent?.shareImg?.setOnClickListener {
            sendLink()
        }
    }

    private fun setUpSrc(isSaved: Boolean) {
        when (isSaved) {
            true -> {
                saved()
            }
            false -> {
                save()
            }
        }
    }

    private fun saved() {
        binding.wordComponent?.savedImg?.visibility = View.VISIBLE
        binding.wordComponent?.savedTxt?.visibility = View.VISIBLE
        binding.wordComponent?.likeImg?.visibility = View.GONE
        binding.wordComponent?.likeTxt?.visibility = View.GONE
    }

    private fun save() {
        binding.wordComponent?.savedImg?.visibility = View.GONE
        binding.wordComponent?.savedTxt?.visibility = View.GONE
        binding.wordComponent?.likeImg?.visibility = View.VISIBLE
        binding.wordComponent?.likeTxt?.visibility = View.VISIBLE
    }

    private fun sendLink() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, INSTAGRAM_LINK)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}
