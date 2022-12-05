package com.xapo.test_project_darshna22.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.xapo.test_project_darshna2.R
import com.xapo.test_project_darshna2.databinding.FragmentDetailsBinding
import com.xapo.test_project_darshna22.utility.DateUtility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            context?.let { TransitionInflater.from(it).inflateTransition(android.R.transition.move) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        _binding = FragmentDetailsBinding.bind(view)

        binding.apply {
            name.text = args.repo.name
            username.text = args.repo.owner?.login ?: ""
            language.text = args.repo.language
            description.text = args.repo.description

            avatar.apply {
                transitionName = args.repo.owner?.avatar_url ?: ""
                Glide.with(view)
                    .load(args.repo.owner?.avatar_url)
                    .error(android.R.drawable.stat_notify_error)
                    .into(this)
            }

            stars.text = args.repo.stars.toString()
            forks.text = args.repo.forks.toString()
            watchers.text = args.repo.watchers.toString()
            issuesOpened.text = args.repo.openIssues.toString()
            createDate.text = DateUtility.formatDate(args.repo.createDate)
            updateDate.text = DateUtility.formatDate(args.repo.updateDate)
            btnBrowse.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(args.repo.url))
                startActivity(browserIntent)
            }

        }

        ViewCompat.setTransitionName(binding.avatar, "avatar_${args.repo.id}")

        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                view?.let { Navigation.findNavController(it).navigateUp() }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}