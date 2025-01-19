package com.denofdevelopers.taskmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.denofdevelopers.taskmanager.databinding.FragmentTaskListBinding
import com.denofdevelopers.taskmanager.ui.AppViewModelProvider
import com.denofdevelopers.taskmanager.ui.TaskListAdapter
import com.denofdevelopers.taskmanager.ui.TaskListViewModel
import kotlinx.coroutines.launch

class TaskListFragment : Fragment() {

    private val viewModel by viewModels<TaskListViewModel> { AppViewModelProvider.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentTaskListBinding.inflate(inflater, container, false).root
    }

    private val adapter = TaskListAdapter(
        onEdit = { task ->
            findNavController().navigate(
                TaskListFragmentDirections.actionTaskListFragmentToCreateTaskFragment(task.id)
            )
        },
        onDelete = { task ->
            viewModel.deleteTask(task)
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentTaskListBinding.bind(view)
        binding.recyclerView.adapter = adapter

        binding.fab.setOnClickListener { fabView ->
            fabView.findNavController().navigate(
                TaskListFragmentDirections.actionTaskListFragmentToCreateTaskFragment()
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.tasksStream.collect {
                    adapter.submitList(it)
                }
            }
        }
    }
}