package com.kma.movies.ui.details

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kma.movies.base.BaseActivity
import com.kma.movies.data.Resource
import com.kma.movies.databinding.FragmentReviewListBinding
import com.kma.movies.model.GetCommentResponse
import com.kma.movies.model.PostComment
import com.kma.movies.model.PostCommentResponse
import com.kma.movies.ui.comment.CommentViewModel
import com.kma.movies.ui.details.viewmodel.CommentListAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class CommentFragment : BaseActivity() {

    private val getCommentViewModel: CommentViewModel by viewModel()
    private val commentListAdapter: CommentListAdapter by inject()

    private var _binding: FragmentReviewListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentReviewListBinding.inflate(layoutInflater)
        binding.rcvComment.layoutManager = LinearLayoutManager(this)
        setContentView(binding.root)
        setupToolbar()
        clearStatusBar()
        setupRecyclerView()
        binding.edtPostComment.editText?.setOnEditorActionListener(
            OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || (event.action == KeyEvent.ACTION_DOWN
                            && event.keyCode == KeyEvent.KEYCODE_ENTER)
                ) {
                    onPostComment(v.text)
                    return@OnEditorActionListener true
                }
                false
            })
        lifecycleScope.launch {
            getCommentViewModel.commentMoviesState.collect {
                handleCommentMovieDataState(it)
            }
        }
        lifecycleScope.launch {
            getCommentViewModel.postCommentMoviesState.collect {
                handlePostCommentMovieDataState(it)
            }
        }
        intent.getStringExtra("movieId")?.let { getCommentViewModel.getCommentMovies(it) }
    }

    private fun onPostComment(text: CharSequence?) {
        if (text.toString().isBlank()) {
            Toast.makeText(this, "Enter review", Toast.LENGTH_LONG).show()
        } else {
            intent.getStringExtra("movieId")
                ?.let { getCommentViewModel.postComment(PostComment(it, text.toString())) }

        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.toolbar.title = "Reviews"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        getCommentViewModel.disposable?.dispose()
    }


    private fun handleCommentMovieDataState(state: Resource<List<GetCommentResponse>>) {
        when (state.status) {
            Resource.Status.LOADING -> {}
            Resource.Status.SUCCESS -> {
                loadComment(state.data)
            }

            Resource.Status.ERROR -> {
                Toast.makeText(this, "Error: ${state.message}", Toast.LENGTH_LONG).show()
            }

            Resource.Status.EMPTY -> {
                Timber.d("Empty state.")
            }
        }
    }

    private fun handlePostCommentMovieDataState(state: Resource<PostCommentResponse>) {
        when (state.status) {
            Resource.Status.LOADING -> {}
            Resource.Status.SUCCESS -> {
                intent.getStringExtra("movieId")?.let { getCommentViewModel.getCommentMovies(it) }
                binding.edtPostComment.editText?.setText("")
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            }

            Resource.Status.ERROR -> {
                Toast.makeText(this, "Login to comment", Toast.LENGTH_LONG).show()
            }

            Resource.Status.EMPTY -> {
                Timber.d("Empty state.")
            }
        }
    }

    private fun loadComment(comments: List<GetCommentResponse>?) {
        comments?.let {
            commentListAdapter.fillList(it)
        }
    }


    private fun setupRecyclerView() {
        binding.rcvComment.adapter = commentListAdapter

    }


}